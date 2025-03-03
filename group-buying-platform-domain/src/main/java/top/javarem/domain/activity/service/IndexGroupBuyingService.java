package top.javarem.domain.activity.service;

import org.springframework.stereotype.Service;
import top.javarem.domain.activity.model.entity.MarketProductEntity;
import top.javarem.domain.activity.model.entity.TrialBalanceEntity;
import top.javarem.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import top.javarem.types.design.framework.tree.StrategyHandler;

import javax.annotation.Resource;

/**
 * @Author: rem
 * @Date: 2025/02/26/16:56
 * @Description: 首页营销服务
 */
@Service
public class IndexGroupBuyingService implements IIndexGroupBuyingService {


    @Resource
    private DefaultActivityStrategyFactory defaultActivityStrategyFactory;

    @Override
    public TrialBalanceEntity indexMarketTrial(MarketProductEntity marketProductEntity) throws Exception {

        StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> strategyHandler = defaultActivityStrategyFactory.strategyHandler();

        TrialBalanceEntity trialBalanceEntity = strategyHandler.apply(marketProductEntity, new DefaultActivityStrategyFactory.DynamicContext());

        return trialBalanceEntity;



    }
}
