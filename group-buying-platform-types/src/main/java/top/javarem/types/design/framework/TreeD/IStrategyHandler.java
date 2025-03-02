package top.javarem.types.design.framework.TreeD;

/**
 * @Author: rem
 * @Date: 2025/03/01/15:57
 * @Description:
 */
public interface IStrategyHandler<T, D, R> {

    IStrategyHandler DEFAULT = (T, D) -> null;

    R apply(T parameter, D dynamicContext);

}
