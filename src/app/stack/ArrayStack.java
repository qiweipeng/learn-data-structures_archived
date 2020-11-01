package app.stack;

import app.array.Array;

/**
 * ArrayStack
 * 
 * 这个类基于我们自己实现的动态数组来实现「栈」这个接口。
 * 
 * 这个栈中的五个方法复杂度：
 * void push(E)        O(1) 均摊
 * E pop()             O(1) 均摊
 * E peek()            O(1)
 * int getSize()       O(1)
 * boolean isEmpty()   O(1)
 * 
 */
public class ArrayStack<E> implements Stack<E> {

    private Array<E> array;

    public ArrayStack(int capacity) {
        array = new Array<>(capacity);
    }

    public ArrayStack() {
        array = new Array<>();
    }

    /**
     * 栈的元素个数。
     */
    @Override
    public int getSize() {
        return array.getSize();
    }

    /**
     * 栈元素是否为空。
     */
    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     * 查看实现这个栈的这个动态数组当前的容积。
     * 这个方法不是栈特有的，因此栈接口没有定义这个方法，但是我们是使用数组实现的这个栈，可以有这个方法供用户查看。
     */
    public int getCapacity() {
        return array.getCapacity();
    }


    /**
     * 入栈。
     */
    @Override
    public void push(E e) {
        array.addLast(e);
    }

    /**
     * 出栈。
     */
    @Override
    public E pop() {
        return array.removeLast();
    }

    /**
     * 查看栈顶元素。
     */
    @Override
    public E peek() {
        return array.getFirst();
    }

    @Override
    public String toString() {
        
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append('[');

        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i != array.getSize() - 1) {
                res.append(", ");
            }
        }

        res.append("] top");

        return res.toString();
    }
}