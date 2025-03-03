package top.javarem.doamin.activity.trial;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.javarem.domain.activity.model.entity.MarketProductEntity;
import top.javarem.domain.activity.model.entity.TrialBalanceEntity;
import top.javarem.domain.activity.service.IndexGroupBuyingService;

import javax.annotation.Resource;

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

}
