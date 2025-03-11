package top.javarem.types.design.framework.link.model1;

/**
 * @Author: rem
 * @Date: 2025/03/10/14:15
 * @Description: 责任链节点抽象类 复用完善部分方法
 */
public abstract class AbstractLogicLink<T, D, R> implements ILogicLink<T, D, R> {

    private ILogicLink<T, D, R> next;

    @Override
    public ILogicLink next() throws Exception {
        return next;
    }

    @Override
    public ILogicLink appendNext(ILogicLink<T, D, R> next) throws Exception {
        this.next = next;
        return next;
    }

    protected R doNext(T requestParameter, D dynamicContext) throws Exception {

        return next.apply(requestParameter, dynamicContext);

    }
}
