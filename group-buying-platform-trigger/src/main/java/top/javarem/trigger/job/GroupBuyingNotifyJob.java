package top.javarem.trigger.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import top.javarem.domain.trade.service.ITradeSettleOrderService;
import top.javarem.types.common.GsonUtils;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author: rem
 * @Date: 2025/03/17/16:14
 * @Description: 回调任务
 */
@Service
@Slf4j
public class GroupBuyingNotifyJob {

    @Resource
    private ITradeSettleOrderService tradeSettleOrderService;

    @Scheduled(cron = "0 */1 * * * *")
    public void groupBuyingNotify() {
        try {
            Map<String, Integer> result = tradeSettleOrderService.execNotifyTaskJob();
            log.info("定时任务- 支付回调通知拼团完成 result:{}", GsonUtils.gson.toJson(result));
        } catch (Exception e) {
            log.error("定时任务- 支付回调通知拼团完成任务失败", e);
        }

    }

}
