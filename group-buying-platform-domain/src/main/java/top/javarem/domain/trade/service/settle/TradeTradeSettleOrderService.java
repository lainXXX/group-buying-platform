package top.javarem.domain.trade.service.settle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.javarem.domain.trade.adapter.repository.ITradeRepository;
import top.javarem.domain.trade.model.aggregate.GroupBuyingSettleOrderAggregate;
import top.javarem.domain.trade.model.entity.*;
import top.javarem.domain.trade.service.ITradeSettleOrderService;
import top.javarem.types.enums.ResponseCode;
import top.javarem.types.exception.AppException;

import javax.annotation.Resource;

/**
 * @Author: rem
 * @Date: 2025/03/13/15:27
 * @Description: 交易结算订单服务实现
 */
@Service
@Slf4j
public class TradeTradeSettleOrderService implements ITradeSettleOrderService {

    @Resource
    private ITradeRepository repository;

    @Override
    public TradePaySettleEntity settlePayOrder(PaySuccessEntity paySuccessEntity) {

        log.info("交易结算订单服务- userId:{}, outTradeNo:{}", paySuccessEntity.getUserId(), paySuccessEntity.getOutTradeNO());
//        1.通过外部透传ID查询订单是否存在
        MarketPayOrderEntity marketPayOrderEntity = repository.queryNoPayOrderByOutTradeNo(paySuccessEntity.getUserId(), paySuccessEntity.getOutTradeNO());
        if (marketPayOrderEntity == null) {
            log.info("用户拼团订单无效 userId:{}, outTradeNo:{}", paySuccessEntity.getUserId(), paySuccessEntity.getOutTradeNO());
            throw new AppException(ResponseCode.E0008);
        }

//        2.获取拼团组队信息
        GroupBuyingTeamEntity groupBuyingTeamEntity = repository.queryGroupBuyingTeam(marketPayOrderEntity.getTeamId(), marketPayOrderEntity.getActivityId());

//        3.构建聚合
        GroupBuyingSettleOrderAggregate groupBuyingSettleOrderAggregate = GroupBuyingSettleOrderAggregate.builder()
                .userEntity(UserEntity.builder().userId(paySuccessEntity.getUserId()).build())
                .groupBuyingTeamEntity(groupBuyingTeamEntity)
                .marketPayOrderEntity(marketPayOrderEntity)
                .build();
//        更新订单详情状态为交易完成 更新拼团组队进度
        repository.updateTradeOrder(groupBuyingSettleOrderAggregate);

        return TradePaySettleEntity.builder()
                .userId(paySuccessEntity.getUserId())
                .activityId(groupBuyingTeamEntity.getActivityId())
                .teamId(groupBuyingTeamEntity.getTeamId())
                .outTradeNo(paySuccessEntity.getOutTradeNO())
                .channel(paySuccessEntity.getChannel())
                .source(paySuccessEntity.getSource())
                .build();

    }
}
