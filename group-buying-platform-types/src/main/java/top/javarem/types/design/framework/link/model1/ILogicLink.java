package top.javarem.types.design.framework.link.model1;

/**
 * @Author: rem
 * @Date: 2025/03/10/14:13
 * @Description: 责任链节点
 */
public interface ILogicLink<T, D, R> extends ILogicLinkArmory<T, D, R> {

    R apply(T requestParameter, D dynamicContext) throws Exception;

}
