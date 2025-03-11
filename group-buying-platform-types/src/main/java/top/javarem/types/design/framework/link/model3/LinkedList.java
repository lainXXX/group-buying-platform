package top.javarem.types.design.framework.link.model3;

/**
 * @Author: rem
 * @Date: 2025/03/10/21:32
 * @Description:
 */
public class LinkedList<E> {

    private Node<E> head;

    private Node<E> tail;

    public void add(E e) {

        Node<E> temp = tail;
        Node<E> newNode = new Node<>(e, tail, null);
        tail = newNode;
        if (temp == null) {
            head = newNode;
        } else {
            temp.next = newNode;
        }
    }

    protected static class Node<E> {

        E element;
        Node<E> next;
        Node<E> prev;

        public Node(E element, Node<E> prev, Node<E> next) {
        }

    }

}
