package com.ozanselte;

import java.util.EmptyStackException;

/**
 * Stack data type implementation.
 * @param <E> Element type
 * @author Ozan Şelte
 */
public class StackO<E> {

    private NodeO<E> top;

    /**
     * The constructor.
     */
    public StackO() {
        top = null;
    }

    /**
     * Tests if this stack is empty.
     * @return true if and only if this stack contains no items; false otherwise.
     */
    public boolean empty() {
        //return (null == top) || (null == top.getObj());
        return (null == top);
    }

    /**
     * Looks at the object at the top of this stack without removing it from the stack.
     * @return the object at the top of this stack.
     * @throws EmptyStackException if this stack is empty.
     */
    public E peek() throws EmptyStackException {
        if(empty()) throw new EmptyStackException();
        return top.getObj();
    }

    /**
     * Removes the object at the top of this stack and returns that object as the value of this function.
     * @return the object at the top of this stack.
     * @throws EmptyStackException if this stack is empty.
     */
    public E pop() throws EmptyStackException {
        if(empty()) throw new EmptyStackException();
        E obj = peek();
        top = top.getNext();
        return obj;
    }

    /**
     * Pushes an item onto the top of this stack.
     * @param item the item to be pushed onto this stack.
     * @return the item argument.
     */
    public E push(E item) {
        NodeO<E> temp = new NodeO<E>(item);
        if(!empty()) {
            temp.setNext(top);
        }
        top = temp;
        return item;
    }
}
