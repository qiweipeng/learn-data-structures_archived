package app.queue;

/**
 * LoopQueue
 * 数组队列的问题在于，出队的时间复杂度过高，导致如果队列元素特别多的情况下，出队就比较耗时。
 * 循环队列可以解决这一问题。
 * 
 * 这个队列中的五个方法复杂度：
 * void enqueue(E)     O(1) 均摊
 * E dequeue()         O(1) 均摊
 * E getFront()        O(1)
 * int getSize()       O(1)
 * boolean isEmpty()   O(1)
 */
public class LoopQueue<E> implements Queue<E> {
    private E[] data;
    private int front, tail;
    private int size;

    @SuppressWarnings("unchecked")
    public LoopQueue(int capacity) {

        // 创建的数组容量比用户期望的多一个，因为循环队列中，我们会有意识的浪费掉一个空间。
        // 因为我们通常认为 front == tail 的时候是队列为空，对于循环队列，我们如果不让出一个空间，那么这个表达式也有可能是队列元素占满的情况。
        data = (E[])new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    /**
     * 循环队列创建的数组中，我们会有意识地浪费掉一个空间。
     * @return
     */
    public int getCapacity() {
        return data.length - 1;
    }

    /**
     * 循环队列使用 front == tail 来判断元素为空，这也是为什么创建数组需要多一个空间。
     */
    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    /**
     * 获得队列元素的个数。
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * 入队。
     */
    @Override
    public void enqueue(E e) {

        // 判断队列是否满了
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2);
        }

        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    /**
     * 出队。
     */
    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }
        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;

        // 缩容。
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }

        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty.");
        }
        return data[front];
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        E[] newData = (E[])new Object[newCapacity + 1];

        // 第一种遍历循环队列的所有元素的方式。
        for (int i = 0; i < size; i++) {
            // 当扩容或者缩容的时候，原本 front 不为 0 的队列被重新规整为 front 为 0 的队列了
            newData[i] = data[(i + front) % data.length];
        }

        data = newData;
        front = 0;
        tail = size;
    }
 
    /**
     * 打印该类的显示字符串。
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("QUeue: size = %d, capacity = %d\n", size, getCapacity()));
        res.append("front [");

        // 第二种遍历循环队列中所有元素的方式。
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            if ((i + 1) % data.length != tail) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }
}