package top.javarem.domain.activity.service.trial.node;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.javarem.domain.activity.model.entity.MarketProductEntity;
import top.javarem.domain.activity.model.entity.TrialBalanceEntity;
import top.javarem.domain.activity.model.vo.GroupBuyingActivityDiscountVO;
import top.javarem.domain.activity.model.vo.SkuVO;
import top.javarem.domain.activity.service.trial.AbstractGroupBuyingSupport;
import top.javarem.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import top.javarem.domain.activity.service.trial.thread.QueryGroupBuyingActivityDiscountThreadTask;
import top.javarem.domain.activity.service.trial.thread.QuerySkuVOFromDBThreadTask;
import top.javarem.types.common.gson.GsonUtils;
import top.javarem.types.design.framework.tree.StrategyHandler;

import javax.annotation.Resource;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: rem
 * @Date: 2025/02/26/16:45
 * @Description:
 */
@Service
@Slf4j
public class MarketNode extends AbstractGroupBuyingSupport<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> {

    @Resource
    private ThreadPoolExecutor threadPoolExecutor;
    @Resource
    private EndNode endNode;

    @Override
    protected void multiThread(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {

//        异步查询活动配置
        QueryGroupBuyingActivityDiscountThreadTask queryGroupBuyingActivityDiscountThreadTask = new QueryGroupBuyingActivityDiscountThreadTask(requestParameter.getSource(), requestParameter.getChannel(), repository);
        FutureTask<GroupBuyingActivityDiscountVO> groupBuyingActivityDiscountVOFutureTask = new FutureTask<>(queryGroupBuyingActivityDiscountThreadTask);
        threadPoolExecutor.execute(groupBuyingActivityDiscountVOFutureTask);
//        异步查询商品信息 - 在实际生产中，商品有同步库或者调用接口查询。这里暂时使用DB方式查询。
        QuerySkuVOFromDBThreadTask querySkuVOFromDBThreadTask = new QuerySkuVOFromDBThreadTask(requestParameter.getGoodsId(), repository);
        FutureTask<SkuVO> skuVOFutureTask = new FutureTask<>(querySkuVOFromDBThreadTask);
        threadPoolExecutor.execute(skuVOFutureTask);

        dynamicContext.setGroupBuyingActivityDiscountVO(groupBuyingActivityDiscountVOFutureTask.get());
        dynamicContext.setSkuVO(skuVOFutureTask.get());
        log.info("拼团活动查询试算服务-MarketNode userId:{} 异步线程加载数据[GroupBuyingActivityDiscountVO、 SkuVO] 完成", requestParameter.getUserId());
    }

    @Override
    public TrialBalanceEntity doApply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {

        log.info("拼团商品查询试算服务-MarketNode userId:{} requestParameter:{}", requestParameter.getUserId(), GsonUtils.gson.toJson(requestParameter));
//        TODO拼团优惠试算

        return router(requestParameter, dynamicContext);

    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return endNode;
    }
}
