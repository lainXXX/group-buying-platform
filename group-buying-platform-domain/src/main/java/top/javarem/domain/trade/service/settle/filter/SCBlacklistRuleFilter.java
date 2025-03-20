package top.javarem.domain.trade.service.settle.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.javarem.domain.trade.adapter.repository.ITradeRepository;
import top.javarem.domain.trade.model.entity.PaySuccessEntity;
import top.javarem.domain.trade.model.entity.SettleRuleFilterBackEntity;
import top.javarem.domain.trade.service.settle.factory.SettleRuleFilterFactory;
import top.javarem.types.design.framework.link.model2.handler.ILinkHandler;
import top.javarem.types.enums.ResponseCode;
import top.javarem.types.exception.AppException;

import javax.annotation.Resource;

/**
 * @Author: rem
 * @Date: 2025/03/19/20:25
 * @Description: sc黑名单规则过滤 [渠道来源 商品下架则拦截]
 */
@Service
@Slf4j
public class SCBlacklistRuleFilter implements ILinkHandler<PaySuccessEntity, SettleRuleFilterFactory.DynamicContext, SettleRuleFilterBackEntity> {

    @Resource
    private ITradeRepository repository;

    @Override
    public SettleRuleFilterBackEntity apply(PaySuccessEntity requestParameter, SettleRuleFilterFactory.DynamicContext dynamicContext) throws Exception {
        log.info("交易结算服务- sc黑名单校验 userId:{}, outTradeNo:{}", requestParameter.getUserId(), requestParameter.getOutTradeNo());
        boolean isIntercept = repository.scBlacklistIntercept(requestParameter.getSource(), requestParameter.getChannel());
        if (isIntercept) {
            log.error("{}{} 渠道黑名单拦截", requestParameter.getSource(), requestParameter.getChannel());
            throw new AppException(ResponseCode.E0009);
        }
        return next(requestParameter, dynamicContext);
    }
}
