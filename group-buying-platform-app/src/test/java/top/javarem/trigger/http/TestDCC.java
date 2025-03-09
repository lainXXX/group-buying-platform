package top.javarem.trigger.http;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Author: rem
 * @Date: 2025/03/09/15:19
 * @Description:
 */
@SpringBootTest
@Slf4j
public class TestDCC {

    @Resource
    private DCCController dccController;

    @Test
    public void test_dcc() {
        String key = "degradeSwitch";
        String value = "1";
        dccController.updateConfig(key, value);

    }


}
