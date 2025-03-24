package top.javarem.test.infrastructure.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.javarem.infrastructure.dao.mapper.GroupBuyOrderListMapper;

import javax.annotation.Resource;

/**
 * @Author: rem
 * @Date: 2025/03/24/14:04
 * @Description:
 */
@SpringBootTest
@Slf4j
public class ActivityTest {

    @Resource
    private GroupBuyOrderListMapper groupBuyOrderListMapper;

    @Test
    public void test() {

        int count = groupBuyOrderListMapper.queryUserActivityPartakeCount(100123L, "rem");
        log.info(String.valueOf(count));

    }

}
