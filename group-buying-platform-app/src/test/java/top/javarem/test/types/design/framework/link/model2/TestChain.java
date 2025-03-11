package top.javarem.test.types.design.framework.link.model2;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.javarem.types.design.framework.link.model2.BusinessLinkedList;

import javax.annotation.Resource;

/**
 * @Author: rem
 * @Date: 2025/03/10/21:10
 * @Description:
 */
@SpringBootTest
@Slf4j
public class TestChain {

    @Resource(name = "chain01")
    private BusinessLinkedList<String, Factory.DynamicContext, XResponse> list1;
    @Resource(name = "chain02")
    private BusinessLinkedList<String, Factory.DynamicContext, XResponse> list2;

    @Test
    public void test() throws Exception {

        XResponse apply = list1.apply("111", new Factory.DynamicContext());
        log.info("测试结果:{}", apply.toString());

    }

    @Test
    public void test02() throws Exception {

        XResponse apply = list2.apply("111", new Factory.DynamicContext());
        log.info("测试结果:{}", apply.toString());

    }

}
