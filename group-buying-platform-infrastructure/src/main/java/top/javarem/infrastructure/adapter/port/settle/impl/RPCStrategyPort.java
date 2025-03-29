package top.javarem.infrastructure.adapter.port.settle.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.javarem.infrastructure.adapter.port.settle.ISettleStrategyPort;
import top.javarem.infrastructure.dao.po.MessageTask;

/**
 * @Author: rem
 * @Date: 2025/03/28/22:20
 * @Description: rpc回调
 */
@Service("RPC")
@Slf4j
public class RPCStrategyPort implements ISettleStrategyPort {
    @Override
    public String groupBuyingNotifyTask(MessageTask messageTaskEntity) {
        log.info("结算策略服务 -rpc回调 {}", messageTaskEntity);
        return "";
    }


}
