package app.segmenttree;

/**
 * SegmentTree
 * 经典面试问题，一面墙，每次可以在其中一端区间进行染色，染色可以覆盖之前的染色，若干次染色后，问某个区间内有几种颜色？
 * 
 * 可以使用数组解决这个问题，只需要遍历指定区间，复杂度为 O(n)
 * 而如果使用线段树解决，复杂度为 O(logn)
 * 
 *          使用数组实现   使用线段树
 * 区间更新     O(n)       O(logn)
 * 区间查询     O(n)       O(logn)
 * 
 * 
 * 对于线段树，不考虑添加和删除操作，线段树解决的问题，区间本身是固定的
 * 假定研究的问题是数组区间求和，数组共 8 个元素，则构造的线段树
 *                     A[0...7]
 *                   /          \
 *           A[0...3]             A[4...7]
 *          /     \               /       \
 *   A[0...1]    A[2...3]    A[4...5]    A[6...7]
 *    /    \       /   \      /     \      /    \
 *  A[0]  A[1]   A[2] A[3]  A[4]  A[5]   A[6]   A[7]
 * 
 * 根节点存储全部区间的和，A[0...3]存储前半段的和，以此类推。
 * 
 * 如果数组是 10 个元素，线段树则表示为
 *                     A[0...9]
 *                   /          \
 *           A[0...4]             A[5...9]
 *          /     \               /       \
 *   A[0...1]    A[2...4]    A[5...6]    A[7...9]
 *    /    \       /   \      /     \      /    \
 *  A[0]  A[1]   A[2] A[3,4] A[5]  A[6]  A[7]   A[8,9]
 *                     /  \                     /    \
 *                  A[3] A[4]                 A[8]  A[9]
 * 
 * 线段树不是满二叉树，更不是完全二叉树。
 * 线段树是一棵平衡二叉树。所谓平衡二叉树，是指一棵树最大深度和最小深度之差最多为 1。
 * 所以堆也是平衡二叉树，完全二叉树就一定是平衡二叉树。但是二分搜索树就不一定是平衡二叉树。
 * 平衡二叉树一定不会退化成链表。
 * 平衡二叉树可以近似看作一个满二叉树，也可以使用数组表示。
 * 
 * 
 * 对于线段树来说，如果使用数组来表示，如果区间有 n 个元素，那么数组需要 4n 的空间来存储。对于线段树我们不考虑添加元素，即区间固定。
 * 所以创建线段树复杂度为 O(n)，准确的讲是 O(4n)
 * 
 * 我们这里实现的线段树只能实现单个元素更新，没有实现区间更新。比如说，我们希望区间 [2,5]中所有元素 +3，在线段树中，需要将这个叶子节点以及他们的父节点都进行更新，复杂度会变为 O(n)级别，比较慢。一个方式是进行懒惰更新，lazy更新，我们只把线段树中具体到相应区间的节点进行更新，其下面的子节点先不进行更新，而使用一个lazy数组记录未更新的内容，之后如果进行查询时先查找 lazy 数组看是否有未更新的内容，如果有再更新一下。
 * 
 * 线段树不仅仅适用于一维，二维甚至三维的区间问题都可以使用线段树解决。
 */
public class SegmentTree<E> {

    private E[] tree;
    private E[] data;
    // 融合器，区间中的元素进行何种融合存到线段树中。
    private Merger<E> merger;

    /**
     * 构造函数。
     * @param arr 传入的数组。
     * @param merger 融合器。
     */
    @SuppressWarnings("unchecked")
    public SegmentTree(E[] arr, Merger<E> merger) {

        this.merger = merger;

        data = (E[])new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }

        // 线段树所使用数组空间长度应该是传入数组长度的 4 倍
        tree = (E[])new Object[4 * arr.length];
        buildSegmentTree(0, 0, data.length - 1);
    }

    /**
     * 在 treeIndex 的位置创建表示区间[l...r]的线段树
     * @param treeIndex 创建的线段树根节点所在的索引
     * @param l 区间的左边
     * @param r 区间的右边
     */
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    // 区间长度。
    public int getSize() {
        return data.length;
    }

    // 获得指定索引的元素。
    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        return data[index];
    }

    // 左孩子索引。
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    // 右孩子索引。
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    // 返回区间 [queryL, queryR]的值
    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length || queryL > queryR) {
            throw new IllegalArgumentException("Index is illegal.");
        }

        return query(0, 0, data.length - 1, queryL, queryR);
    }

    // 在以treeIndex 为根的线段树中[l...r]的范围内，搜索区间 [queryL...queryR]的值
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }

        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (queryL >= mid + 1) {
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) {
            return query(leftTreeIndex, l, mid, queryL, queryR);
        }

        E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        return merger.merge(leftResult, rightResult);
    }

    // 将index位置的值，更新为e
    public void set(int index, E e){
        if(index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal");
        }

        data[index] = e;
        set(0, 0, data.length - 1, index, e);
    }

    // 在以treeIndex为根的线段树中更新index的值为e
    private void set(int treeIndex, int l, int r, int index, E e){

        if(l == r){
            tree[treeIndex] = e;
            return;
        }

        int mid = l + (r - l) / 2;
        // treeIndex的节点分为[l...mid]和[mid+1...r]两部分

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if(index >= mid + 1) {
            set(rightTreeIndex, mid + 1, r, index, e);
        } else {  // index <= mid
            set(leftTreeIndex, l, mid, index, e);
        }

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append('[');
        for(int i = 0; i < tree.length; i ++){
            if(tree[i] != null) {
                res.append(tree[i]);
            } else {
                res.append("null");
            }

            if(i != tree.length - 1) {
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();
    }
}