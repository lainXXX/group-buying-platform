package top.javarem.domain.trade.service.settle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.javarem.domain.trade.adapter.port.ITradePort;
import top.javarem.domain.trade.adapter.repository.ITradeRepository;
import top.javarem.domain.trade.model.aggregate.GroupBuyingSettleOrderAggregate;
import top.javarem.domain.trade.model.entity.*;
import top.javarem.domain.trade.service.ITradeSettleOrderService;
import top.javarem.domain.trade.service.settle.factory.SettleRuleFilterFactory;
import top.javarem.types.common.GsonUtils;
import top.javarem.types.design.framework.link.model2.BusinessLinkedList;
import top.javarem.types.enums.NotifyTaskHttpEnumVO;
import top.javarem.types.enums.ResponseCode;
import top.javarem.types.exception.AppException;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: rem
 * @Date: 2025/03/13/15:27
 * @Description: 交易结算订单服务实现
 */
@Service
@Slf4j
public class TradeTradeSettleOrderService implements ITradeSettleOrderService {

    @Resource
    private ITradeRepository repository;
    @Resource
    private BusinessLinkedList<PaySuccessEntity, SettleRuleFilterFactory.DynamicContext, SettleRuleFilterBackEntity> settleRuleFilter;
    @Resource
    private ITradePort port;
    @Resource
    private ThreadPoolExecutor executor;


    @Override
    public TradePaySettleEntity settlePayOrder(PaySuccessEntity paySuccessEntity) throws Exception {
        //        1.结算规则过滤
        SettleRuleFilterBackEntity settleRuleFilterBackEntity = settleRuleFilter.apply(paySuccessEntity, new SettleRuleFilterFactory.DynamicContext());
//        2.构建聚合
        GroupBuyingTeamEntity groupBuyingTeamEntity = GroupBuyingTeamEntity.builder()
                .teamId(settleRuleFilterBackEntity.getTeamId())
                .activityId(settleRuleFilterBackEntity.getActivityId())
                .targetCount(settleRuleFilterBackEntity.getTargetCount())
                .completeCount(settleRuleFilterBackEntity.getCompleteCount())
                .lockCount(settleRuleFilterBackEntity.getLockCount())
                .status(settleRuleFilterBackEntity.getStatus())
                .validBeginTime(settleRuleFilterBackEntity.getValidBeginTime())
                .validEndTime(settleRuleFilterBackEntity.getValidEndTime())
                .notifyUrl(settleRuleFilterBackEntity.getNotifyUrl())
                .build();

        GroupBuyingSettleOrderAggregate groupBuyingSettleOrderAggregate = GroupBuyingSettleOrderAggregate.builder()
                .userEntity(UserEntity.builder().userId(paySuccessEntity.getUserId()).build())
                .groupBuyingTeamEntity(groupBuyingTeamEntity)
                .paySuccessEntity(paySuccessEntity)
                .build();
//        3.更新订单详情状态为交易完成 更新拼团组队进度
        repository.settleOrder(groupBuyingSettleOrderAggregate);

//        if (notifyTaskEntity != null) {
//            Map<String, Integer> notifyResultMap = execNotifyTaskJob(Collections.singletonList(notifyTaskEntity));
//            log.info("回调通知拼团完结 result:{}", GsonUtils.gson.toJson(notifyResultMap));
//        }

        return TradePaySettleEntity.builder()
                .userId(paySuccessEntity.getUserId())
                .activityId(groupBuyingTeamEntity.getActivityId())
                .teamId(groupBuyingTeamEntity.getTeamId())
                .outTradeNo(paySuccessEntity.getOutTradeNo())
                .channel(paySuccessEntity.getChannel())
                .source(paySuccessEntity.getSource())
                .build();

    }

    @Override
    public Map<String, Integer> execNotifyTaskJob(String teamId) throws Exception {

        log.info("拼团交易-执行交易回调任务 指定teamId:{}", teamId);
        List<NotifyTaskEntity> notifyTaskEntityList = repository.queryUnExecutedNotifyTask(teamId);
        return execNotifyTaskJob(notifyTaskEntityList);

    }

    @Override
    public Map<String, Integer> execNotifyTaskJob() throws Exception {
        log.info("拼团交易-执行交易回调任务");
        List<NotifyTaskEntity> notifyTaskEntityList = repository.queryUnExecutedNotifyTask();
        return execNotifyTaskJob(notifyTaskEntityList);
    }

    private Map<String, Integer> execNotifyTaskJob(List<NotifyTaskEntity> notifyTaskEntityList) {
        int successCount = 0, errorCount = 0, retryCount = 0;
            for (NotifyTaskEntity notifyTaskEntity : notifyTaskEntityList) {
                String response = port.groupBuyingNotifyTask(notifyTaskEntity);
                if (NotifyTaskHttpEnumVO.SUCCESS.getCode().equals(response)) {
                    int updateCount = repository.updateNotifyTaskStatusSuccess(notifyTaskEntity.getTeamId());
                    if (updateCount == 1) {
                        successCount++;
                    }
                } else if (NotifyTaskHttpEnumVO.ERROR.getCode().equals(response)) {
                    if (notifyTaskEntity.getNotifyCount() > 4) {
                        int updateCount = repository.updateNotifyTaskStatusError(notifyTaskEntity.getTeamId());
                        if (updateCount == 1) {
                            errorCount++;
                        }
                    } else {
                        int updateCount = repository.updateNotifyTaskStatusRetry(notifyTaskEntity.getTeamId());
                        if (updateCount == 1) {
                            retryCount++;
                        }
                    }

                }
        }
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("waitCount", notifyTaskEntityList.size());
        resultMap.put("successCount", successCount);
        resultMap.put("errorCount", errorCount);
        resultMap.put("retryCount", retryCount);

        return resultMap;
    }
}
