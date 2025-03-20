package top.javarem.infrastructure.adapter.repository;

import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RBitSet;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import top.javarem.domain.activity.adapter.repository.IActivityRepository;
import top.javarem.domain.activity.model.entity.GroupBuyingTeamOrderDetailEntity;
import top.javarem.domain.activity.model.entity.ScSkuActivityEntity;
import top.javarem.domain.activity.model.vo.DiscountTypeEnum;
import top.javarem.domain.activity.model.vo.GroupBuyingActivityDiscountVO;
import top.javarem.domain.activity.model.vo.SkuVO;
import top.javarem.domain.activity.model.vo.TeamStatisticVO;
import top.javarem.infrastructure.dao.po.*;
import top.javarem.infrastructure.dao.service.*;
import top.javarem.infrastructure.redis.IRedisService;
import top.javarem.types.common.Constants;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

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
    @Resource
    private IRedisService redisService;
    @Resource
    private GroupBuyOrderListService groupBuyOrderListService;
    @Resource
    private GroupBuyingOrderService groupBuyingOrderService;

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
        if (!bitSet.isExists()) {
            return false;
        }
        return bitSet.get(redisService.getIndexFromUserId(userId));


    }

    @Override
    public List<GroupBuyingTeamOrderDetailEntity> queryOwnerGroupBuyingTeamOrderDetailList(String userId, Long activityId, int ownerCount) {

//        1.查询拼团个人订单信息
        List<GroupBuyOrderList> groupBuyOrderLists = groupBuyOrderListService.queryOwnerGroupBuyingTeamOrderDetailList(userId, activityId, ownerCount);

        if (CollectionUtils.isEmpty(groupBuyOrderLists)) return null;
//        2.过滤掉没有拼团的订单 (即直接购买而非拼团购买)
        Set<String> teamIdSet = groupBuyOrderLists.stream()
                .map(GroupBuyOrderList::getTeamId)
                .filter(teamId -> StringUtils.isNotBlank(teamId))
                .collect(Collectors.toSet());
//        3.根据自己的个人拼团订单查询拼团组队的信息
        List<GroupBuyingOrder> groupBuyingOrderList = groupBuyingOrderService.queryInProgressGroupBuyingTeam(teamIdSet);
        if (CollectionUtils.isEmpty(groupBuyingOrderList)) return null;
//        4.组队信息转化为map 方便下一步遍历封装到teamOrderDetail中
        Map<String, GroupBuyingOrder> teamDetailMap = groupBuyingOrderList.stream()
                .collect(Collectors.toMap(GroupBuyingOrder::getTeamId, order -> order));

//        5.遍历封装信息到集合中
        List<GroupBuyingTeamOrderDetailEntity> userGroupBuyOrderDetailEntities = new ArrayList<>();
        for (GroupBuyOrderList groupBuyOrderList : groupBuyOrderLists) {
            String teamId = groupBuyOrderList.getTeamId();
            GroupBuyingOrder groupBuyOrder = teamDetailMap.get(teamId);
            if (null == groupBuyOrder) continue;

            GroupBuyingTeamOrderDetailEntity userGroupBuyOrderDetailEntity = GroupBuyingTeamOrderDetailEntity.builder()
                    .userId(groupBuyOrderList.getUserId())
                    .teamId(groupBuyOrder.getTeamId())
                    .activityId(groupBuyOrder.getActivityId())
                    .targetCount(groupBuyOrder.getTargetCount())
                    .completeCount(groupBuyOrder.getCompleteCount())
                    .lockCount(groupBuyOrder.getLockCount())
                    .validBeginTime(groupBuyOrder.getValidBeginTime())
                    .validEndTime(groupBuyOrder.getValidEndTime())
                    .outTradeNo(groupBuyOrderList.getOutTradeNo())
                    .build();

            userGroupBuyOrderDetailEntities.add(userGroupBuyOrderDetailEntity);
        }
        return userGroupBuyOrderDetailEntities;
    }

    @Override
    public List<GroupBuyingTeamOrderDetailEntity> queryOthersGroupBuyingTeamOrderDetailList(String userId, Long activityId, int randomCount) {
        //        1.查询拼团个人订单信息
        List<GroupBuyOrderList> groupBuyOrderDetails = groupBuyOrderListService.queryOtherGroupBuyingTeamOrderDetailList(userId, activityId, randomCount * 2);

        if (CollectionUtils.isEmpty(groupBuyOrderDetails)) return null;

        // 判断总量是否大于 randomCount
        if (groupBuyOrderDetails.size() > randomCount) {
            // 随机打乱列表
            Collections.shuffle(groupBuyOrderDetails);
            // 获取前 randomCount 个元素
            groupBuyOrderDetails = groupBuyOrderDetails.subList(0, randomCount);
        }
//        2.过滤掉没有拼团的订单 (即直接购买而非拼团购买)
        Set<String> teamIdSet = groupBuyOrderDetails.stream()
                .map(GroupBuyOrderList::getTeamId)
                .filter(teamId -> StringUtils.isNotBlank(teamId))
                .collect(Collectors.toSet());
//        3.根据拼团订单查询拼团组队的信息
        List<GroupBuyingOrder> groupBuyingOrderList = groupBuyingOrderService.queryInProgressGroupBuyingTeam(teamIdSet);
        if (CollectionUtils.isEmpty(groupBuyingOrderList)) return null;
//        4.组队信息转化为map 方便下一步遍历封装到teamOrderDetail中
        Map<String, GroupBuyingOrder> teamDetailMap = groupBuyingOrderList.stream()
                .collect(Collectors.toMap(GroupBuyingOrder::getTeamId, order -> order));

//        5.遍历封装信息到集合中
        List<GroupBuyingTeamOrderDetailEntity> userGroupBuyOrderDetailEntities = new ArrayList<>();
        for (GroupBuyOrderList groupBuyOrderList : groupBuyOrderDetails) {
            String teamId = groupBuyOrderList.getTeamId();
            GroupBuyingOrder groupBuyOrder = teamDetailMap.get(teamId);
            if (null == groupBuyOrder) continue;

            GroupBuyingTeamOrderDetailEntity userGroupBuyOrderDetailEntity = GroupBuyingTeamOrderDetailEntity.builder()
                    .userId(groupBuyOrderList.getUserId())
                    .teamId(groupBuyOrder.getTeamId())
                    .activityId(groupBuyOrder.getActivityId())
                    .targetCount(groupBuyOrder.getTargetCount())
                    .completeCount(groupBuyOrder.getCompleteCount())
                    .lockCount(groupBuyOrder.getLockCount())
                    .validBeginTime(groupBuyOrder.getValidBeginTime())
                    .validEndTime(groupBuyOrder.getValidEndTime())
                    .outTradeNo(groupBuyOrderList.getOutTradeNo())
                    .build();

            userGroupBuyOrderDetailEntities.add(userGroupBuyOrderDetailEntity);
        }
        return userGroupBuyOrderDetailEntities;
    }

    @Override
    public TeamStatisticVO queryTeamStatisticsByActivityId(Long activityId, String goodsId) {

        /**
         * 为什么要通过user订单来获取teamId 而不是直接使用activityId到组队里面寻找匹配的全部组队
         * 因为一个活动对应多个商品 但是一个商品只有一个活动 所以先查询商品中的组队 再过滤才能准确获取对应商品的组队ID
         */

//        1.查询活动内的拼团订单数
        List<GroupBuyOrderList> groupBuyOrderLists = groupBuyOrderListService.queryActivityUserOrders(activityId, goodsId);

        if (CollectionUtils.isEmpty(groupBuyOrderLists)) return null;
//        2.过滤组队获取teamId
        Set<String> teamIds = groupBuyOrderLists.stream()
                .filter(groupBuyOrderList -> StringUtils.isNotBlank(groupBuyOrderList.getTeamId()))
                .map(GroupBuyOrderList::getTeamId)
                .collect(Collectors.toSet());

//        3.统计信息
        Integer teamCount = teamIds.size();
        Integer completeCount = groupBuyingOrderService.getAllCompleteCount(teamIds);
        Integer userCount = groupBuyingOrderService.getUserCount(teamIds);

        // 4. 构建对象
        return TeamStatisticVO.builder()
                .allTeamCount(teamCount)
                .allTeamCompleteCount(completeCount)
                .allTeamUserCount(userCount)
                .build();

    }
}
