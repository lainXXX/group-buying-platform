package top.javarem.test.infrastructure.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.javarem.infrastructure.dao.service.GroupBuyingDiscountService;

import javax.annotation.Resource;

/**
 * @Author: rem
 * @Date: 2025/03/26/11:33
 * @Description:
 */
@Slf4j
@SpringBootTest
public class DiscountTest {

    @Resource
    private GroupBuyingDiscountService groupBuyingDiscountService;

    @Test
    public void test() {

        groupBuyingDiscountService.queryGroupBuyingDiscountByDiscountId("21120209");

    }

}
