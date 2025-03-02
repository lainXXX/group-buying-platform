package top.javarem.infrastructure.adapter.repository;

import org.springframework.stereotype.Repository;
import top.javarem.domain.activity.adapter.repository.IActivityRepository;
import top.javarem.domain.activity.model.vo.GroupBuyingActivityDiscountVO;
import top.javarem.domain.activity.model.vo.SkuVO;
import top.javarem.infrastructure.dao.po.GroupBuyingActivity;
import top.javarem.infrastructure.dao.po.GroupBuyingDiscount;
import top.javarem.infrastructure.dao.po.Sku;
import top.javarem.infrastructure.dao.service.GroupBuyingActivityService;
import top.javarem.infrastructure.dao.service.GroupBuyingDiscountService;
import top.javarem.infrastructure.dao.service.SkuService;

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

    @Override
    public GroupBuyingActivityDiscountVO queryGroupBuyingActivityDiscount(String source, String channel) {
        // 根据SC渠道值查询配置中最新的1个有效的活动
        GroupBuyingActivity groupBuyingActivityRes = groupBuyingActivityService.queryValidGroupBuyingActivity(source, channel);
        String discountId = groupBuyingActivityRes.getDiscountId();
        GroupBuyingDiscount groupBuyingDiscountRes = groupBuyingDiscountService.queryGroupBuyingDiscountByDiscountId(discountId);
        GroupBuyingActivityDiscountVO.GroupBuyingDiscount groupBuyingDiscount = GroupBuyingActivityDiscountVO.GroupBuyingDiscount.builder()
                .discountName(groupBuyingDiscountRes.getDiscountName())
                .discountDesc(groupBuyingDiscountRes.getDiscountDesc())
                .discountType(groupBuyingDiscountRes.getDiscountType())
                .marketingPlan(groupBuyingDiscountRes.getMarketingPlan())
                .marketingExpr(groupBuyingDiscountRes.getMarketingExpr())
                .tagId(groupBuyingDiscountRes.getTagId())
                .build();
        return GroupBuyingActivityDiscountVO.builder()
                .activityId(groupBuyingActivityRes.getActivityId())
                .activityName(groupBuyingActivityRes.getActivityName())
                .source(groupBuyingActivityRes.getSource())
                .channel(groupBuyingActivityRes.getChannel())
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
        return SkuVO.builder()
                .goodsId(sku.getGoodsId())
                .goodsName(sku.getGoodsName())
                .originalPrice(sku.getOriginalPrice())
                .build();
    }
}
