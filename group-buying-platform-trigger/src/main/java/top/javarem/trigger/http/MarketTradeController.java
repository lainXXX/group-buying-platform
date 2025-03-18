package top.javarem.trigger.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import top.javarem.api.IMarketTradeService;
import top.javarem.api.dto.LockMarketPayOrderRequestDTO;
import top.javarem.api.dto.LockMarketPayOrderResponseDTO;
import top.javarem.api.dto.PaySuccessRequestDTO;
import top.javarem.api.response.Response;
import top.javarem.domain.activity.model.entity.MarketProductEntity;
import top.javarem.domain.activity.model.entity.TrialBalanceEntity;
import top.javarem.domain.activity.model.vo.GroupBuyingActivityDiscountVO;
import top.javarem.domain.activity.service.IIndexGroupBuyingService;
import top.javarem.domain.trade.model.entity.*;
import top.javarem.domain.trade.model.vo.GroupBuyingProgressVO;
import top.javarem.domain.trade.service.ITradeOrderService;
import top.javarem.domain.trade.service.ITradeSettleOrderService;
import top.javarem.types.common.GsonUtils;
import top.javarem.types.enums.ResponseCode;
import top.javarem.types.exception.AppException;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Author: rem
 * @Date: 2025/03/09/19:13
 * @Description:
 */
@RestController
@RequestMapping("/api/v1/trade")
@CrossOrigin("*")
@Slf4j
public class MarketTradeController implements IMarketTradeService {

    @Resource
    private ITradeOrderService tradeOrderService;

    @Resource
    private IIndexGroupBuyingService indexGroupBuyingService;

    @Resource
    private ITradeSettleOrderService tradeSettleOrderService;

