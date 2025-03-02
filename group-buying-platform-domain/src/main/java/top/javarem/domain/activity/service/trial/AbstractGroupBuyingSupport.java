package top.javarem.domain.activity.service.trial;

import top.javarem.domain.activity.adapter.repository.IActivityRepository;
import top.javarem.types.design.framework.tree.AbstractMultiThreadStrategyRouter;

import javax.annotation.Resource;

/**
 * @Author: rem
 * @Date: 2025/02/26/16:32
 * @Description:
 */
public abstract class AbstractGroupBuyingSupport<MarketProductEntity, DynamicContext, TrialBalanceEntity> extends AbstractMultiThreadStrategyRouter<MarketProductEntity, DynamicContext, TrialBalanceEntity> {

    protected long timeout = 500;
    @Resource
    protected IActivityRepository repository;

    @Override
    protected void multiThread(MarketProductEntity requestParameter, DynamicContext dynamicContext) throws Exception {

        TrialBalanceEntity apply = defaultStrategyHandler.apply(requestParameter, dynamicContext);


    }
}
