package top.javarem.types.design.framework.tree;

/**
 * @Author: rem
 * @Date: 2025/02/26/11:18
 * @Description: 策略映射器
 * T 入参类型
 * D 上下文类型
 * R 返回类型
 */
public interface StrategyMapper<T, D, R> {

    /**
     * 获取待执行策略
     * @param requestParameter 入参
     * @param dynamicContext 上下文
     * @return 返回值
     * @throws Exception 异常
     */
    StrategyHandler<T, D, R> get(T requestParameter, D dynamicContext) throws Exception;

}
