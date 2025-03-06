package top.javarem.doamin.activity.trial;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.javarem.domain.activity.model.entity.MarketProductEntity;
import top.javarem.domain.activity.model.entity.TrialBalanceEntity;
import top.javarem.domain.activity.service.IndexGroupBuyingService;
import top.javarem.domain.activity.service.trial.factory.DefaultActivityStrategyFactory;
import top.javarem.domain.activity.service.trial.node.MarketNode;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @Author: rem
 * @Date: 2025/03/02/9:26
 * @Description:
 */
@SpringBootTest
@Slf4j
public class IIndexGroupBuyingServiceTest {

    @Resource
    private IndexGroupBuyingService indexGroupBuyingService;

    @Resource
    private MarketNode marketNode;

    /**
     * 通过多线程和单线程的测试 得出多线程用时2051毫秒 单线程用时5434毫秒 相差3.383秒
     * @throws Exception
     */
    @Test
    public void test_multiThead() throws Exception {
        MarketProductEntity marketProductEntity = new MarketProductEntity();
        marketProductEntity.setUserId("rem");
        marketProductEntity.setSource("s01");
        marketProductEntity.setChannel("c01");
        marketProductEntity.setGoodsId("9890001");
        DefaultActivityStrategyFactory.DynamicContext dynamicContext = new DefaultActivityStrategyFactory.DynamicContext();
        LocalDateTime begin = LocalDateTime.now();

        marketNode.multiThread(marketProductEntity, dynamicContext);
        LocalDateTime end = LocalDateTime.now();
        Duration between = Duration.between(begin, end);
        long millis = between.toMillis();
        log.info("用时: {}", millis);
    }

    @Test
    public void test_indexMarketTrial() throws Exception {

        MarketProductEntity marketProductEntity = new MarketProductEntity();
        marketProductEntity.setUserId("rem");
        marketProductEntity.setSource("s01");
        marketProductEntity.setChannel("c01");
        marketProductEntity.setGoodsId("9890001");

        TrialBalanceEntity trialBalanceEntity = indexGroupBuyingService.indexMarketTrial(marketProductEntity);
        log.info("请求参数: {}", marketProductEntity);
        log.info("返回结果: {}", trialBalanceEntity.toString());

    }

    @Test
    public void test_indexMarketTrial_no_tag() throws Exception {

        MarketProductEntity marketProductEntity = new MarketProductEntity();
        marketProductEntity.setUserId("atri");
        marketProductEntity.setSource("s01");
        marketProductEntity.setChannel("c01");
        marketProductEntity.setGoodsId("9890001");

        TrialBalanceEntity trialBalanceEntity = indexGroupBuyingService.indexMarketTrial(marketProductEntity);
        log.info("请求参数: {}", marketProductEntity);
        log.info("返回结果: {}", trialBalanceEntity.toString());

    }

    @Test
    public void test_indexMarketTrial_error() throws Exception {

        MarketProductEntity marketProductEntity = new MarketProductEntity();
        marketProductEntity.setUserId("rem");
        marketProductEntity.setSource("s01");
        marketProductEntity.setChannel("c01");
        marketProductEntity.setGoodsId("9890002");

        TrialBalanceEntity trialBalanceEntity = indexGroupBuyingService.indexMarketTrial(marketProductEntity);
        log.info("请求参数: {}", marketProductEntity);
        log.info("返回结果: {}", trialBalanceEntity.toString());

    }

}
