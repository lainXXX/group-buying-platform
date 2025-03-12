package top.javarem.test.doamin.trade.factory;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.javarem.domain.trade.model.entity.TradeRuleFilterBackEntity;
import top.javarem.domain.trade.model.entity.TradeRuleFilterEntity;
import top.javarem.domain.trade.service.factory.TradeRuleFilterFactory;
import top.javarem.types.design.framework.link.model2.BusinessLinkedList;

import javax.annotation.Resource;

/**
 * @Author: rem
 * @Date: 2025/03/12/9:00
 * @Description:
 */
@SpringBootTest
@Slf4j
public class TestArmoryLink {

    @Resource
    private TradeRuleFilterFactory tradeRuleFilterFactory;

    @Test
    public void test_armory_link() throws Exception {

        BusinessLinkedList<TradeRuleFilterEntity, TradeRuleFilterFactory.DynamicContext, TradeRuleFilterBackEntity> tradeRuleFilter = tradeRuleFilterFactory.getTradeRuleFilter();
        TradeRuleFilterEntity tradeRuleFilterEntity = TradeRuleFilterEntity.builder()
                .activityId(100123L)
                .userId("rem01")
                .build();
        TradeRuleFilterBackEntity apply = tradeRuleFilter.apply(tradeRuleFilterEntity, new TradeRuleFilterFactory.DynamicContext());
        log.info("测试成功 userId :{} activityId: {} 用户参与次数 userPartakeCount : {}", tradeRuleFilterEntity.getUserId(), tradeRuleFilterEntity.getActivityId(), apply.getUserPartakeCount());
    }

}
