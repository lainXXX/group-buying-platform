package top.javarem.types.design.framework.link.model2;

import top.javarem.types.design.framework.link.model2.handler.ILinkHandler;

/**
 * @Author: rem
 * @Date: 2025/03/10/19:47
 * @Description: 业务责任链
 */
public class BusinessLinkedList<T, D, R> extends LinkedList<ILinkHandler<T, D, R>> implements ILinkHandler<T, D, R> {

    public BusinessLinkedList() {}

    public BusinessLinkedList(String name) {

        super(name);
    }


    @Override
    public R apply(T requestParameter, D dynamicContext) throws Exception {

        Node<ILinkHandler<T, D, R>> current = first;
        do {
            ILinkHandler<T, D, R> element = current.element;
            element.apply(requestParameter, dynamicContext);
            current = current.next;
        } while (current != null);
        return null;

    }
}
