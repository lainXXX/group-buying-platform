package top.javarem.types.design.framework.link.model2;

import top.javarem.types.design.framework.link.model2.handler.ILinkHandler;

/**
 * @Author: rem
 * @Date: 2025/03/10/20:03
 * @Description: 责任链装备
 */
public class ChainArmory<T, D, R> {

    private final BusinessLinkedList<T, D, R> logicLink;

    public ChainArmory(String chainName, ILinkHandler<T, D, R>... handlers) {
        this.logicLink = new BusinessLinkedList<>(chainName);
        for (ILinkHandler<T, D, R> handler : handlers) {
            logicLink.add(handler);
        }
    }

    public BusinessLinkedList<T, D, R> getLogicLink() {
        return logicLink;
    }


}
