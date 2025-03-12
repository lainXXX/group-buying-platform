package top.javarem.domain.trade.adapter.repository;

import top.javarem.domain.trade.model.aggregate.GroupBuyingOrderAggregate;
import top.javarem.domain.trade.model.entity.GroupBuyingActivityEntity;
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

    /**
     * 查询拼团活动
     * @param activityId id
     * @return 活动
     */
    GroupBuyingActivityEntity queryActivity(Long activityId);

    /**
     * 查询用户参与活动拼团次数
     * @param activityId 活动Id
     * @param userId 用户Id
     * @return 次数
     */
    Integer queryUserActivityPartakeCount(Long activityId, String userId);
}
