package top.javarem.infrastructure.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: rem
 * @Date: 2025/03/23/10:01
 * @Description: 发送mq消息
 */
@Slf4j
@Component
public class EventPublisher {

    @Value("${spring.rabbitmq.config.producer.exchange}")
    private String exchangeName;

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void publish(String routerKey, String message) {
        try {
            rabbitTemplate.convertAndSend(exchangeName, routerKey, message, m ->{
//            持久化配置消息
                m.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                return m;
            });

        } catch (Exception e) {
            log.error("发送mq消息失败 message:{}", message, e);
            throw e;
        }

    }


}
