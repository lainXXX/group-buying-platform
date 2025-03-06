package top.javarem.infrastructure.adapter.repository;

import org.redisson.api.RBitSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import top.javarem.domain.activity.adapter.repository.IActivityRepository;
import top.javarem.domain.activity.model.entity.ScSkuActivityEntity;
import top.javarem.domain.activity.model.vo.DiscountTypeEnum;
import top.javarem.domain.activity.model.vo.GroupBuyingActivityDiscountVO;
import top.javarem.domain.activity.model.vo.SkuVO;
import top.javarem.infrastructure.dao.po.GroupBuyingActivity;
import top.javarem.infrastructure.dao.po.GroupBuyingDiscount;
import top.javarem.infrastructure.dao.po.ScSkuActivity;
import top.javarem.infrastructure.dao.po.Sku;
import top.javarem.infrastructure.dao.service.*;
import top.javarem.infrastructure.redis.IRedisService;
import top.javarem.types.common.constants.Constants;

import javax.annotation.Resource;

/**
 * @Author: rem
 * @Date: 2025/02/28/9:55
 * @Description: 活动仓储实现类
 */
@Repository
public class ActivityRepository implements IActivityRepository {

    @Resource
    private GroupBuyingActivityService groupBuyingActivityService;
    @Resource
    private GroupBuyingDiscountService groupBuyingDiscountService;
    @Resource
    private SkuService skuService;
    @Resource
    private ScSkuActivityService scSkuActivityService;
    @Autowired
    private IRedisService redisService;

    @Override
    public GroupBuyingActivityDiscountVO queryGroupBuyingActivityDiscount(Long activityId) {

        // 根据SC渠道值查询配置中最新的1个有效的活动
        GroupBuyingActivity groupBuyingActivityRes = groupBuyingActivityService.queryValidGroupBuyingActivityByActivityId(activityId);
        if (groupBuyingActivityRes == null) return null;
        GroupBuyingDiscount groupBuyingDiscountRes = groupBuyingDiscountService.queryGroupBuyingDiscountByDiscountId(groupBuyingActivityRes.getDiscountId());
        if (groupBuyingDiscountRes == null) return null;
        GroupBuyingActivityDiscountVO.GroupBuyingDiscount groupBuyingDiscount = GroupBuyingActivityDiscountVO.GroupBuyingDiscount.builder()
                .discountName(groupBuyingDiscountRes.getDiscountName())
                .discountDesc(groupBuyingDiscountRes.getDiscountDesc())
                .discountType(DiscountTypeEnum.get(groupBuyingDiscountRes.getDiscountType()))
                .marketingPlan(groupBuyingDiscountRes.getMarketingPlan())
                .marketingExpr(groupBuyingDiscountRes.getMarketingExpr())
                .tagId(groupBuyingDiscountRes.getTagId())
                .build();
        return GroupBuyingActivityDiscountVO.builder()
                .activityId(groupBuyingActivityRes.getActivityId())
                .activityName(groupBuyingActivityRes.getActivityName())
                .goodsId(groupBuyingActivityRes.getGoodsId())
                .groupBuyingDiscount(groupBuyingDiscount)
                .groupType(groupBuyingActivityRes.getGroupType())
                .takeLimitCount(groupBuyingActivityRes.getTakeLimitCount())
                .target(groupBuyingActivityRes.getTarget())
                .validTime(groupBuyingActivityRes.getValidTime())
                .status(groupBuyingActivityRes.getStatus())
                .beginTime(groupBuyingActivityRes.getBeginTime())
                .endTime(groupBuyingActivityRes.getEndTime())
                .tagId(groupBuyingActivityRes.getTagId())
                .tagScope(groupBuyingActivityRes.getTagScope())
                .build();
    }

    @Override
    public SkuVO querySkuByGoodsId(String goodsId) {
        Sku sku = skuService.querySkuByGoodsId(goodsId);
        if (sku == null) return null;
        return SkuVO.builder()
                .goodsId(sku.getGoodsId())
                .goodsName(sku.getGoodsName())
                .originalPrice(sku.getOriginalPrice())
                .build();
    }

    @Override
    public ScSkuActivityEntity queryScSkuActivityByGoodsId(String source, String channel, String goodsId) {

        ScSkuActivity scSkuActivity = scSkuActivityService.getScSkuActivity(source, channel, goodsId);
        if (scSkuActivity == null) return null;
        return ScSkuActivityEntity.builder()
                .activityId(scSkuActivity.getActivityId())
                .goodsId(scSkuActivity.getGoodsId())
                .channel(scSkuActivity.getChannel())
                .source(scSkuActivity.getSource())
                .build();

    }

    @Override
    public boolean isTagCrowdRange(String tagId, String userId) {

        String cacheKey = Constants.RedisKey.BIT_SET_KEY + tagId;
        RBitSet bitSet = redisService.getBitSet(cacheKey);
        System.out.println(bitSet.isExists());
//        if (!bitSet.isExists()) {
//            return false;
//        }
        return bitSet.get(redisService.getIndexFromUserId(userId));


    }
}
