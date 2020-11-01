package app.stack;

import app.linkedlist.LinkedList;

/**
 * LinkedListStack
 * 使用链表来实现一个栈的数据结构。
 * 
 * 这个栈中的五个方法复杂度：
 * void push(E)        O(1)
 * E pop()             O(1)
 * E peek()            O(1)
 * int getSize()       O(1)
 * boolean isEmpty()   O(1)
 * 
 */
public class LinkedListStack<E> implements Stack<E> {
    private LinkedList<E> list;

    public LinkedListStack() {
        list = new LinkedList<>();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public E peek() {
        return list.getFirst();
    }
    
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        res.append("Stack: top ");
        res.append(list);
        return res.toString();
    }
}