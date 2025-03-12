package top.javarem.domain.trade.service.factory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import top.javarem.domain.trade.model.entity.GroupBuyingActivityEntity;
import top.javarem.domain.trade.model.entity.TradeRuleFilterBackEntity;
import top.javarem.domain.trade.model.entity.TradeRuleFilterEntity;
import top.javarem.domain.trade.service.impl.ActivityValidFilter;
import top.javarem.domain.trade.service.impl.UserPartakeLimitFilter;
import top.javarem.types.design.framework.link.model2.BusinessLinkedList;
import top.javarem.types.design.framework.link.model2.ChainArmory;

import javax.annotation.Resource;

/**
 * @Author: rem
 * @Date: 2025/03/11/19:53
 * @Description:
 */
@Service
public class TradeRuleFilterFactory {

    @Resource
    private ActivityValidFilter activityValidFilter;
    @Resource
    private UserPartakeLimitFilter userPartakeLimitFilter;

    @Bean("tradeRuleFilter")
    public BusinessLinkedList<TradeRuleFilterEntity, TradeRuleFilterFactory.DynamicContext, TradeRuleFilterBackEntity> getTradeRuleFilter() {

        ChainArmory<TradeRuleFilterEntity, DynamicContext, TradeRuleFilterBackEntity> tradeRuleFilter = new ChainArmory<>("tradeRuleFilter", activityValidFilter, userPartakeLimitFilter);
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
