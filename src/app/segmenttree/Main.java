package app.segmenttree;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        
        Integer[] nums = { -2, 0, 3, -5, 2, -1 };
        SegmentTree<Integer> segTree = new SegmentTree<>(nums, new Merger<Integer>() {
            @Override
            // 用于求和的线段树。
            public Integer merge(Integer a, Integer b) {
                return a + b;
            }
        });

        System.out.println(segTree.query(0, 2)); // 1
        System.out.println(segTree.query(2, 5)); // -1
        System.out.println(segTree.query(0, 5)); // -3

        // System.out.println(segTree);
    }
}