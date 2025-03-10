package top.javarem.domain.trade.service;

import org.springframework.stereotype.Service;
import top.javarem.domain.trade.adapter.repository.ITradeRepository;
import top.javarem.domain.trade.model.aggregate.GroupBuyingOrderAggregate;
import top.javarem.domain.trade.model.entity.MarketPayOrderEntity;
import top.javarem.domain.trade.model.entity.PayActivityEntity;
import top.javarem.domain.trade.model.entity.PayDiscountEntity;
import top.javarem.domain.trade.model.entity.UserEntity;
import top.javarem.domain.trade.model.vo.GroupBuyingProgressVO;

import javax.annotation.Resource;

/**
 * @Author: rem
 * @Date: 2025/03/09/17:11
 * @Description:
 */
@Service
public class TradeOrderService implements ITradeOrderService {

    @Resource
    private ITradeRepository repository;

    @Override
    public MarketPayOrderEntity queryNoPayOrderByOutTradeNo(String userId, String outTradeNo) {
        return repository.queryNoPayOrderByOutTradeNo(userId, outTradeNo);
    }

    @Override
    public GroupBuyingProgressVO queryGroupBuyingProgress(String teamId) {
        return repository.queryGroupBuyingProgress(teamId);
    }

    @Override
    public MarketPayOrderEntity lockMarketPayOrder(UserEntity userEntity, PayActivityEntity payActivityEntity, PayDiscountEntity payDiscountEntity) {

        GroupBuyingOrderAggregate groupBuyingOrderAggregate = GroupBuyingOrderAggregate.builder()
                .userEntity(userEntity)
                .payActivityEntity(payActivityEntity)
                .payDiscountEntity(payDiscountEntity)
                .build();

        return repository.lockMarketPayOrder(groupBuyingOrderAggregate);
    }
}
