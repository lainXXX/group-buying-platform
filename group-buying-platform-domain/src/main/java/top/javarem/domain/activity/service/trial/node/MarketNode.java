package top.javarem.domain.activity.service.trial.node;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.javarem.domain.activity.model.entity.MarketProductEntity;
import top.javarem.domain.activity.model.entity.TrialBalanceEntity;
import top.javarem.domain.activity.model.vo.GroupBuyingActivityDiscountVO;
import top.javarem.domain.activity.model.vo.SkuVO;
import top.javarem.domain.activity.service.discount.IDiscountCalculateService;
import top.javarem.domain.activity.service.trial.AbstractGroupBuyingSupport;
import top.javarem.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import top.javarem.domain.activity.service.trial.thread.QueryGroupBuyingActivityDiscountThreadTask;
import top.javarem.domain.activity.service.trial.thread.QuerySkuVOFromDBThreadTask;
import top.javarem.types.common.gson.GsonUtils;
import top.javarem.types.design.framework.tree.StrategyHandler;
import top.javarem.types.enums.ResponseCode;
import top.javarem.types.exception.AppException;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: rem
 * @Date: 2025/02/26/16:45
 * @Description: 营销优惠节点
 */
@Service
@Slf4j
public class MarketNode extends AbstractGroupBuyingSupport<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> {

    @Resource
    private ThreadPoolExecutor threadPoolExecutor;
    @Resource
    private TagNode tagNode;
    @Resource
    private ErrorNode errorNode;
    @Resource
    private Map<String, IDiscountCalculateService> discountCalculateServiceGroup;

    @Override
    public void multiThread(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {

//        异步查询活动配置
        QueryGroupBuyingActivityDiscountThreadTask queryGroupBuyingActivityDiscountThreadTask = new QueryGroupBuyingActivityDiscountThreadTask(requestParameter.getSource(), requestParameter.getChannel(), requestParameter.getGoodsId(), repository);
        FutureTask<GroupBuyingActivityDiscountVO> groupBuyingActivityDiscountVOFutureTask = new FutureTask<>(queryGroupBuyingActivityDiscountThreadTask);
        threadPoolExecutor.execute(groupBuyingActivityDiscountVOFutureTask);
//        异步查询商品信息 - 在实际生产中，商品有同步库或者调用接口查询。这里暂时使用DB方式查询。
        QuerySkuVOFromDBThreadTask querySkuVOFromDBThreadTask = new QuerySkuVOFromDBThreadTask(requestParameter.getGoodsId(), repository);
        FutureTask<SkuVO> skuVOFutureTask = new FutureTask<>(querySkuVOFromDBThreadTask);
        threadPoolExecutor.execute(skuVOFutureTask);

        dynamicContext.setGroupBuyingActivityDiscountVO(groupBuyingActivityDiscountVOFutureTask.get(timeout, TimeUnit.MINUTES));
        dynamicContext.setSkuVO(skuVOFutureTask.get(timeout, TimeUnit.MINUTES));
        log.info("拼团活动查询试算服务-MarketNode userId:{} 异步线程加载数据[GroupBuyingActivityDiscountVO、 SkuVO] 完成", requestParameter.getUserId());
    }

    @Override
    public TrialBalanceEntity doApply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {

        log.info("拼团商品查询试算服务-MarketNode userId:{} requestParameter:{}", requestParameter.getUserId(), GsonUtils.gson.toJson(requestParameter));
//        1.从动态上下文中获取各种信息
        GroupBuyingActivityDiscountVO groupBuyingActivityDiscountVO = dynamicContext.getGroupBuyingActivityDiscountVO();
        if (groupBuyingActivityDiscountVO == null) {
            return router(requestParameter, dynamicContext);
        }
        SkuVO skuVO = dynamicContext.getSkuVO();
        GroupBuyingActivityDiscountVO.GroupBuyingDiscount groupBuyingDiscount = groupBuyingActivityDiscountVO.getGroupBuyingDiscount();
        if (skuVO == null || groupBuyingDiscount == null) {
            return router(requestParameter, dynamicContext);
        }
//        2.从商品的营销优惠计划中取出对应折扣服务
        IDiscountCalculateService discountCalculateService = discountCalculateServiceGroup.get(groupBuyingDiscount.getMarketingPlan());
        if (null == discountCalculateService) {
            log.info("不存在{}类型的折扣计算服务，支持类型为:{}", groupBuyingDiscount.getMarketingPlan(), GsonUtils.gson.toJson(discountCalculateServiceGroup.keySet()));
            throw new AppException(ResponseCode.E0001.getCode(), ResponseCode.E0001.getInfo());
        }
//        3.计算折扣优惠
        BigDecimal discountPrice = discountCalculateService.calculate(requestParameter.getUserId(), skuVO.getOriginalPrice(), groupBuyingDiscount);
//        4.填充折扣优惠后的价格到动态上下文信息中
        dynamicContext.setDiscountPrice(discountPrice);
//        5.路由传递到下一节点
        return router(requestParameter, dynamicContext);

    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {

//        如果传入的参数或者要传到下一节点的数据为null 则进入errorNode
        if (dynamicContext.getSkuVO() == null || dynamicContext.getGroupBuyingActivityDiscountVO() == null || dynamicContext.getDiscountPrice() == null) {
            return errorNode;
        }
        return tagNode;

    }
}
