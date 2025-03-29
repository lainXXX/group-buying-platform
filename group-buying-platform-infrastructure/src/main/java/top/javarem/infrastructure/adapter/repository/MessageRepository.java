package top.javarem.infrastructure.adapter.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import top.javarem.domain.message.adapter.repository.IMessageRepository;
import top.javarem.domain.message.model.entity.MessageEntity;
import top.javarem.domain.message.model.vo.MessageStatusEnumsVO;
import top.javarem.domain.trade.adapter.repository.ITradeRepository;
import top.javarem.infrastructure.adapter.port.settle.ISettleStrategyPort;
import top.javarem.infrastructure.adapter.port.settle.factory.DefaultSettleStrategyFactory;
import top.javarem.infrastructure.dao.po.MessageTask;
import top.javarem.infrastructure.dao.service.MessageService;
import top.javarem.types.enums.NotifyTaskHttpEnumVO;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: rem
 * @Date: 2025/03/28/12:41
 * @Description:
 */
@Repository
@Slf4j
public class MessageRepository implements IMessageRepository {

    @Resource
    private MessageService messageService;
    @Resource
    private DefaultSettleStrategyFactory settleStrategyFactory;
    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    @Override
    @Transactional
    public void distributeMessage(MessageEntity messageEntity, boolean isAsync) {
//        1.幂等性检查 保证消息唯一性
        boolean isExists = messageService.isExits(messageEntity.getMsgId());
        if (isExists) {
            return;
        }
//        2，插入message消息
        MessageTask messageTask = buildMessage(messageEntity);
        messageService.save(messageTask);
//            3.1.使用事务同步管理器注册事务同步监听器
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
//                3.2.选择事务完成后执行逻辑
            @Override
            public void afterCompletion(int status) {
//                    3.3如果事务完成状态为提交成功则执行
                if (STATUS_COMMITTED == status) {
                    log.info("开始处理消息逻辑");
//                        判断是否异步执行
                    if (isAsync) {
                        log.info("异步处理逻辑");
//                    使用线程池执行
                        threadPoolExecutor.execute(() -> {
                            execNotifyTaskJob(Collections.singletonList(messageTask));
                        });
                    } else {
                        log.info("同步处理逻辑");
                        execNotifyTaskJob(Collections.singletonList(messageTask));
//                    同步执行
                    }
                }
            }
        });
    }

    /**
     * 构建消息
     *
     * @param messageEntity 消息实体
     * @return 消息
     */
    private MessageTask buildMessage(MessageEntity messageEntity) {
        MessageTask messageTask = new MessageTask();
        messageTask.setMsgId(messageEntity.getMsgId());
        messageTask.setMsgType(messageEntity.getMsgType());
        messageTask.setDeliveryType(messageEntity.getDeliveryType().getCode());
        messageTask.setDeliveryCount(0);
        messageTask.setDeliveryCount(0);
        messageTask.setRetryInterval(messageEntity.getRetryInterval());
        messageTask.setMsgJson(messageEntity.getMsgJson());
        messageTask.setNotifyUrl(messageEntity.getNotifyUrl());
        messageTask.setStatus(messageEntity.getStatus().getCode());
        messageTask.setCreateTime(new Date());
        messageTask.setUpdateTime(new Date());
        return messageTask;
    }

    @Override
    public Map<String, Integer> execNotifyTaskJob() throws Exception {
        log.info("拼团交易-执行交易回调任务");
        List<MessageTask> messageTaskList = messageService.queryUnExecutedMessage();
        return execNotifyTaskJob(messageTaskList);
    }

    private Map<String, Integer> execNotifyTaskJob(List<MessageTask> messageTaskList) {
        int successCount = 0, errorCount = 0, retryCount = 0;
        for (MessageTask messageTask : messageTaskList) {
//            选择回调策略
            ISettleStrategyPort strategyPort = settleStrategyFactory.getSettleStrategy(messageTask.getDeliveryType());
            String response = strategyPort.groupBuyingNotifyTask(messageTask);
            if (NotifyTaskHttpEnumVO.SUCCESS.getCode().equals(response)) {
                int updateCount = messageService.updateMessageStatusSuccess(messageTask.getMsgId());
                if (updateCount == 1) {
                    successCount++;
                }
            } else if (NotifyTaskHttpEnumVO.ERROR.getCode().equals(response)) {
                if (messageTask.getDeliveryCount() >= messageTask.getRetryCount()) {
                    int updateCount = messageService.updateMessageStatusError(messageTask.getMsgId());
                    if (updateCount == 1) {
                        errorCount++;
                    }
                } else {
                    int updateCount = messageService.updateMessageStatusRetry(messageTask.getMsgId());
                    if (updateCount == 1) {
                        retryCount++;
                    }
                }

            }
        }
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("waitCount", messageTaskList.size());
        resultMap.put("successCount", successCount);
        resultMap.put("errorCount", errorCount);
        resultMap.put("retryCount", retryCount);

        return resultMap;
    }

}
