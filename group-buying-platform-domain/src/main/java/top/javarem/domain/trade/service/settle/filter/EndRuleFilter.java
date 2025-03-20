package top.javarem.domain.trade.service.settle.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.javarem.domain.trade.model.entity.GroupBuyingTeamEntity;
import top.javarem.domain.trade.model.entity.PaySuccessEntity;
import top.javarem.domain.trade.model.entity.SettleRuleFilterBackEntity;
import top.javarem.domain.trade.service.settle.factory.SettleRuleFilterFactory;
import top.javarem.types.design.framework.link.model2.handler.ILinkHandler;

/**
 * @Author: rem
 * @Date: 2025/03/19/20:28
 * @Description: 尾节点封装数据
 */
@Service
@Slf4j
public class EndRuleFilter implements ILinkHandler<PaySuccessEntity, SettleRuleFilterFactory.DynamicContext, SettleRuleFilterBackEntity> {

    @Override
    public SettleRuleFilterBackEntity apply(PaySuccessEntity requestParameter, SettleRuleFilterFactory.DynamicContext dynamicContext) throws Exception {
        log.info("交易结算服务- 尾节点封装数据 userId:{}, outTradeNo:{}", requestParameter.getUserId(), requestParameter.getOutTradeNo());
        // 获取上下文对象
        GroupBuyingTeamEntity groupBuyingTeamEntity = dynamicContext.getGroupBuyingTeamEntity();

        // 返回封装数据
        return SettleRuleFilterBackEntity.builder()
                .teamId(groupBuyingTeamEntity.getTeamId())
                .activityId(groupBuyingTeamEntity.getActivityId())
                .targetCount(groupBuyingTeamEntity.getTargetCount())
                .completeCount(groupBuyingTeamEntity.getCompleteCount())
                .lockCount(groupBuyingTeamEntity.getLockCount())
                .status(groupBuyingTeamEntity.getStatus())
                .validBeginTime(groupBuyingTeamEntity.getValidBeginTime())
                .validEndTime(groupBuyingTeamEntity.getValidEndTime())
                .notifyUrl(groupBuyingTeamEntity.getNotifyUrl())
                .build();
    }
}
