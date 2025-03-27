package top.javarem.domain.activity.service;

import top.javarem.domain.activity.model.entity.GroupBuyingTeamOrderDetailEntity;
import top.javarem.domain.activity.model.entity.MarketProductEntity;
import top.javarem.domain.activity.model.entity.TrialBalanceEntity;
import top.javarem.domain.activity.model.vo.TeamStatisticVO;

import java.util.List;

/**
 * @Author: rem
 * @Date: 2025/02/26/16:56
 * @Description: 首页营销服务接口
 */
public interface IIndexGroupBuyingService {

    /**
     * 营销优惠试算
     * @param marketProduct 营销产品实体类
     * @return 试算结果实体对象（给用户展示拼团可获得的优惠信息）
     * @throws Exception
     */
    TrialBalanceEntity indexMarketTrial(MarketProductEntity marketProduct) throws Exception;

    List<GroupBuyingTeamOrderDetailEntity> queryInProgressTeamList(Long activityId, String userId, int ownerCount, int randomCount);

    /**
     * 查询组队统计信息
     *
     * @param activityId 活动ID
     * @param goodsId 商品ID
     * @return 组队统计信息
     */
    TeamStatisticVO queryTeamStatisticsByActivityId(Long activityId, String goodsId);
}
