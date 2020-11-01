package app.set;

// import java.util.ArrayList;

/**
 * Main
 * 
 * 对于集合来说，主要涉及增、查、删的操作，不涉及改的操作。
 * 二分搜索树集合和链表集合的对比，由于需要涉及文件导入，就不做对比，直接进行分析。
 * 
 *               链表集合        二分搜索树集合   平均    最差
 * 增 add          O(n)              O(h)    O(logn)  O(n)
 * 查 contains     O(n)              O(h)    O(logn)  O(n)
 * 删 remove       O(n)              O(h)    O(logn)  O(n)
 * 
 * 对于链表集合来说，本身在链表头增加一个元素或者删除一个元素，都是 O(1) 级别的
 * 但是为了查重，都需要遍历查询一边，链表的查询是 O(n) 级别的，所以最终增删也是 O(n) 级别的
 * 
 * 对于二分搜索树集合，它的增查删其实只是寻找了树的层树，我们用 h 表示
 * 最好的情况，也就是树是满的情况下层数和元素数的关系是 2^h - 1 = n，h = log(n + 1)    (log底是 2)
 * 那么最好情况的复杂度就是 O(logn) 级别的，这其实很接近 O(1) 级别，在数据量大的时候要比 O(n) 好得多
 * 
 * 但是对于二分搜索树来说，如果添加元素的时候恰好是从小到大或者相反地插入元素，这棵树就退化成链表了
 * 要解决这个问题，就要使用平衡二叉树。
 * 
 * 
 * 二分搜索树集合属于有序集合，有序集合都是基于搜索树实现的。
 * 如果我们需要使用集合的有序性这一特点，就要考虑使用搜索树来实现。
 * 比如有序集合更适合得到集合中的最大元素，最小元素等。
 * 
 * 链表集合输入无序集合。无序集合也可以使用哈希表来实现，哈希表实现的集合性能比搜索树实现的更好。
 * 
 */
public class Main {

    public static void main(String[] args) {
        // ArrayList<String> words = new ArrayList<>();

        // words.add("a");
        // words.add("b");
        // words.add("e");
        // words.add("a");

        BSTSet<String> set = new BSTSet<>();

        set.add("a");
        set.add("b");
        set.add("e");
        set.add("a");

        System.out.println(set.getSize());
        System.out.println(set.isEmpty());
    }
}