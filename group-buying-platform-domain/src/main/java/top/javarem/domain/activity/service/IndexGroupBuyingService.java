package top.javarem.domain.activity.service;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.javarem.domain.activity.adapter.repository.IActivityRepository;
import top.javarem.domain.activity.model.entity.GroupBuyingTeamOrderDetailEntity;
import top.javarem.domain.activity.model.entity.MarketProductEntity;
import top.javarem.domain.activity.model.entity.TrialBalanceEntity;
import top.javarem.domain.activity.model.vo.TeamStatisticVO;
import top.javarem.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import top.javarem.types.design.framework.tree.StrategyHandler;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: rem
 * @Date: 2025/02/26/16:56
 * @Description: 首页营销服务
 */
@Service
public class IndexGroupBuyingService implements IIndexGroupBuyingService {


    @Resource
    private DefaultActivityStrategyFactory defaultActivityStrategyFactory;
    @Resource
    private IActivityRepository repository;

    @Override
    public TrialBalanceEntity indexMarketTrial(MarketProductEntity marketProductEntity) throws Exception {

        StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> strategyHandler = defaultActivityStrategyFactory.strategyHandler();

        TrialBalanceEntity trialBalanceEntity = strategyHandler.apply(marketProductEntity, new DefaultActivityStrategyFactory.DynamicContext());

        return trialBalanceEntity;



    }

    @Override
    public List<GroupBuyingTeamOrderDetailEntity> queryInProgressTeamList(Long activityId, String userId, int ownerCount, int randomCount) {

        List<GroupBuyingTeamOrderDetailEntity> groupBuyingTeamOrderDetailEntities = new ArrayList<>();
        if (0 != ownerCount) {
            List<GroupBuyingTeamOrderDetailEntity> ownerDetailList = repository.queryOwnerGroupBuyingTeamOrderDetailList(userId, activityId, ownerCount);
            if (!CollectionUtils.isEmpty(ownerDetailList)) {
                groupBuyingTeamOrderDetailEntities.addAll(ownerDetailList);
            }
        }

        if (0 != randomCount) {
            List<GroupBuyingTeamOrderDetailEntity> othersDetailList = repository.queryOthersGroupBuyingTeamOrderDetailList(userId, activityId, randomCount);
            if (!CollectionUtils.isEmpty(othersDetailList)) {
                groupBuyingTeamOrderDetailEntities.addAll(othersDetailList);
            }
        }

        return groupBuyingTeamOrderDetailEntities;

    }

    @Override
    public TeamStatisticVO queryTeamStatisticsByActivityId(Long activityId, String goodsId) {

        return repository.queryTeamStatisticsByActivityId(activityId, goodsId);

    }


}
