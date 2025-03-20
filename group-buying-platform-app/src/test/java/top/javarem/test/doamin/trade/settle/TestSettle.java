package top.javarem.test.doamin.trade.settle;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.javarem.domain.trade.model.entity.PaySuccessEntity;
import top.javarem.domain.trade.model.entity.TradePaySettleEntity;
import top.javarem.domain.trade.service.ITradeSettleOrderService;
import top.javarem.infrastructure.dao.service.GroupBuyOrderListService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: rem
 * @Date: 2025/03/13/21:59
 * @Description:
 */
@SpringBootTest
@Slf4j
public class TestSettle {

    @Resource
    private ITradeSettleOrderService tradeSettlementOrderService;
    @Resource
    private GroupBuyOrderListService groupBuyOrderListService;

    @Test
    public void test() {

        List<String> strings = groupBuyOrderListService.queryAllOutTradeNoByTeamId("23130978", 100123L);
        log.info(JSON.toJSONString(strings));

    }

    @Test
    public void test_settlementMarketPayOrder() throws Exception {
        PaySuccessEntity tradePaySuccessEntity = new PaySuccessEntity();
        tradePaySuccessEntity.setSource("s01");
        tradePaySuccessEntity.setChannel("c01");
        tradePaySuccessEntity.setUserId("rem");
        tradePaySuccessEntity.setOutTradeNo("075116371415");
        TradePaySettleEntity tradePaySettlementEntity = tradeSettlementOrderService.settlePayOrder(tradePaySuccessEntity);
        log.info("请求参数:{}", JSON.toJSONString(tradePaySuccessEntity));
        log.info("测试结果:{}", JSON.toJSONString(tradePaySettlementEntity));
    }

}
