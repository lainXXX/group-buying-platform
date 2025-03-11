package top.javarem.types.design.framework.link.model2;

/**
 * @Author: rem
 * @Date: 2025/03/10/17:08
 * @Description:
 */
public class LinkedList<E> implements ILinkedList<E> {

    private String name;

    protected Node<E> first;

    protected Node<E> last;


    public LinkedList() {}

    public LinkedList(String name) {

        this.name = name;

    }

    @Override
    public void add(E e) {

        final Node<E> l = last;
        Node<E> newNode = new Node<>(e, last, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
    }

    protected static class Node<E> {

        E element;

        Node<E> prev;

        Node<E> next;

        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

    }


}
