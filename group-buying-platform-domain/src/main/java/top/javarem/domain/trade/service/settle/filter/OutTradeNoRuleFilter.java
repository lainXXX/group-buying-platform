package top.javarem.domain.trade.service.settle.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.javarem.domain.trade.adapter.repository.ITradeRepository;
import top.javarem.domain.trade.model.entity.MarketPayOrderEntity;
import top.javarem.domain.trade.model.entity.PaySuccessEntity;
import top.javarem.domain.trade.model.entity.SettleRuleFilterBackEntity;
import top.javarem.domain.trade.service.settle.factory.SettleRuleFilterFactory;
import top.javarem.types.design.framework.link.model2.handler.ILinkHandler;
import top.javarem.types.enums.ResponseCode;
import top.javarem.types.exception.AppException;

import javax.annotation.Resource;

/** ooo
 * @Author: rem
 * @Date: 2025/03/19/20:26
 * @Description: 外部订单有效性过滤
 */
@Service
@Slf4j
public class OutTradeNoRuleFilter implements ILinkHandler<PaySuccessEntity, SettleRuleFilterFactory.DynamicContext, SettleRuleFilterBackEntity> {

    @Resource
    private ITradeRepository repository;
    @Override
    public SettleRuleFilterBackEntity apply(PaySuccessEntity requestParameter, SettleRuleFilterFactory.DynamicContext dynamicContext) throws Exception {
//        1.通过外部透传ID查询订单是否存在
        log.info("交易结算服务- 外部订单有效性校验 userId:{}, outTradeNo:{}", requestParameter.getUserId(), requestParameter.getOutTradeNo());
        MarketPayOrderEntity marketPayOrderEntity = repository.queryNoPayOrderByOutTradeNo(requestParameter.getUserId(), requestParameter.getOutTradeNo());
        if (marketPayOrderEntity == null) {
            log.info("用户拼团订单无效 userId:{}, outTradeNo:{}", requestParameter.getUserId(), requestParameter.getOutTradeNo());
            throw new AppException(ResponseCode.E0008);
        }
        dynamicContext.setMarketPayOrderEntity(marketPayOrderEntity);
        return next(requestParameter, dynamicContext);
    }
}
