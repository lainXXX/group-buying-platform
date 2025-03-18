package top.javarem.domain.trade.service.lock.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.javarem.domain.trade.adapter.repository.ITradeRepository;
import top.javarem.domain.trade.model.entity.GroupBuyingActivityEntity;
import top.javarem.domain.trade.model.entity.TradeRuleFilterBackEntity;
import top.javarem.domain.trade.model.entity.TradeRuleFilterEntity;
import top.javarem.domain.trade.service.lock.factory.TradeRuleFilterFactory;
import top.javarem.types.design.framework.link.model2.handler.ILinkHandler;
import top.javarem.types.enums.ResponseCode;
import top.javarem.types.exception.AppException;

import javax.annotation.Resource;

/**
 * @Author: rem
 * @Date: 2025/03/11/20:05
 * @Description: 用户参与限制过滤
 */
@Service
@Slf4j
public class UserPartakeLimitFilter implements ILinkHandler<TradeRuleFilterEntity, TradeRuleFilterFactory.DynamicContext, TradeRuleFilterBackEntity> {

    @Resource
    private ITradeRepository repository;

    @Override
    public TradeRuleFilterBackEntity apply(TradeRuleFilterEntity requestParameter, TradeRuleFilterFactory.DynamicContext dynamicContext) throws Exception {
        Long activityId = requestParameter.getActivityId();
        String userId = requestParameter.getUserId();
        GroupBuyingActivityEntity groupBuyingActivityEntity = dynamicContext.getGroupBuyingActivityEntity();
        log.info("交易规则过滤- [用户参与限制过滤服务] userId:{}, activityId;{}", userId, activityId);
        Integer userPartakeCount = repository.queryUserActivityPartakeCount(activityId, userId);
        if (userPartakeCount >= groupBuyingActivityEntity.getTakeLimitCount()) {
            log.error("用户参与限制过滤服务 用户拼团次数已达上限 userId:{}, activityId", userId, activityId);
            throw new AppException(ResponseCode.E0007);
        }
        return TradeRuleFilterBackEntity.builder()
                .userPartakeCount(userPartakeCount)
                .build();
    }
}
