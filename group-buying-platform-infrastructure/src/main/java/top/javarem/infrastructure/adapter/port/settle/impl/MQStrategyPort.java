package top.javarem.infrastructure.adapter.port.settle.impl;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.javarem.infrastructure.adapter.port.settle.ISettleStrategyPort;
import top.javarem.infrastructure.dao.po.MessageTask;
import top.javarem.infrastructure.event.EventPublisher;
import top.javarem.infrastructure.gateway.GroupBuyingNotifyService;
import top.javarem.infrastructure.redis.IRedisService;
import top.javarem.types.common.Constants;
import top.javarem.types.enums.NotifyTaskHttpEnumVO;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author: rem
 * @Date: 2025/03/28/21:54
 * @Description: mq回调
 */
@Service("MQ")
@Slf4j
public class MQStrategyPort implements ISettleStrategyPort {
    @Resource
    private GroupBuyingNotifyService groupBuyingNotifyService;
    @Resource
    private IRedisService redisService;
    @Resource
    private EventPublisher publisher;

    @Value("${spring.rabbitmq.config.producer.topic_team_success.routing_key}")
    private String routingKey;
    @Override
    public String groupBuyingNotifyTask(MessageTask messageTaskEntity) {
        log.info("结算策略服务 -mq回调 {}", messageTaskEntity);
            String key = Constants.NOTIFY_TASK_LOCK_KEY + messageTaskEntity.getMsgId();
            RLock lock = redisService.getLock(key);
            try {
                if (lock.tryLock(3, TimeUnit.SECONDS)) {
                    try {

//                    改用MQ异步解耦
                        publisher.publish(routingKey, messageTaskEntity.getMsgJson());
                        return NotifyTaskHttpEnumVO.SUCCESS.getCode();
//                    不采用http回调
//                    return groupBuyingNotifyService.groupBuyingNotify(notifyTaskEntity.getNotifyUrl(), notifyTaskEntity.getParameterJson());
                    } finally {
                        lock.unlock();
                    }
                }
                return NotifyTaskHttpEnumVO.NULL.getCode();
            } catch (Exception e) {
                Thread.currentThread().interrupt();
                return NotifyTaskHttpEnumVO.NULL.getCode();
            }
        }
}
