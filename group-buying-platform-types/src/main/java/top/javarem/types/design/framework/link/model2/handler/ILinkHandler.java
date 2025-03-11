package top.javarem.types.design.framework.link.model2.handler;


/**
 * @Author: rem
 * @Date: 2025/03/10/17:08
 * @Description:
 */
public interface ILinkHandler<T, D, R> {

    default R next(T requestParameter, D dynamicContext) throws Exception {
        return null;
    }

    R apply(T requestParameter, D dynamicContext) throws Exception;

}
