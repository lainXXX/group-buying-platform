package top.javarem.domain.trade.service.settle.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.javarem.domain.trade.adapter.repository.ITradeRepository;
import top.javarem.domain.trade.model.entity.GroupBuyingTeamEntity;
import top.javarem.domain.trade.model.entity.MarketPayOrderEntity;
import top.javarem.domain.trade.model.entity.PaySuccessEntity;
import top.javarem.domain.trade.model.entity.SettleRuleFilterBackEntity;
import top.javarem.domain.trade.service.settle.factory.SettleRuleFilterFactory;
import top.javarem.types.design.framework.link.model2.handler.ILinkHandler;
import top.javarem.types.enums.ResponseCode;
import top.javarem.types.exception.AppException;

import javax.annotation.Resource;

/**
 * @Author: rem
 * @Date: 2025/03/19/20:27
 * @Description: 交易时间有效性过滤
 */
@Service
@Slf4j
public class SettleTimeRuleFilter implements ILinkHandler<PaySuccessEntity, SettleRuleFilterFactory.DynamicContext, SettleRuleFilterBackEntity> {

    @Resource
    private ITradeRepository repository;

    @Override
    public SettleRuleFilterBackEntity apply(PaySuccessEntity requestParameter, SettleRuleFilterFactory.DynamicContext dynamicContext) throws Exception {
        log.info("交易结算服务- 时间有效性校验 userId:{}, outTradeNo:{}", requestParameter.getUserId(), requestParameter.getOutTradeNo());
        //        2.获取拼团组队信息
        MarketPayOrderEntity marketPayOrderEntity = dynamicContext.getMarketPayOrderEntity();
        GroupBuyingTeamEntity groupBuyingTeamEntity = repository.queryGroupBuyingTeam(marketPayOrderEntity.getTeamId(), marketPayOrderEntity.getActivityId());
        dynamicContext.setGroupBuyingTeamEntity(groupBuyingTeamEntity);
        if (requestParameter.getOutTradeTime().before(groupBuyingTeamEntity.getValidBeginTime()) || requestParameter.getOutTradeTime().after(groupBuyingTeamEntity.getValidEndTime())) {
            log.error("交易结算服务- 时间有效性校验 交易时间不在拼团有效时间内 userId:{}, outTradeNo:{}", requestParameter.getUserId(), requestParameter.getOutTradeNo());
            throw new AppException(ResponseCode.E0010);
        }
        return next(requestParameter, dynamicContext);
    }
}
