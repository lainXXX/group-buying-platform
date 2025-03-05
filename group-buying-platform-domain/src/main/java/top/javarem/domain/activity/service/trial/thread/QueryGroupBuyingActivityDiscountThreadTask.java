package top.javarem.domain.activity.service.trial.thread;

import top.javarem.domain.activity.adapter.repository.IActivityRepository;
import top.javarem.domain.activity.model.entity.ScSkuActivityEntity;
import top.javarem.domain.activity.model.vo.GroupBuyingActivityDiscountVO;

import java.util.concurrent.Callable;

/**
 * @Author: rem
 * @Date: 2025/02/27/20:38
 * @Description: 查询拼团活动折扣任务
 */
public class QueryGroupBuyingActivityDiscountThreadTask implements Callable<GroupBuyingActivityDiscountVO> {

    /**
     * 来源
     */
    private final String source;

    /**
     * 渠道
     */
    private final String channel;

    /**
     * 商品Id
     */
    private String goodsId;

    /**
     * 活动仓储
     */
    private final IActivityRepository activityRepository;

    public QueryGroupBuyingActivityDiscountThreadTask(String source, String channel, String goodsId, IActivityRepository activityRepository) {
        this.source = source;
        this.channel = channel;
        this.goodsId = goodsId;
        this.activityRepository = activityRepository;
    }

    @Override
    public GroupBuyingActivityDiscountVO call() throws Exception {
        ScSkuActivityEntity scSkuActivity = activityRepository.queryScSkuActivityByGoodsId(source, channel, goodsId);
        if (scSkuActivity == null) return null;
        return activityRepository.queryGroupBuyingActivityDiscount(scSkuActivity.getActivityId());
    }
}
