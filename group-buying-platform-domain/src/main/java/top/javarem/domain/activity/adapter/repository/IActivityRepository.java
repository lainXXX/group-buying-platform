package top.javarem.domain.activity.adapter.repository;

import top.javarem.domain.activity.model.vo.GroupBuyingActivityDiscountVO;
import top.javarem.domain.activity.model.vo.SkuVO;

/**
 * @Author: rem
 * @Date: 2025/02/27/19:27
 * @Description: 活动仓储
 */
public interface IActivityRepository {

    GroupBuyingActivityDiscountVO queryGroupBuyingActivityDiscount(String source, String channel);

    SkuVO querySkuByGoodsId(String goodsId);

}
