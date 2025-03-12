package top.javarem.domain.trade.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.javarem.domain.trade.adapter.repository.ITradeRepository;
import top.javarem.domain.trade.model.aggregate.GroupBuyingOrderAggregate;
import top.javarem.domain.trade.model.entity.*;
import top.javarem.domain.trade.model.vo.GroupBuyingProgressVO;
import top.javarem.domain.trade.service.factory.TradeRuleFilterFactory;
import top.javarem.types.design.framework.link.model2.BusinessLinkedList;

import javax.annotation.Resource;

/**
 * @Author: rem
 * @Date: 2025/03/09/17:11
 * @Description: 拼团交易订单服务实现类
 */
@Service
@Slf4j
public class TradeOrderService implements ITradeOrderService {

    @Resource
    private ITradeRepository repository;
    @Resource
    private TradeRuleFilterFactory tradeRuleFilterFactory;

    @Override
    public MarketPayOrderEntity queryNoPayOrderByOutTradeNo(String userId, String outTradeNo) {
        return repository.queryNoPayOrderByOutTradeNo(userId, outTradeNo);
    }

    @Override
    public GroupBuyingProgressVO queryGroupBuyingProgress(String teamId) {
        return repository.queryGroupBuyingProgress(teamId);
    }

    @Override
    public MarketPayOrderEntity lockMarketPayOrder(UserEntity userEntity, PayActivityEntity payActivityEntity, PayDiscountEntity payDiscountEntity) throws Exception {
        log.info("拼团锁单进行 activityId:{} userId:{} teamId:{}", userEntity.getUserId(), payActivityEntity.getActivityId(), payActivityEntity.getTeamId());
        BusinessLinkedList<TradeRuleFilterEntity, TradeRuleFilterFactory.DynamicContext, TradeRuleFilterBackEntity> tradeRuleFilter = tradeRuleFilterFactory.getTradeRuleFilter();
        TradeRuleFilterEntity tradeRuleFilterEntity = TradeRuleFilterEntity.builder()
                .userId(userEntity.getUserId())
                .activityId(payActivityEntity.getActivityId())
                .build();
        TradeRuleFilterBackEntity tradeRuleFilterBackEntity = tradeRuleFilter.apply(tradeRuleFilterEntity, new TradeRuleFilterFactory.DynamicContext());
        GroupBuyingOrderAggregate groupBuyingOrderAggregate = GroupBuyingOrderAggregate.builder()
                .userEntity(userEntity)
                .payActivityEntity(payActivityEntity)
                .payDiscountEntity(payDiscountEntity)
                .userPartakeCount(tradeRuleFilterBackEntity.getUserPartakeCount())
                .build();

        return repository.lockMarketPayOrder(groupBuyingOrderAggregate);
    }
}
