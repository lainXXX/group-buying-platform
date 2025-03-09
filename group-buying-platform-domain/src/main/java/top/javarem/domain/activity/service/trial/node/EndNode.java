package top.javarem.domain.activity.service.trial.node;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.javarem.domain.activity.model.entity.MarketProductEntity;
import top.javarem.domain.activity.model.entity.TrialBalanceEntity;
import top.javarem.domain.activity.model.vo.GroupBuyingActivityDiscountVO;
import top.javarem.domain.activity.model.vo.SkuVO;
import top.javarem.domain.activity.service.trial.AbstractGroupBuyingSupport;
import top.javarem.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import top.javarem.types.common.GsonUtils;
import top.javarem.types.design.framework.tree.StrategyHandler;


/**
 * @Author: rem
 * @Date: 2025/02/26/16:46
 * @Description: 结束节点
 */
@Service
@Slf4j
public class EndNode extends AbstractGroupBuyingSupport<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> {
    @Override
    public TrialBalanceEntity doApply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        log.info("拼团商品查询试算服务-EndNode userId:{} requestParameter:{}", requestParameter.getUserId(), GsonUtils.gson.toJson(requestParameter));
        GroupBuyingActivityDiscountVO groupBuyingActivityDiscountVO = dynamicContext.getGroupBuyingActivityDiscountVO();
        SkuVO skuVO = dynamicContext.getSkuVO();

        return TrialBalanceEntity.builder()
                .goodsId(skuVO.getGoodsId())
                .goodsName(skuVO.getGoodsName())
                .originalPrice(skuVO.getOriginalPrice())
                .discountPrice(dynamicContext.getDiscountPrice())
                .targetCount(groupBuyingActivityDiscountVO.getTarget())
                .startTime(groupBuyingActivityDiscountVO.getBeginTime())
                .endTime(groupBuyingActivityDiscountVO.getEndTime())
                .isVisible(dynamicContext.isVisible())
                .isEnable(dynamicContext.isEnable())
                .build();
    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return defaultStrategyHandler;
    }
}
