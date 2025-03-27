package top.javarem.test.infrastructure.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.javarem.domain.activity.adapter.repository.IActivityRepository;
import top.javarem.domain.activity.model.entity.GroupBuyingTeamOrderDetailEntity;
import top.javarem.infrastructure.dao.mapper.GroupBuyOrderListMapper;
import top.javarem.infrastructure.dao.po.GroupBuyOrderList;
import top.javarem.infrastructure.dao.service.GroupBuyOrderListService;
import top.javarem.infrastructure.dao.service.GroupBuyingOrderService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: rem
 * @Date: 2025/03/24/14:04
 * @Description:
 */
@SpringBootTest
@Slf4j
public class ActivityTest {

    @Resource
    private GroupBuyOrderListService groupBuyOrderListService;

    @Resource
    private GroupBuyOrderListMapper groupBuyOrderListMapper;

    @Test
    public void test() {

        int count = groupBuyOrderListMapper.queryUserActivityPartakeCount(100123L, "rem");
        log.info(String.valueOf(count));

    }

    @Test
    public void test2() {

        List<GroupBuyOrderList> groupBuyingTeamOrderDetailEntities = groupBuyOrderListService.queryOtherGroupBuyingTeamOrderDetailList("111", 100123L, 2);
        log.info(groupBuyingTeamOrderDetailEntities.toString());

    }

}
