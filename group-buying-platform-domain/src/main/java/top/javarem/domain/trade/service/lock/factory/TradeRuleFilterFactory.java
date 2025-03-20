package top.javarem.domain.trade.service.lock.factory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import top.javarem.domain.trade.model.entity.GroupBuyingActivityEntity;
import top.javarem.domain.trade.model.entity.TradeRuleFilterBackEntity;
import top.javarem.domain.trade.model.entity.TradeRuleFilterEntity;
import top.javarem.domain.trade.service.lock.impl.ActivityValidFilter;
import top.javarem.domain.trade.service.lock.impl.UserPartakeLimitFilter;
import top.javarem.types.design.framework.link.model2.BusinessLinkedList;
import top.javarem.types.design.framework.link.model2.ChainArmory;

/**
 * @Author: rem
 * @Date: 2025/03/11/19:53
 * @Description:
 */
@Service
public class TradeRuleFilterFactory {


    @Bean("tradeLockRuleFilter")
    public BusinessLinkedList<TradeRuleFilterEntity, TradeRuleFilterFactory.DynamicContext, TradeRuleFilterBackEntity> tradeLockRuleFilter(ActivityValidFilter activityValidFilter, UserPartakeLimitFilter userPartakeLimitFilter) {

        ChainArmory<TradeRuleFilterEntity, DynamicContext, TradeRuleFilterBackEntity> tradeRuleFilter = new ChainArmory<>("tradeLockRuleFilter", activityValidFilter, userPartakeLimitFilter);
        return tradeRuleFilter.getLogicLink();

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DynamicContext {

        private GroupBuyingActivityEntity groupBuyingActivityEntity;


    }

}
