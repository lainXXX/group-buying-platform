package top.javarem.test.infrastructure.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.javarem.infrastructure.dao.po.GroupBuyingActivity;
import top.javarem.infrastructure.dao.po.GroupBuyingDiscount;
import top.javarem.infrastructure.dao.service.GroupBuyingActivityService;
import top.javarem.infrastructure.dao.service.GroupBuyingDiscountService;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    public static void main(String[] args) {
            try {
                String userId = "rem";
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] hashBytes = md.digest(userId.getBytes(StandardCharsets.UTF_8));
                // 将哈希字节数组转换为正整数
                BigInteger bigInt = new BigInteger(1, hashBytes);
                // 取模以确保索引在合理范围内
                int i = bigInt.mod(BigInteger.valueOf(Integer.MAX_VALUE)).intValue();
                System.out.println(i);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("MD5 algorithm not found", e);
            }
    }

    @Test
    public void test_activity_insert() {

        List<GroupBuyingActivity> buyingActivities = groupBuyingActivityService.list();
        List<GroupBuyingDiscount> groupBuyingDiscounts = groupBuyingDiscountService.list();
        log.info(buyingActivities.toString());
        log.info(groupBuyingDiscounts.toString());


    }
}
