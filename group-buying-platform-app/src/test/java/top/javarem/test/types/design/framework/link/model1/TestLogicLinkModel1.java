package top.javarem.test.types.design.framework.link.model1;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

/**
 * @Author: rem
 * @Date: 2025/03/10/15:00
 * @Description:
 */

@Slf4j
public class TestLogicLinkModel1 {

    @Resource
    private LogicLink1 logicLink1;

    @Test
    public void test() throws Exception {

        Object o = logicLink1.apply("123", "123");
        System.out.println(o);

    }

}
