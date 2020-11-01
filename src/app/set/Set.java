package app.set;

/**
 * Set
 * 集合的应用，比如词汇量统计，客户统计等。
 * 
 * 对于集合来说，主要涉及增、查、删的操作，不涉及改的操作。
 * 
 * 集合分为有序集合与无序集合；二分搜索树实现的集合，包括 Java 中的 TreeSet 使用红黑树实现，都是有序集合，因为这个集合中的元素是有序排列的。
 * 但是很多时候可能使用集合不会使用到集合到有序性，那么性能更好的无序集合就更适合。这里使用链表实现的集合虽然也是无序的，但是性能不好，但是如果使用哈希表实现，性能就很好了。
 */
public interface Set<E> {

    /**
     * 添加元素 e
     * @param e
     */
    void add(E e);

    /**
     * 删除元素 e
     * @param e
     */
    void remove(E e);

    /**
     * 查看集合是否包含元素 e
     * @param e
     * @return
     */
    boolean contains(E e);

    /**
     * 查看集合中元素的数量
     * @return
     */
    int getSize();

    /**
     * 查看当前集合是否为空。
     * @return
     */
    boolean isEmpty();
}