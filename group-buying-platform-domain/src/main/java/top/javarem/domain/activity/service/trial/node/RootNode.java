package top.javarem.domain.activity.service.trial.node;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import top.javarem.domain.activity.model.entity.MarketProductEntity;
import top.javarem.domain.activity.model.entity.TrialBalanceEntity;
import top.javarem.domain.activity.service.trial.AbstractGroupBuyingSupport;
import top.javarem.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import top.javarem.types.design.framework.tree.StrategyHandler;
import top.javarem.types.enums.ResponseCode;
import top.javarem.types.exception.AppException;

import javax.annotation.Resource;

/**
 * @Author: rem
 * @Date: 2025/02/26/16:42
 * @Description: 根节点
 */
@Service
@Slf4j
public class RootNode extends AbstractGroupBuyingSupport<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> {

    @Resource
    private SwitchNode switchNode;

    @Override
    public TrialBalanceEntity doApply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        if (StringUtils.isBlank(requestParameter.getUserId()) ||
                StringUtils.isBlank(requestParameter.getGoodsId()) ||
                StringUtils.isBlank(requestParameter.getSource()) ||
                StringUtils.isBlank(requestParameter.getChannel())
        )
            throw new AppException(ResponseCode.ILLEGAL_PARAMETER.getCode(), ResponseCode.ILLEGAL_PARAMETER.getInfo());
        return router(requestParameter, dynamicContext);
    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return switchNode;
    }
}
