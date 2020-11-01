package app.queue;

/**
 * Queue
 * 队列的的操作也是数组的子集，不同的是，队列只能从一端（队尾）添加元素，只能从另一端（队首）取出元素。
 */
public interface Queue<E> {

    /**
     * 入队。
     * @param e 入队的元素。
     */
    void enqueue(E e);

    /**
     * 出队。
     * @return 出队的元素。
     */
    E dequeue();

    /**
     * 获得队首元素。
     * @return 队首的元素。
     */
    E getFront();

    /**
     * 获得队列中元素的个数。
     * @return 元素的个数。
     */
    int getSize();

    /**
     * 查看队列是否为空。
     * @return 队列是否为空。
     */
    boolean isEmpty();
}