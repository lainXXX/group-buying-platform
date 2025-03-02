package top.javarem.types.design.framework.TreeD;

/**
 * @Author: rem
 * @Date: 2025/03/01/22:48
 * @Description:
 */
public abstract class AbstractStrategyRouter<T, D, R> implements IStrategyHandler<T, D, R>, IStrategyMapper<T, D, R> {

    IStrategyHandler<T, D, R> defaultHandler = IStrategyHandler.DEFAULT;

    protected R router(T requestParam, D dynamicContext) throws Exception {

        IStrategyHandler<T, D, R> strategyHandler = get(requestParam, dynamicContext);
        if (strategyHandler != null) return defaultHandler.apply(requestParam, dynamicContext);
        return strategyHandler.apply(requestParam, dynamicContext);

    }

}
