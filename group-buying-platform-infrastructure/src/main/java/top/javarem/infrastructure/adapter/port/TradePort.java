package top.javarem.infrastructure.adapter.port;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import top.javarem.domain.trade.adapter.port.ITradePort;
import top.javarem.domain.trade.model.entity.NotifyTaskEntity;
import top.javarem.infrastructure.event.EventPublisher;
import top.javarem.infrastructure.gateway.GroupBuyingNotifyService;
import top.javarem.infrastructure.redis.IRedisService;
import top.javarem.types.common.Constants;
import top.javarem.types.enums.NotifyTaskHttpEnumVO;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author: rem
 * @Date: 2025/03/17/13:56
 * @Description: 交易
 */
@Service
@Slf4j
public class TradePort implements ITradePort {

    @Resource
    private GroupBuyingNotifyService groupBuyingNotifyService;
    @Resource
    private IRedisService redisService;
    @Resource
    private EventPublisher publisher;

    @Value("${spring.rabbitmq.config.producer.topic_team_success.routing_key}")
    private String routingKey;

    @Override
    public String groupBuyingNotifyTask(NotifyTaskEntity notifyTaskEntity) {
        String key = Constants.NOTIFY_TASK_LOCK_KEY + notifyTaskEntity.getTeamId();
        RLock lock = redisService.getLock(key);
        try {
            if (lock.tryLock(3, TimeUnit.SECONDS)) {
                try {
                    if (StringUtils.isBlank(notifyTaskEntity.getNotifyUrl())) {
                        return NotifyTaskHttpEnumVO.SUCCESS.getCode();
                    }
//                    改用MQ异步解耦
                    publisher.publish(routingKey, notifyTaskEntity.getParameterJson());
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
