package top.javarem.test.trigger.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.javarem.api.IMarketTradeService;
import top.javarem.api.dto.LockMarketPayOrderRequestDTO;

import javax.annotation.Resource;

/**
 * @Author: rem
 * @Date: 2025/03/10/9:53
 * @Description:
 */
@SpringBootTest
@Slf4j
public class TestTrade {

    @Resource
    private IMarketTradeService tradeService;

    @Test
    public void test_lockPayOrder() {

        LockMarketPayOrderRequestDTO lockMarketPayOrderRequestDTO = new LockMarketPayOrderRequestDTO();
        lockMarketPayOrderRequestDTO.setActivityId(100123L);
        lockMarketPayOrderRequestDTO.setChannel("c01");
        lockMarketPayOrderRequestDTO.setSource("s01");
        lockMarketPayOrderRequestDTO.setGoodsId("9890001");
        lockMarketPayOrderRequestDTO.setTeamId(null);
        lockMarketPayOrderRequestDTO.setUserId("lain");
        lockMarketPayOrderRequestDTO.setOutTradeNo(RandomStringUtils.randomNumeric(12));
        lockMarketPayOrderRequestDTO.setNotifyUrl("http://127.0.0.1:8090/api/v1/test/group_buy_notify");
        tradeService.lockPayOrder(lockMarketPayOrderRequestDTO);

    }

    @Test
    public void test_lockPayOrder_team_not_null() {
        LockMarketPayOrderRequestDTO lockMarketPayOrderRequestDTO = new LockMarketPayOrderRequestDTO();
        lockMarketPayOrderRequestDTO.setActivityId(100123L);
        lockMarketPayOrderRequestDTO.setChannel("c01");
        lockMarketPayOrderRequestDTO.setSource("s01");
        lockMarketPayOrderRequestDTO.setGoodsId("9890001");
        lockMarketPayOrderRequestDTO.setTeamId("99359307");
        lockMarketPayOrderRequestDTO.setUserId("rem");
        lockMarketPayOrderRequestDTO.setOutTradeNo(RandomStringUtils.randomNumeric(12));
        lockMarketPayOrderRequestDTO.setNotifyUrl("http://127.0.0.1:8090/api/v1/test/group_buy_notify");
        tradeService.lockPayOrder(lockMarketPayOrderRequestDTO);

    }

    @Test
    public void test_lockPayOrder_trade_order_filter() {
        LockMarketPayOrderRequestDTO lockMarketPayOrderRequestDTO = new LockMarketPayOrderRequestDTO();
        lockMarketPayOrderRequestDTO.setActivityId(100123L);
        lockMarketPayOrderRequestDTO.setChannel("c01");
        lockMarketPayOrderRequestDTO.setSource("s01");
        lockMarketPayOrderRequestDTO.setGoodsId("9890001");
        lockMarketPayOrderRequestDTO.setTeamId("80557358");
        lockMarketPayOrderRequestDTO.setUserId("rem01");
        lockMarketPayOrderRequestDTO.setOutTradeNo(RandomStringUtils.randomNumeric(12));
        tradeService.lockPayOrder(lockMarketPayOrderRequestDTO);
    }

}
