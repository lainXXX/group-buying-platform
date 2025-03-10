package top.javarem.infrastructure.adapter.repository;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import top.javarem.domain.trade.adapter.repository.ITradeRepository;
import top.javarem.domain.trade.model.aggregate.GroupBuyingOrderAggregate;
import top.javarem.domain.trade.model.entity.MarketPayOrderEntity;
import top.javarem.domain.trade.model.entity.PayActivityEntity;
import top.javarem.domain.trade.model.entity.PayDiscountEntity;
import top.javarem.domain.trade.model.entity.UserEntity;
import top.javarem.domain.trade.model.vo.GroupBuyingProgressVO;
import top.javarem.domain.trade.model.vo.TradeOrderStatusEnumVO;
import top.javarem.infrastructure.dao.po.GroupBuyOrderList;
import top.javarem.infrastructure.dao.po.GroupBuyingOrder;
import top.javarem.infrastructure.dao.service.GroupBuyOrderListService;
import top.javarem.infrastructure.dao.service.GroupBuyingOrderService;
import top.javarem.types.enums.ResponseCode;
import top.javarem.types.exception.AppException;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author: rem
 * @Date: 2025/03/09/17:09
 * @Description:
 */
@Repository
public class TradeRepository implements ITradeRepository {

    @Resource
    private GroupBuyingOrderService groupBuyingOrderService;

    @Resource
    private GroupBuyOrderListService groupBuyOrderListService;

    @Override
    public MarketPayOrderEntity queryNoPayOrderByOutTradeNo(String userId, String outTradeNo) {
        GroupBuyOrderList groupBuyOrderList = groupBuyOrderListService.queryNoPayOrderByOutTradeNo(userId, outTradeNo);
        if (groupBuyOrderList == null) return null;
        return MarketPayOrderEntity.builder()
                .orderId(groupBuyOrderList.getOrderId())
                .discountPrice(groupBuyOrderList.getDiscountPrice())
                .tradeOrderStatusEnumVO(TradeOrderStatusEnumVO.getByCode(groupBuyOrderList.getStatus()))
                .build();
    }

    @Override
    public GroupBuyingProgressVO queryGroupBuyingProgress(String teamId) {

        GroupBuyingOrder groupBuyingOrder = groupBuyingOrderService.queryGroupBuyingProgress(teamId);
        if (groupBuyingOrder == null) return null;
        return GroupBuyingProgressVO.builder()
                .completeCount(groupBuyingOrder.getCompleteCount())
                .lockCount(groupBuyingOrder.getLockCount())
                .targetCount(groupBuyingOrder.getTargetCount())
                .build();

    }

    @Transactional(timeout = 500)
    @Override
    public MarketPayOrderEntity lockMarketPayOrder(GroupBuyingOrderAggregate groupBuyingOrderAggregate) {
        PayActivityEntity payActivityEntity = groupBuyingOrderAggregate.getPayActivityEntity();
        UserEntity userEntity = groupBuyingOrderAggregate.getUserEntity();
        PayDiscountEntity payDiscountEntity = groupBuyingOrderAggregate.getPayDiscountEntity();
        String teamId = payActivityEntity.getTeamId();
        if (StringUtils.isBlank(teamId)) {

            teamId = RandomStringUtils.randomNumeric(8);
            GroupBuyingOrder groupBuyingOrder = GroupBuyingOrder.builder()
                    .teamId(teamId)
                    .activityId(payActivityEntity.getActivityId())
                    .channel(payDiscountEntity.getChannel())
                    .source(payDiscountEntity.getSource())
                    .originalPrice(payDiscountEntity.getOriginalPrice())
                    .discountPrice(payDiscountEntity.getDiscountPrice())
                    .payPrice(payDiscountEntity.getDiscountPrice())
                    .targetCount(payActivityEntity.getTargetCount())
                    .completeCount(0)
                    .lockCount(1)
                    .status(TradeOrderStatusEnumVO.CREATE.getCode())
                    .createTime(new Date())
                    .updateTime(new Date())
                    .build();
//            写入记录
            groupBuyingOrderService.save(groupBuyingOrder);

        } else {
            int count = groupBuyingOrderService.updateAddLockCount(teamId);
            if (count != 1) {
                throw new AppException(ResponseCode.E0003);
            }
        }

        String orderId = RandomStringUtils.randomNumeric(12);
        GroupBuyOrderList groupBuyOrderList = GroupBuyOrderList.builder()
                .userId(userEntity.getUserId())
                .teamId(teamId)
                .orderId(orderId)
                .activityId(payActivityEntity.getActivityId())
                .beginTime(payActivityEntity.getBeginTime())
                .endTime(payActivityEntity.getEndTime())
                .goodsId(payDiscountEntity.getGoodsId())
                .source(payDiscountEntity.getSource())
                .channel(payDiscountEntity.getChannel())
                .originalPrice(payDiscountEntity.getOriginalPrice())
                .discountPrice(payDiscountEntity.getDiscountPrice())
                .status(TradeOrderStatusEnumVO.CREATE.getCode())
                .outTradeNo(payDiscountEntity.getOutTradeNo())
                .createTime(new Date())
                .updateTime(new Date())
                .build();

        try {
            groupBuyOrderListService.save(groupBuyOrderList);
        } catch (DuplicateKeyException e) {
            throw new AppException(ResponseCode.INDEX_EXCEPTION);
        } catch (AppException ex) {
            throw new RuntimeException(ex);
        }
        return MarketPayOrderEntity.builder()
                .orderId(orderId)
                .discountPrice(payDiscountEntity.getDiscountPrice())
                .tradeOrderStatusEnumVO(TradeOrderStatusEnumVO.CREATE)
                .build();
    }

}


