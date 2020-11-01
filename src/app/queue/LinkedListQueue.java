package app.queue;

/**
 * LinkedListQueue
 * 
 * 这里我们使用链表实现队列。
 * 由于队列需要从一端添加，另一端删除，对于链表来说，我们需要给它先添加一个 tail 标记，类似 head 标记那样，这样我们从末尾添加元素就也是 O(1) 级别的了。
 * 但是有一个问题是，即使添加了 tail 标记，在从尾部删除一个元素的时候，我们无法快速找到 tail 之前的那个节点。
 * 
 * 所以合理的设计队列的方式就是，从尾部只负责添加元素，在链表的头部负责删除元素，也就是出队。
 * 
 * 这里我们设计的链表就不使用 dummyHead 了，但是要注意链表为空的情况。
 */
public class LinkedListQueue<E> implements Queue<E> {

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

        // public Node() {
        //     this(null);
        // }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node head, tail;
    private int size;

    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E e) {
        if (tail == null) {
            tail = new Node(e);
            head = tail;
        } else {
            tail.next = new Node(e);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }

        Node retNode = head;
        head = head.next;
        retNode.next = null;
        if (head == null) {
            tail = null;
        }
        size--;
        return retNode.e;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
           throw new IllegalArgumentException("Queue is empty.");
        }

        return head.e;
    }

    @Override
    public String toString() {
        
        StringBuilder res = new StringBuilder();
        res.append("Queue: front ");

        Node cur = head;
        while (cur != null) {
            res.append(cur + "->");
            cur = cur.next;
        }

        res.append("NULL tail");

        return res.toString();
    }
}