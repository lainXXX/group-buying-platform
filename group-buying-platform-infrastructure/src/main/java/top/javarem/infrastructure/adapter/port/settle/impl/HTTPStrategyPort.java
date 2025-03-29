package top.javarem.infrastructure.adapter.port.settle.impl;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.stereotype.Service;
import top.javarem.infrastructure.adapter.port.settle.ISettleStrategyPort;
import top.javarem.infrastructure.dao.po.MessageTask;
import top.javarem.infrastructure.gateway.GroupBuyingNotifyService;
import top.javarem.infrastructure.redis.IRedisService;
import top.javarem.types.common.Constants;
import top.javarem.types.enums.NotifyTaskHttpEnumVO;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author: rem
 * @Date: 2025/03/28/22:20
 * @Description: http回调
 */
@Service("HTTP")
@Slf4j
public class HTTPStrategyPort implements ISettleStrategyPort {

    @Resource
    private GroupBuyingNotifyService notifyService;
    @Resource
    private IRedisService redisService;

    @Override
    public String groupBuyingNotifyTask(MessageTask messageTaskEntity) {
        log.info("结算策略服务 -http回调 {}", messageTaskEntity);

        String key = Constants.NOTIFY_TASK_LOCK_KEY + messageTaskEntity.getMsgId();
        RLock lock = redisService.getLock(key);
        try {
            if (lock.tryLock(3, TimeUnit.SECONDS)) {
                try {
                    return notifyService.groupBuyingNotify(messageTaskEntity.getNotifyUrl(), messageTaskEntity.getMsgJson());
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
