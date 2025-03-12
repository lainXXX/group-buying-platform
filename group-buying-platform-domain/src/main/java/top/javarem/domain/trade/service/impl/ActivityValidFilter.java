package top.javarem.domain.trade.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.javarem.domain.trade.adapter.repository.ITradeRepository;
import top.javarem.domain.trade.model.entity.GroupBuyingActivityEntity;
import top.javarem.domain.trade.model.entity.TradeRuleFilterBackEntity;
import top.javarem.domain.trade.model.entity.TradeRuleFilterEntity;
import top.javarem.domain.trade.service.factory.TradeRuleFilterFactory;
import top.javarem.types.design.framework.link.model2.handler.ILinkHandler;
import top.javarem.types.enums.ResponseCode;
import top.javarem.types.exception.AppException;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author: rem
 * @Date: 2025/03/11/19:50
 * @Description: 活动有效性过滤服务
 */
@Service
@Slf4j
public class ActivityValidFilter implements ILinkHandler<TradeRuleFilterEntity, TradeRuleFilterFactory.DynamicContext, TradeRuleFilterBackEntity> {

    @Resource
    private ITradeRepository repository;

    @Override
    public TradeRuleFilterBackEntity apply(TradeRuleFilterEntity requestParameter, TradeRuleFilterFactory.DynamicContext dynamicContext) throws Exception {
        Long activityId = requestParameter.getActivityId();
        String userId = requestParameter.getUserId();
        log.info("交易规则过滤- [活动有效性校验服务] userId:{}, activityId;{}", userId, activityId);
        GroupBuyingActivityEntity groupBuyingActivityEntity = repository.queryActivity(activityId);
        if (1 != groupBuyingActivityEntity.getStatus()) {
            log.info("活动有效性校验服务 活动未生效 activityId:{}", activityId);
            throw new AppException(ResponseCode.E0005);
        }
        Date today = new Date();
        if (!today.after(groupBuyingActivityEntity.getBeginTime()) || !today.before(groupBuyingActivityEntity.getEndTime())) {
            log.info("活动有效性校验服务 非可参与时间范围 activityId:{}", activityId);
            throw new AppException(ResponseCode.E0006);
        }

        dynamicContext.setGroupBuyingActivityEntity(groupBuyingActivityEntity);
        return next(requestParameter, dynamicContext);

    }
}