    @PostMapping("/lock_pay_order")
    @Override
    public Response<LockMarketPayOrderResponseDTO> lockPayOrder(@RequestBody LockMarketPayOrderRequestDTO lockMarketPayOrderRequestDTO) {
        try {
            // 参数
            String userId = lockMarketPayOrderRequestDTO.getUserId();
            String source = lockMarketPayOrderRequestDTO.getSource();
            String channel = lockMarketPayOrderRequestDTO.getChannel();
            String goodsId = lockMarketPayOrderRequestDTO.getGoodsId();
            Long activityId = lockMarketPayOrderRequestDTO.getActivityId();
            String outTradeNo = lockMarketPayOrderRequestDTO.getOutTradeNo();
            String teamId = lockMarketPayOrderRequestDTO.getTeamId();
            String notifyUrl = lockMarketPayOrderRequestDTO.getNotifyUrl();
            log.info("营销交易锁单:{} LockMarketPayOrderRequestDTO:{}", userId, GsonUtils.gson.toJson(lockMarketPayOrderRequestDTO));
            if (StringUtils.isBlank(userId) || StringUtils.isBlank(source) || StringUtils.isBlank(channel) || StringUtils.isBlank(goodsId) || StringUtils.isBlank(goodsId) || null == activityId || StringUtils.isBlank(notifyUrl)) {
                return Response.<LockMarketPayOrderResponseDTO>builder()
                        .code(ResponseCode.ILLEGAL_PARAMETER.getCode())
                        .info(ResponseCode.ILLEGAL_PARAMETER.getInfo())
                        .build();
            }
            // 查询 outTradeNo 是否已经存在交易记录
            MarketPayOrderEntity marketPayOrderEntity = tradeOrderService.queryNoPayOrderByOutTradeNo(userId, outTradeNo);
            if (null != marketPayOrderEntity) {
                LockMarketPayOrderResponseDTO lockMarketPayOrderResponseDTO = LockMarketPayOrderResponseDTO.builder()
                        .orderId(marketPayOrderEntity.getOrderId())
                        .discountPrice(marketPayOrderEntity.getDiscountPrice())
                        .tradeOrderStatus(marketPayOrderEntity.getTradeOrderStatusEnumVO().getCode())
                        .build();

                log.info("交易锁单记录(存在):{} marketPayOrderEntity:{}", userId, GsonUtils.gson.toJson(lockMarketPayOrderResponseDTO));
                return Response.<LockMarketPayOrderResponseDTO>builder()
                        .code(ResponseCode.SUCCESS.getCode())
                        .info(ResponseCode.SUCCESS.getInfo())
                        .data(lockMarketPayOrderResponseDTO)
                        .build();
            }

            // 判断拼团锁单是否完成了目标 也就是拼团人数是否达到上限
            if (null != teamId) {
                GroupBuyingProgressVO groupBuyProgressVO = tradeOrderService.queryGroupBuyingProgress(teamId);
                if (null != groupBuyProgressVO && Objects.equals(groupBuyProgressVO.getTargetCount(), groupBuyProgressVO.getLockCount())) {
                    log.info("交易锁单拦截-拼单目标已达成:{} {}", userId, teamId);
                    return Response.<LockMarketPayOrderResponseDTO>builder()
                            .code(ResponseCode.E0004.getCode())
                            .info(ResponseCode.E0004.getInfo())
                            .build();
                }
            }

            // 营销优惠试算
            TrialBalanceEntity trialBalanceEntity = indexGroupBuyingService.indexMarketTrial(MarketProductEntity.builder()
                    .userId(userId)
                    .source(source)
                    .channel(channel)
                    .goodsId(goodsId)
                    .activityId(activityId)
                    .build());

            GroupBuyingActivityDiscountVO groupBuyActivityDiscountVO = trialBalanceEntity.getGroupBuyingActivityDiscountVO();

            // 锁单
            marketPayOrderEntity = tradeOrderService.lockMarketPayOrder(
                    UserEntity.builder().userId(userId).build(),
                    PayActivityEntity.builder()
                            .teamId(teamId)
                            .activityId(activityId)
                            .activityName(groupBuyActivityDiscountVO.getActivityName())
                            .beginTime(groupBuyActivityDiscountVO.getBeginTime())
                            .endTime(groupBuyActivityDiscountVO.getEndTime())
                            .targetCount(groupBuyActivityDiscountVO.getTarget())
                            .build(),
                    PayDiscountEntity.builder()
                            .source(source)
                            .channel(channel)
                            .goodsId(goodsId)
                            .goodsName(trialBalanceEntity.getGoodsName())
                            .originalPrice(trialBalanceEntity.getOriginalPrice())
                            .discountPrice(trialBalanceEntity.getDiscountPrice())
                            .payPrice(trialBalanceEntity.getPayPrice())
                            .outTradeNo(outTradeNo)
                            .notifyUrl(notifyUrl)
                            .build());

            log.info("交易锁单记录(新):{} marketPayOrderEntity:{}", userId, GsonUtils.gson.toJson(marketPayOrderEntity));

            // 返回结果
            return Response.<LockMarketPayOrderResponseDTO>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data(LockMarketPayOrderResponseDTO.builder()
                            .orderId(marketPayOrderEntity.getOrderId())
                            .discountPrice(marketPayOrderEntity.getDiscountPrice())
                            .tradeOrderStatus(marketPayOrderEntity.getTradeOrderStatusEnumVO().getCode())
                            .build())
                    .build();
        } catch (AppException e) {
            log.error("营销交易锁单业务异常:{} LockMarketPayOrderRequestDTO:{}", lockMarketPayOrderRequestDTO.getUserId(), GsonUtils.gson.toJson(lockMarketPayOrderRequestDTO), e);
            return Response.<LockMarketPayOrderResponseDTO>builder()
                    .code(e.getCode())
                    .info(e.getInfo())
                    .build();
        } catch (Exception e) {
            log.error("营销交易锁单服务失败:{} LockMarketPayOrderRequestDTO:{}", lockMarketPayOrderRequestDTO.getUserId(), GsonUtils.gson.toJson(lockMarketPayOrderRequestDTO), e);
            return Response.<LockMarketPayOrderResponseDTO>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }

    }

    @PostMapping("/settle_pay_order")
    @Override
    public Response<Boolean> settlePayOrder(@RequestBody PaySuccessRequestDTO paySuccessRequestDTO) {
        try {
            String userId = paySuccessRequestDTO.getUserId();
            String outTradeNo = paySuccessRequestDTO.getOutTradeNo();
            String goodId = paySuccessRequestDTO.getGoodId();
            String channel = paySuccessRequestDTO.getChannel();
            String source = paySuccessRequestDTO.getSource();
            if (StringUtils.isAnyBlank(userId, outTradeNo, goodId, channel, source)) {
                throw new AppException(ResponseCode.ILLEGAL_PARAMETER);
            }
            log.info("营销交易结算支付订单 userId:{}, outTradeNo:{}", userId, outTradeNo);
            TradePaySettleEntity tradePaySettleEntity = tradeSettleOrderService.settlePayOrder(PaySuccessEntity.builder()
                    .userId(userId)
                    .outTradeNo(outTradeNo)
                    .goodId(goodId)
                    .channel(channel)
                    .source(source)
                    .build());
            return Response.success();
        } catch (AppException e) {
            log.error("营销交易结算支付订单 -非法参数 userId:{}, outTradeNo:{}", paySuccessRequestDTO.getUserId(), paySuccessRequestDTO.getOutTradeNo(), e);
            return Response.error();
        } catch (Exception e) {
            log.error("营销交易结算支付订单 -结算订单失败 userId:{}, outTradeNo:{}", paySuccessRequestDTO.getUserId(), paySuccessRequestDTO.getOutTradeNo(), e);
            return Response.error();
        }


    }
}
