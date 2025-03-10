package top.javarem.domain.trade.adapter.repository;

import top.javarem.domain.trade.model.aggregate.GroupBuyingOrderAggregate;
import top.javarem.domain.trade.model.entity.MarketPayOrderEntity;
import top.javarem.domain.trade.model.vo.GroupBuyingProgressVO;

/**
 * @Author: rem
 * @Date: 2025/03/09/17:09
 * @Description: 交易仓储接口
 */
public interface ITradeRepository {


    MarketPayOrderEntity queryNoPayOrderByOutTradeNo(String userId, String outTradeNo);

    GroupBuyingProgressVO queryGroupBuyingProgress(String teamId);

    MarketPayOrderEntity lockMarketPayOrder(GroupBuyingOrderAggregate groupBuyingOrderAggregate);
}
