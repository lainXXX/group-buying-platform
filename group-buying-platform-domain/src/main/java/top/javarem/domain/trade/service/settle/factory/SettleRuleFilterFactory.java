package top.javarem.domain.trade.service.settle.factory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import top.javarem.domain.trade.model.entity.GroupBuyingTeamEntity;
import top.javarem.domain.trade.model.entity.MarketPayOrderEntity;
import top.javarem.domain.trade.model.entity.PaySuccessEntity;
import top.javarem.domain.trade.model.entity.SettleRuleFilterBackEntity;
import top.javarem.domain.trade.service.settle.filter.EndRuleFilter;
import top.javarem.domain.trade.service.settle.filter.OutTradeNoRuleFilter;
import top.javarem.domain.trade.service.settle.filter.SCBlacklistRuleFilter;
import top.javarem.domain.trade.service.settle.filter.SettleTimeRuleFilter;
import top.javarem.types.design.framework.link.model2.BusinessLinkedList;
import top.javarem.types.design.framework.link.model2.ChainArmory;

/**
 * @Author: rem
 * @Date: 2025/03/19/20:29
 * @Description: 结算规则过滤工厂
 */
@Service
public class SettleRuleFilterFactory {

    @Bean("settleRuleFilter")
    public BusinessLinkedList<PaySuccessEntity, DynamicContext, SettleRuleFilterBackEntity> settleRuleFilter(SCBlacklistRuleFilter scBlacklistRuleFilter,
                                                                                                             OutTradeNoRuleFilter outTradeNoRuleFilter,
                                                                                                             SettleTimeRuleFilter settleTimeRuleFilter,
                                                                                                             EndRuleFilter endRuleFilter
    ) {

        ChainArmory<PaySuccessEntity, DynamicContext, SettleRuleFilterBackEntity> settleRuleFilter = new ChainArmory<>("settleRuleFilter",
                scBlacklistRuleFilter,
                outTradeNoRuleFilter,
                settleTimeRuleFilter,
                endRuleFilter);
        return settleRuleFilter.getLogicLink();

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DynamicContext {

        private MarketPayOrderEntity marketPayOrderEntity;

        private GroupBuyingTeamEntity groupBuyingTeamEntity;

    }

}
