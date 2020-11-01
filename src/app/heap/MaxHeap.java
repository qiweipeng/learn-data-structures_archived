package app.heap;

import app.array.Array;

/**
 * MaxHeap
 * 优先队列就是出队永远需要优先级对高的。优先队列针对的是动态的情况，如果是静态的，那么只需要排序一次即可。
 * 
 * 如果使用之前的线性数据结构实现优先队列，入队操作是 O(1)，但是出队操作需要遍历找到优先级最高的元素，因此是 O(n)。
 * 如果专门维护一个顺序的线性结构，也就是入队直接排序好，那么入队复杂度为 O(n)，出队复杂度为 O(1)。
 * 使用堆实现的优先队列，入队出队复杂度均为 O(logn)
 * 
 * 对于满二叉树，就是所有非叶子节点均既有左孩子，又有右孩子。
 * 完全二叉树不一定是满二叉树，但是其不满的部分一定是在树的右下侧。也就是除了最底层之外，上面的是一个满二叉树，最底层从左开始放置元素。
 * 
 * 二叉堆是一棵完全二叉树。
 * 堆中某个节点的值总是不大于其父节点的值，这叫做最大堆。相应也可以定义最小堆。
 * 
 * 因为二叉堆是一个完全二叉树，其实也能使用数组来表示这个完全二叉树，即分别按照层数从上倒下，从左到右放进数组，根节点放进索引为 1的位置，以此类推。
 * 
 * 放进数组后，假如某节点索引为 i，那么左孩子节点索引就是 2i，右孩子节点索引就是 2i+1，其父节点索引就是 i/2
 * 如果根节点位置直接放进索引为 0的位置，那么放进数组后，假如某节点索引为 i，那么左孩子节点索引就是 2i + 1，右孩子节点索引就是 2i+2，其父节点索引就是 (i - 1)/2
 * 对于完全二叉树，寻找最后一个非叶子节点的索引就是找最后一个节点的父亲节点
 * 
 * 
 * 使用动态数组实现一个最大堆。
 * 
 * add 和 extractMax 的时间复杂度都是 O(logn) 级别的
 * 需要注意的是，完全二叉树是不会退化为链表的，所以这个时间复杂度是可以一直保持的。
 * 
 * 我们实现的是二叉堆，相应的也就有三叉堆、四叉堆等等
 * 对于 d叉堆来说，层数有可能更少，这在有些操作上会让速度更快
 * 但是比如下沉操作，就要考虑 d 个子节点而不是 2 个
 * 
 * 
 * 更高级的堆还有 索引堆，它可以操作堆中的某个元素
 * 二项堆
 * 斐波那契堆
 */
public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap() {
        data = new Array<>();
    }

    /**
     * 将任意一个数组生成为一个最大堆。
     * 如果将 n 个元素逐个插入到一个空堆中，复杂度是 O(nlogn) 级别的。
     * heapify 的过程，算法复杂度是 O(n) 级别的
     * @param arr
     */
    public MaxHeap(E[] arr) {
        data = new Array<>(arr);
        for (int i = parent(arr.length - 1); i >= 0; i--) {
            siftDown(i);
        }
    }
 
    /**
     * 返回堆中元素个数。
     * @return
     */
    public int size() {
        return data.getSize();
    }

    /**
     * 返回一个布尔值，表示堆是否为空。
     * @return
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引。
     * @param index
     * @return
     */
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        }
        return (index - 1) / 2;
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引。
     * @param index
     * @return
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引。
     * @param index
     * @return
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    /**
     * 向堆中添加元素
     * @param e
     */
    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    /**
     * 元素堆上浮，新添加一个元素后，需要不断和它堆父节点做比较，如果大了就上浮
     * @param k 需要上浮元素的索引
     */
    private void siftUp(int k) {

        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    /**
     * 看堆中的最大元素，不取出。
     * @return
     */
    public E findMax() {
        if (data.getSize() == 0) {
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        }

        return data.get(0);
    }

    /**
     * 取出堆中最大元素。
     * 对于堆来讲，我们只能拿最大堆也就是根节点，或者说数组索引为 0 的元素。
     * @return
     */
    public E extractMax() {

        E ret = findMax();

        // 先把最大元素和最后一个元素交换。
        data.swap(0, data.getSize() - 1);
        data.removeLast();

        // 之后将根节点元素做下沉操作，也就是把它交换到合适的位置。
        siftDown(0);

        return ret;
    }

    private void siftDown(int k) {

        while (leftChild(k) < data.getSize()) {
            int j = leftChild(k);
            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0) {
                j = rightChild(k);
            }

            if (data.get(k).compareTo(data.get(j)) >= 0) {
                break;
            }

            data.swap(k, j);
            k = j;
        }
    }

    /**
     * 取出堆中最大的元素，并且替换成元素 e
     * 正常情况下，这是两个操作，即先 extractMax，再 add，复杂度就是连续两个 O(logn)
     * 但是组合起来就可以直接用新元素替换旧元素，然后再 siftDown,复杂度就仅仅是 O(logn)
     * @param e
     * @return
     */
    public E replace(E e) {
        E ret = findMax();
        data.set(0, e);
        siftDown(0);

        return ret;
    }
}