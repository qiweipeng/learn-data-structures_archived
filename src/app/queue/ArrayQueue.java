package app.queue;

import app.array.Array;

/**
 * ArrayQueue
 * 
 * 类似栈的实现，这里也使用动态数组对队列进行一个实现。
 * 
 * 这个队列中的五个方法复杂度：
 * void enqueue(E)     O(1) 均摊
 * E dequeue()         O(n)
 * E getFront()        O(1)
 * int getSize()       O(1)
 * boolean isEmpty()   O(1)
 * 
 * 可以看到，数组实现的队列最大问题就是出队的复杂度是 O(n)
 */
public class ArrayQueue<E> implements Queue<E> {

    private Array<E> array;

    public ArrayQueue(int capacity) {
        array = new Array<>(capacity);
    }

    public ArrayQueue() {
        array = new Array<>();
    }

     /**
     * 队列的元素个数。
     */
    @Override
    public int getSize() {
        return array.getSize();
    }

    /**
     * 队列元素是否为空。
     */
    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     * 查看实现这个队列的这个动态数组当前的容积。
     */
    public int getCapacity() {
        return array.getCapacity();
    }

    /**
     * 入队。
     */
    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    /**
     * 出队。
     */
    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    /**
     * 获取队首元素。
     */
    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        res.append("Queue: ");
        res.append("front [");

        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i != array.getSize() - 1) {
                res.append(", ");
            }
        }

        res.append("] tail");

        return res.toString();
    }
}