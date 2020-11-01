package app.array;

/**
 * Array
 * 
 * 数组最大的优点是可以快速查询，scores[2]
 * 因此数组最好应用于 “索引有语意” 的情况
 * 根据 Java 提供的静态数组，我们自己创建一个名叫 Array 的动态数组类，添加更多的功能。
 * 这个数组可以添加元素。
 * 
 * 
 * 关于时间复杂度：
 * 增 O(n) 
 * 删 O(n)
 * 改 已知索引 O(1) 未知索引O(n)
 * 查 已知索引 O(1) 未知索引O(n)
 * 
 * 所以说，如果数组的索引有语义的情况下，改查的性能就会很高。
 * 而对于增删，虽然如果增删的动作如果都是最后一个元素的话，复杂度都是 O(1) 级别的，但是会有可能有数组容量的 resize 操作
 */
public class Array<E> {

    // 使用一个静态数组来存放元素，也就是当前数组的容量。
    private E[] data;
    // 数组中存放元素的个数。
    private int size;

    /**
     * 构造函数。
     * @param capacity 数组初始容量。
     */
    @SuppressWarnings("unchecked")
    public Array(int capacity){
        // Java 无法直接设置一个泛型数组，所以设置一个 Object 类型的数组，然后强制类型转换。其中，Object 是所有类的父类。
        data = (E[])new Object[capacity];
        size = 0;
    }

    /**
     * 无需传入数组容量的构造函数。默认初始容量为 10。
     */
    public Array(){
        this(10);
    }

    /**
     * 通过一个静态数组构造动态数组。
     * @param arr 静态数组。
     */
    @SuppressWarnings("unchecked")
    public Array(E[] arr) {
        data = (E[])new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        size = arr.length;
    }

    /**
     * 获取数组的容量。
     * 
     * @return 数组的容量。
     */
    public int getCapacity(){
        return data.length;
    }

    /**
     * 获取数组中元素的个数。
     * 
     * @return 数组中元素的个数。
     */
    public int getSize(){
        return size;
    }

    /**
     * 数组是否为空。
     * 
     * @return 数组是否为空。
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 在指定索引添加一个元素。
     * 时间复杂度最大为 O(n)，最小为 O(1)。总体计算应该算出各个可能性的期望复杂度，最终是 O(n/2)，由于常数忽略，所以还是 O(n)。
     * 
     * @param index
     * @param e
     */
    public void add(int index, E e) {

        // 索引必须合法
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
        }

        // 如果数组已经满了，则无法添加新元素，抛出异常。
        if (size == data.length) {

            // 修改后，如果数组已经满了，则不再抛出异常，而是容量翻倍。
            resize(2 * data.length);
            // throw new IllegalArgumentException("Add failed. Array is full.");
        }

        // 将索引后面的元素依次向后挪动一个位置，应该从最后一个元素开始挪起。
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }

        data[index] = e;
        size++;
    }

    /**
     *
     * 向数组末尾添加元素。
     * 时间复杂度 O(1)，因为算法运行时间和输入数据规模无关，是一个常数，也就是和数组元素个数无关。
     * 
     * @param e 元素。
     */
    public void addLast(E e) {

        // 尾部添加一个元素可以调用 add 方法。
        add(size, e);
        // // 如果数组已经满了，则无法添加新元素，抛出异常。
        // if (size == data.length) {
        // throw new IllegalArgumentException("AddLast failed. Array is full.");
        // }
        // data[size] = e;
        // size++;
    }

    /**
     * 在数组开头插入一个元素。
     * 时间复杂度 O(n)，因为需要向后挪动 n 个元素，数组中所有内容都要向后挪动。
     * 
     * @param e 插入的元素。
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 获取某个元素。
     * 时间复杂度 O(1)
     * 
     * @param index
     * @return
     */
    public E get(int index){
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        }

        return data[index];
    }

    /**
     * 获取数组最后一个元素。
     * @return
     */
    public E getLast() {
        return get(size - 1);
    }

    /**
     * 获取数组第一个元素。
     * @return
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * 修改 index 索引位置的元素为 e
     * 时间复杂度 O(1)。这也是数组最大的优势，支持随机访问，知道索引的情况下改元素超快。
     * 
     * @param index
     * @param e
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        }

        data[index] = e;
    }

    /**
     * 查找数组中是否包含某个元素。
     * 时间复杂度 O(n)
     * 
     * @param e
     * @return
     */
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找元素 e 所在的第一个索引，如果不存在该元素，则返回 -1。
     * 时间复杂度 O(n)
     * 
     * @param e
     * @return
     */
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 从数组中删除指定索引的元素，并将其返回。
     * 时间复杂度 O(n)
     * 
     * @param index
     * @return
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Index is Illegal.");
        }

        E ret = data[index];

        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        data[size] = null; // 可写可不写，写的话可以优化一点点内存。

        // 缩容采取 lazy 的策略，元素变为 1/4 时再进行缩容。
        // 这样的目的是防止复杂度震荡。
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }

        return ret;
    }

    /**
     * 删除第一个元素。
     * 时间复杂度 O(n)
     * 
     * @return 删除的元素。
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 删除最后一个元素。
     * 时间复杂度 O(1)
     * 
     * @return 删除的元素。
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 如果数组包含元素 e，则删除第一个 e。
     * 
     * @param e
     */
    public void removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    /**
     * 交换数组中指定两个索引的元素
     * @param i
     * @param j
     */
    public void swap(int i, int j) {
        if (i < 0 || i >= size || j < 0 || j >= size) {
            throw new IllegalArgumentException("Index is illegal.");
        }

        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    /**
     * 改变数组容量。
     * 
     * 对于该算法复杂度的分析，最合适的应该是均摊复杂度分析，而不应该使用最坏的情况。因为 resize 操作是不可能每次添加元素都触发的。
     * 由于不是每次操作都触发，则这次的耗时可以分摊到每一次操作中。
     * 使用均摊复杂度分析的复杂度是 O(1) 级别的
     * 
     * 但是，如果我们正好在扩容和缩容的边界反复交替调用 addLast 和 removeLast 的话，复杂度就一直是 O(n) 级别的，这叫做复杂度震荡。
     * 为了防止复杂度震荡，我们可以在缩容的时候采取 lazy 的策略，即元素个数到 1/2 容量时先不要锁容，而等到更小，比如 1/4 的时候再进行。
     * 
     * @param newCapacity 需要变更的容量。
     */
    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {

        if (newCapacity < size) {
            throw new IllegalArgumentException("New capacity is Illegal.");
        }

        E[] newData = (E[])new Object[newCapacity];

        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d\n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();
    }
}
