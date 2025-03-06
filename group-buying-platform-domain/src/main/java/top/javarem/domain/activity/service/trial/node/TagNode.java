package top.javarem.domain.activity.service.trial.node;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import top.javarem.domain.activity.model.entity.MarketProductEntity;
import top.javarem.domain.activity.model.entity.TrialBalanceEntity;
import top.javarem.domain.activity.model.vo.GroupBuyingActivityDiscountVO;
import top.javarem.domain.activity.service.trial.AbstractGroupBuyingSupport;
import top.javarem.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import top.javarem.types.design.framework.tree.StrategyHandler;

import javax.annotation.Resource;

/**
 * @Author: rem
 * @Date: 2025/03/06/14:12
 * @Description: 标签节点服务实现
 */
@Service
public class TagNode extends AbstractGroupBuyingSupport<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> {

    @Resource
    private EndNode endNode;

    /**
     * 为了检测活动商品有没有配置标签限制 如果没有配置则这一节点直接放行拼团限制
     * 如果配置了则检测用户是否属于活动商品的标签范围内 如果在则放行标签拼团限制
     * 其实就是综合活动商品的标签限制和用户属不属于目标人群 来判断活动是否可见、 是否可参与 两者满足其一就放行
     */
    @Override
    protected TrialBalanceEntity doApply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {

//        1.获取拼团配置
        GroupBuyingActivityDiscountVO groupBuyingActivityDiscountVO = dynamicContext.getGroupBuyingActivityDiscountVO();
        String tagId = groupBuyingActivityDiscountVO.getTagId();

//        2.判断活动是否配置标签
        if (StringUtils.isBlank(tagId)) {

            dynamicContext.setVisible(true);
            dynamicContext.setEnable(true);
            return router(requestParameter, dynamicContext);
        }
//        3.判断用户是否属于该标签人群 visible、enable 如果值为 ture 则表示没有配置拼团限制，那么就直接保证为 true 即可
        boolean isWithin = repository.isTagCrowdRange(tagId, requestParameter.getUserId());
        boolean isVisible = groupBuyingActivityDiscountVO.isVisible();
        boolean isEnable = groupBuyingActivityDiscountVO.isEnable();
        dynamicContext.setVisible(isVisible || isWithin);
        dynamicContext.setEnable(isEnable || isWithin);

        return router(requestParameter, dynamicContext);

    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return endNode;
    }
}
