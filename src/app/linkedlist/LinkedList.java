package app.linkedlist;

/**
 * LinkedList
 * 
 * 之前学习的动态数组、栈、队列底层都是依托于静态数组，通过 resize 解决容量问题。
 * 链表是最简单的动态数据结构。
 * 
 * 优点：
 * 真正的动态，不需要处理固定容量的问题。
 * 缺点：
 * 丧失了随机访问的能力。（这也是数组的优点）
 * 
 * 链表各个操作时间复杂度：
 * addLast(e)     O(n)
 * addFirst(e)    O(1)
 * add(e)         O(n)
 * 
 * removeLast(e)  O(n)
 * removeFirst(e) O(1)
 * remove(e)      O(n)
 * 
 * set(index, e)  O(n)
 * 
 * get(index)     O(n)
 * contains(e)    O(n)
 * 
 * 也就是说，对于链表来说，增删改查全是 O(n) 级别的
 * 但是需要注意，如果只是对链表头进行操作，那么不管是增删还是查，都是 O(1) 级别的
 * 
 * 链表的复杂度主要是花费在查找节点上，而具体删除或者插入的操作是很方便的，这点和数组不同，数组体现在查找特别快，但是删除或者插入比较麻烦。
 * 
 * 链表的增删改查操作可以尝试使用递归实现。
 */
public class LinkedList<E> {

    /**
     * Node
     * 链表的节点，设置为一个内部的私有类，只在链表类内部可以访问到。
     */
    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    // 第一个节点，即链表的头。对于链表来说，我们只跟踪链表的头，并没有跟踪链表的尾巴。
    // 这一点和数组不同，数组我们往往设计一个变量去跟踪数组的尾部。
    // 为了添加元素的统一，我们在真正的 head 前放置一个虚拟头节点。
    // private Node head; 
    private Node dummyHead;
    // 元素个数。
    int size;

    public LinkedList() {
        // head = null;
        dummyHead = new Node();
        size = 0;
    }

    /**
     * 获取链表中元素的个数。
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 链表是否为空。
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 在链表的指定 index 位置插入新的元素 e
     * 这个操作在链表中是一个不常用操作，主要是练习使用。
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }

        // 如果为填表头添加元素需要特殊处理，那么我们可以使用为链表设立虚拟头节点的方式解决。
        // if (index == 0 ) {
        //     addFirst(e);
        // } else {
        //     Node prev = head;
        //     for (int i = 0; i < index - 1; i++) {
        //         prev = prev.next;
        //     }

        //     // Node node = new Node(e);
        //     // node.next = prev.next;
        //     // prev.next = node;
        //     prev.next = new Node(e, prev.next);

        //     size++;
        // }

        // 因为虚拟头节点的添加，我们不再需要 if-else
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        prev.next = new Node(e, prev.next);
        size++;
    }

        /**
     * 为链表头添加一个节点。
     * 对于链表来讲，在头部添加一个节点是最容易的。
     */
    public void addFirst(E e) {
        // Node node = new Node(e, head);
        // head = node;

        // size++;
        add(0, e);
    }

    /**
     * 在链表末尾添加元素。
     * @param e
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 获得链表 index 位置的元素。
     * 这也不是一个常用操作，仅作练习使用。
     * @param index
     * @return
     */
    public E get(int index) {

        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Illegal index.");
        }

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }

        return cur.e;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    /**
     * 更新 index 位置的元素。
     * 不常用操作，练习使用。
     * @param index
     * @param e
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Illegal index.");
        }

        Node cur = dummyHead.next;

        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }

        cur.e = e;
    }

    /**
     * 查找链表中是否有元素 e
     * @param e
     * @return
     */
    public boolean contains(E e) {

        Node cur = dummyHead.next;

        // for (int i = 0; i < size; i++) {
        //     if (cur.e.equals(e)) {
        //         return true;
        //     }
        //      cur = cur.next;
        // }
        // return false;

        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    /**
     * 删除指定 index 位置的元素，并返回所删除的元素。
     * 不常用操作，练习使用。
     * @param index
     * @return
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Illegal index.");
        }

        Node prev = dummyHead;

        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        Node delNode = prev.next;
        prev.next = delNode.next;
        delNode.next = null;
        size--;

        return delNode.e;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    // 从链表中删除元素 e
    public void removeElements(E e) {
        
        Node prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.e.equals(e)) {
                break;
            }
            prev = prev.next;
        }

        if (prev.next != null) {
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
        }
    }

    @Override
    public String toString() {
        
        StringBuilder res = new StringBuilder();

        Node cur = dummyHead.next;
        while (cur != null) {
            res.append(cur + "->");
            cur = cur.next;
        }

        res.append("NULL");

        return res.toString();
    }
}