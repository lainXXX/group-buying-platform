package top.javarem.types.design.framework.tree;

/**
 * @Author: rem
 * @Date: 2025/02/26/11:18
 * @Description: 受理策略处理器
 * T 入参类型
 * D 上下文类型
 * R 返回类型
 */
public interface StrategyHandler<T, D, R> {

    StrategyHandler DEFAULT = (T, D) -> null;

    /**
     *
     * @param requestParameter 入参
     * @param dynamicContext 上下文
     * @return 返回值
     * @throws Exception 异常
     */
    R apply(T requestParameter, D dynamicContext) throws Exception;

}
