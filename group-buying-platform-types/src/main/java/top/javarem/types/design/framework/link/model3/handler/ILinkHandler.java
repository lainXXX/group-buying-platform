package top.javarem.types.design.framework.link.model3.handler;

/**
 * @Author: rem
 * @Date: 2025/03/10/21:29
 * @Description: 责任链节点处理器
 */
public interface ILinkHandler<T, D, R> {

    R apply(T requestParameter, D dynamicContext) throws Exception;

}
