package top.javarem.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import top.javarem.infrastructure.event.EventPublisher;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: rem
 * @Date: 2025/03/23/10:22
 * @Description:
 */
@SpringBootTest
@Slf4j
public class ApiTest {

    @Resource
    private EventPublisher publisher;

    @Value("${spring.rabbitmq.config.producer.topic_team_success.routing_key}")
    private String routingKey;

    @Test
    public void test_rabbitmq() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        publisher.publish(routingKey, "订单结算：ORD-20231234");
        publisher.publish(routingKey, "订单结算：ORD-20231235");
        publisher.publish(routingKey, "订单结算：ORD-20231236");
        publisher.publish(routingKey, "订单结算：ORD-20231237");
        publisher.publish(routingKey, "订单结算：ORD-20231238");

        // 等待，消息消费。测试后，可主动关闭。
        countDownLatch.await();
    }

}
