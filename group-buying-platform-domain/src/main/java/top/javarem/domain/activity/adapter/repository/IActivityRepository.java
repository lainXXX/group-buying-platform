package top.javarem.domain.activity.adapter.repository;

import top.javarem.domain.activity.model.entity.ScSkuActivityEntity;
import top.javarem.domain.activity.model.vo.GroupBuyingActivityDiscountVO;
import top.javarem.domain.activity.model.vo.SkuVO;

/**
 * @Author: rem
 * @Date: 2025/02/27/19:27
 * @Description: 活动仓储
 */
public interface IActivityRepository {

    /**
     * 查询拼团活动折扣
     * @param source 来源
     * @param channel 渠道
     * @return
     */
    GroupBuyingActivityDiscountVO queryGroupBuyingActivityDiscount(String source, String channel);

    /**
     * 查询商品信息通过商品Id
     * @param goodsId 商品ID
     * @return
     */
    SkuVO querySkuByGoodsId(String goodsId);

    /**
     * 查询拼团活动折扣通过活动Id
     * @param activityId 活动Id
     * @return
     */
    GroupBuyingActivityDiscountVO queryGroupBuyingActivityDiscount(Long activityId);

    /**
     * 查询渠道商品活动配置关联信息
     * @param source 来源
     * @param channel 渠道
     * @param goodsId 商品Id
     * @return
     */
    ScSkuActivityEntity queryScSkuActivityByGoodsId(String source, String channel, String goodsId);
}
