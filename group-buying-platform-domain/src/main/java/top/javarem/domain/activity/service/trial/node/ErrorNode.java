package top.javarem.domain.activity.service.trial.node;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.javarem.domain.activity.model.entity.MarketProductEntity;
import top.javarem.domain.activity.model.entity.TrialBalanceEntity;
import top.javarem.domain.activity.service.trial.AbstractGroupBuyingSupport;
import top.javarem.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import top.javarem.types.common.GsonUtils;
import top.javarem.types.design.framework.tree.StrategyHandler;
import top.javarem.types.enums.ResponseCode;
import top.javarem.types.exception.AppException;

/**
 * @Author: rem
 * @Date: 2025/03/05/9:44
 * @Description: 异常兜底节点
 */
@Service
@Slf4j
public class ErrorNode extends AbstractGroupBuyingSupport<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> {

    @Override
    protected TrialBalanceEntity doApply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        log.info("拼团商品查询试算服务-MarketNode userId:{} requestParameter:{}", requestParameter.getUserId(), GsonUtils.gson.toJson(requestParameter));
        if (dynamicContext.getSkuVO() == null || dynamicContext.getGroupBuyingActivityDiscountVO() == null) {

            log.info("无营销配置 goodsId: {}", requestParameter.getGoodsId());
            throw new AppException(ResponseCode.E0002.getCode(), ResponseCode.E0002.getInfo());
        }
        return TrialBalanceEntity.builder().build();
    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return defaultStrategyHandler;
    }
}
