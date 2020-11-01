package app.bst;

import java.util.Random;
import java.util.ArrayList;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();

        Random random = new Random();
        int n = 1000;
        for (int i = 0; i < n; i++) {
            bst.add(random.nextInt(10000));
        }

        ArrayList<Integer> nums = new ArrayList<>();
        while (!bst.isEmpty()) {
            nums.add(bst.removeMin());
        }
        System.out.println(nums);

        // int[] nums = { 5, 3, 6, 8, 4, 2};
        // for (int num : nums) {
        //     bst.add(num);
        // }

        // bst.levelOrder();

        // bst.preOrder();
        // System.out.println();

        // bst.preOrderNR();

        // bst.inOrder();

        // System.out.println(bst);
    }
}