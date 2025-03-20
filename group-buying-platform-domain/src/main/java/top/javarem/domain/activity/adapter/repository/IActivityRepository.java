package top.javarem.domain.activity.adapter.repository;

import top.javarem.domain.activity.model.entity.GroupBuyingTeamOrderDetailEntity;
import top.javarem.domain.activity.model.entity.ScSkuActivityEntity;
import top.javarem.domain.activity.model.vo.GroupBuyingActivityDiscountVO;
import top.javarem.domain.activity.model.vo.SkuVO;
import top.javarem.domain.activity.model.vo.TeamStatisticVO;

import java.util.List;

/**
 * @Author: rem
 * @Date: 2025/02/27/19:27
 * @Description: 活动仓储
 */
public interface IActivityRepository {

    /**
     * 查询拼团活动折扣通过活动Id
     * @param activityId 活动Id
     * @return
     */
    GroupBuyingActivityDiscountVO queryGroupBuyingActivityDiscount(Long activityId);

    /**
     * 查询商品信息通过商品Id
     * @param goodsId 商品ID
     * @return
     */
    SkuVO querySkuByGoodsId(String goodsId);


    /**
     * 查询渠道商品活动配置关联信息
     * @param source 来源
     * @param channel 渠道
     * @param goodsId 商品Id
     * @return
     */
    ScSkuActivityEntity queryScSkuActivityByGoodsId(String source, String channel, String goodsId);

    /**
     * 判断用户是否属于对应标签人群
     * @param tagId 标签Id
     * @param userId 用户Id
     * @return
     */
    boolean isTagCrowdRange(String tagId, String userId);

    /**
     * 查询个人拼团组队详情信息
     *
     * @param userId     用户id
     * @param activityId 活动id
     * @param ownerCount 需要的信息条数
     * @return 个人拼团组队详情信息集合
     */
    List<GroupBuyingTeamOrderDetailEntity> queryOwnerGroupBuyingTeamOrderDetailList(String userId, Long activityId, int ownerCount);

    /**
     * 查询个人拼团组队详情信息
     *
     * @param userId      用户id
     * @param activityId  活动id
     * @param randomCount 需要的信息条数
     * @return 其他的拼团组队详情信息集合
     */
    List<GroupBuyingTeamOrderDetailEntity> queryOthersGroupBuyingTeamOrderDetailList(String userId, Long activityId, int randomCount);

    TeamStatisticVO queryTeamStatisticsByActivityId(Long activityId, String goodsId);
}
