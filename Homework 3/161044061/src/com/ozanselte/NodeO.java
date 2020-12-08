package com.ozanselte;

/**
 * Node class implementation for StackO.
 * @param <E> Element type
 * @author Ozan Şelte
 */
public class NodeO<E> {

    private E obj;
    private NodeO<E> next;

    /**
     * The constructor with an object.
     * @param obj the object
     */
    NodeO(E obj) {
        this(obj, null);
    }

    /**
     * The constructor with an object and a next NodeO.
     * @param obj the object
     * @param next the next node
     */
    NodeO(E obj, NodeO<E> next) {
        setObj(obj);
        setNext(next);
    }

    /**
     * The objects getter.
     * @return the object
     */
    public E getObj() {
        return obj;
    }

    /**
     * The objects setter.
     * @param obj the new object
     */
    public void setObj(E obj) {
        this.obj = obj;
    }

    /**
     * The next nodes getter.
     * @return the next node
     */
    public NodeO<E> getNext() {
        return next;
    }

    /**
     * The next nodes setter.
     * @param next the next node
     */
    public void setNext(NodeO<E> next) {
        this.next = next;
    }
}
