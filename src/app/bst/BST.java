package app.bst;

import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;


/**
 * 关于二叉树：
 * 二叉树名词：左孩子、右孩子、左子树、右子树、父亲节点、叶子节点
 * 二叉树具有唯一根节点。
 * 每个节点最多有两个孩子节点；每个节点最多有一个父亲节点。
 * 二叉树具有天然递归性，左子树、右子树都是一个二叉树。
 * 二叉树不一定是满的，空也是一个二叉树。
 * 
 * BST 即 Binary Search Tree 二分搜索树
 * 
 * 二分搜索树特点：
 * 首先是一棵二叉树；
 * 二分搜索树每一个节点的值都大于其左子树中所有节点的值，都小于其右子树中所有节点的值。
 * 所存储的元素必须具有可比较性。
 * 
 * 我们实现的这个二分搜索树不包含重复元素
 */

// 这里这个二分搜索树需要满足泛型，同时，这个类型必须是可比较的，因此，这个类型 E 需要满足 Comparable 这个接口。
// 类似地，可以和 Swift 中的协议做对比。
public class BST<E extends Comparable<E>> {

    // 在类的内部定义一个二分搜索树的节点类
    private class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    // 根节点
    private Node root;
    // 元素数量
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 给树添加新元素。
     * @param e
     */
    public void add(E e) {

        // if (root == null) {
        //     root = new Node(e);
        //     size++;
        // } else {
        //     add(root, e);
        // }

        // 改进后
        root = add(root, e);
    }

    /**
     * 向以 node 为根的二分搜索树中插入元素 E， 递归算法。
     * @param node
     * @param e
     */
    // private void add(Node node, E e) {

    //     if (e.equals(node.e)) {
    //         return;
    //     } else if (e.compareTo(node.e) < 0 && node.left == null) {
    //         node.left = new Node(e);
    //         size++;
    //         return;
    //     } else if (e.compareTo(node.e) > 0 && node.right == null) {
    //         node.right = new Node(e);
    //         size++;
    //         return;
    //     }

    //     if (e.compareTo(node.e) < 0) {
    //         add(node.left, e);
    //     } else {
    //         add(node.right, e);
    //     }
    // }

    /**
     * 改进后的递归函数。
     * @param node
     * @param e
     * @return
     */
    private Node add(Node node, E e) {

        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }

        return node;
    }

    /**
     * 查看二分搜索树中是否包含元素 e
     * @param e
     * @return
     */
    public boolean contains(E e) {
        return contains(root, e);
    }

    /**
     * 私有的递归方法。
     * @param node
     * @param e
     * @return
     */
    private boolean contains(Node node, E e) {

        if (node == null) {
            return false;
        }
        
        if (e.compareTo(node.e) == 0) {
            return true;
        } else if(e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }

    /**
     * 二分搜索树的前序遍历。
     * 前序遍历就是先访问节点，然后访问左右子树。
     */
    public void preOrder() {
        preOrder(root);
    }

    /**
     * 二分搜索树前序遍历私有递归方法。
     * @param node
     */
    private void preOrder(Node node) {

        if (node == null) {
            return;
        }

        System.out.println(node.e);

        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 非递归写法的前序遍历。
     */
    public void preOrderNR() {
        Stack<Node> stack = new Stack<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);

            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    /**
     * 二分搜索树的中序遍历。中序遍历最重要的特点就是，它就是元素从小到大的排序。
     * 中序遍历就是先遍历左子树，再遍历根节点，再遍历右子树。
     */
    public void inOrder() {
        inOrder(root);
    }

    /**
     * 中序遍历递归方法。
     * @param node
     */
    private void inOrder(Node node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    /**
     * 二分搜索树的后序遍历。
     * 后序遍历就是先遍历左右子树，再遍历根节点。
     * 后序遍历的一个应用是为二分搜索树释放内存。
     */
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);
        inOrder(node.right);
        System.out.println(node.e);
    }

    /**
     * 层序遍历。顾名思义，就是从跟节点开始一层一层遍历，每层从左到右。
     * 层序遍历一般使用非递归的算法完成，需要使用队列。这也是队列的一个不错的应用。
     * 
     * 前序遍历、中序遍历、后序遍历都是深度优先遍历，而层序遍历是广度优先遍历。
     */
    public void levelOrder() {
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            Node cur = q.remove();
            System.out.println(cur.e);

            if (cur.left != null) {
                q.add(cur.left);
            }

            if (cur.right != null) {
                q.add(cur.right);
            }
        }
    }

    /**
     * 寻找二分搜索树的最小元素。
     */
    public E minimum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }

        return minimum(root).e;
    }

    private Node minimum(Node node) {

        if (node.left == null) {
            return node;
        }

        return minimum(node.left);
    }

    /**
     * 寻找二分搜索树的最大元素。
     */
    public E maximum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty.");
        }

        return maximum(root).e;
    }

    private Node maximum(Node node) {

        if (node.right == null) {
            return node;
        }

        return maximum(node.right);
    }

    /**
     * 从二分搜索树中删除最小值所在的节点，返回最小值。
     * 注意点就是，如果最小值所在节点还存在右子树的话，需要把右子树的根节点接到该节点的位置。
     * @return
     */
    public E removeMin() {
        E ret = minimum();

        root = removeMin(root);

        return ret;
    }

    /**
     * 删除以 node 为根的二分搜索树的最小节点，并返回删除节点后新的二分搜索树的根
     * @param node
     * @return
     */
    private Node removeMin(Node node) {
        
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 从二分搜索树中删除最大值所在的节点，返回最大值。
     * 注意点就是，如果最大值所在节点还存在左子树的话，需要把左子树的根节点接到该节点的位置。
     * @return
     */
    public E removeMax() {
        E ret = maximum();

        root = removeMax(root);

        return ret;
    }

    /**
     * 删除以 node 为根的二分搜索树的最大节点，并返回删除节点后新的二分搜索树的根
     * @param node
     * @return
     */
    private Node removeMax(Node node) {
        
        if (node.right == null) {

            // Java 里类的赋值是完全拷贝？
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = removeMin(node.right);
        return node;
    }

    /**
     * 从二分搜索树中删除元素为 e 的节点。
     * @param e
     */
    public void remove(E e) {
        root = remove(root, e);
    }

    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }

        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else {
            // 待删除节点右子树为空
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            // 待删除节点右子树为空
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            // 待删除节点左右子树均不为空
            // 找到比待删除节点大的最小节点，即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置。
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }
    }
 
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        generateBSTString(root, 0, res);
        return res.toString();
    }

    // 生成以 node 为根节点，深度为 depth 的描述二叉树的字符串。
    private void generateBSTString(Node node, int depth, StringBuilder res) {

        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < depth; i++) {
            res.append("--");
        }

        return res.toString();
    }
}