package top.javarem.infrastructure.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.javarem.infrastructure.dao.po.GroupBuyingActivity;
import top.javarem.infrastructure.dao.po.GroupBuyingDiscount;
import top.javarem.infrastructure.dao.service.GroupBuyingActivityService;
import top.javarem.infrastructure.dao.service.GroupBuyingDiscountService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: rem
 * @Date: 2025/02/26/09:31
 * @Description:基础设施层的dao测试
 */
@SpringBootTest
@Slf4j
public class DaoTest {

    @Resource
    private GroupBuyingActivityService groupBuyingActivityService;
    @Resource
    private GroupBuyingDiscountService groupBuyingDiscountService;

    @Test
    public void test_activity_insert() {

        List<GroupBuyingActivity> buyingActivities = groupBuyingActivityService.list();
        List<GroupBuyingDiscount> groupBuyingDiscounts = groupBuyingDiscountService.list();
        log.info(buyingActivities.toString());
        log.info(groupBuyingDiscounts.toString());


    }
}
