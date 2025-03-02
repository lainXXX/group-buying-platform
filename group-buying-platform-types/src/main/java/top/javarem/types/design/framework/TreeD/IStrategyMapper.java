package top.javarem.types.design.framework.TreeD;

/**
 * @Author: rem
 * @Date: 2025/03/01/15:30
 * @Description: 策略映射器
 */
public interface IStrategyMapper<T, D, R> {

    IStrategyHandler<T, D, R> get(T requestParam, D dynamicContext) throws Exception;

}
