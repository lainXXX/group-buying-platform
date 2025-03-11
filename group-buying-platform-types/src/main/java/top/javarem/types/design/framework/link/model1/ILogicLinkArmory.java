package top.javarem.types.design.framework.link.model1;

/**
 * @Author: rem
 * @Date: 2025/03/10/14:21
 * @Description: 责任链节点装备
 */
public interface ILogicLinkArmory<T, D, R> {

    ILogicLink<T, D, R> next() throws Exception;

    ILogicLink<T, D, R> appendNext(ILogicLink<T, D, R> next) throws Exception;

}
