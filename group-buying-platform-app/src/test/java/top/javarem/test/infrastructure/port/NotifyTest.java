package top.javarem.test.infrastructure.port;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.javarem.infrastructure.gateway.GroupBuyingNotifyService;

import javax.annotation.Resource;

/**
 * @Author: rem
 * @Date: 2025/03/17/17:35
 * @Description:
 */
@SpringBootTest
@Slf4j
public class NotifyTest {

    @Resource
    private GroupBuyingNotifyService groupBuyingNotifyService;

    @Test
    public void test() throws Exception {
        String url = "http://127.0.0.1:8090/api/v1/test/group_buy_notify";
        String json = "{\"teamId\":\"99359307\",\"outTradeNo\":[\"779144288971\",\"075116371415\"]}";
        String s = groupBuyingNotifyService.groupBuyingNotify(url, json);
        log.info("结果: s:{}", s);
    }

}
