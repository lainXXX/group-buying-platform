package top.javarem.infrastructure.adapter.port.settle.factory;

import org.springframework.stereotype.Component;
import top.javarem.infrastructure.adapter.port.settle.ISettleStrategyPort;

import java.util.Map;

/**
 * @Author: rem
 * @Date: 2025/03/28/22:02
 * @Description: 结算策略注册工厂
 */
@Component
public class DefaultSettleStrategyFactory {

    private final Map<String, ISettleStrategyPort> settleStrategyGroup;

    public DefaultSettleStrategyFactory(Map<String, ISettleStrategyPort> settleStrategyGroup) {
        this.settleStrategyGroup = settleStrategyGroup;
    }

    public ISettleStrategyPort getSettleStrategy(String strategyName) {
        return settleStrategyGroup.get(strategyName);
    }

}
