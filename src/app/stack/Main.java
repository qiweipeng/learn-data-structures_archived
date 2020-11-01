package app.stack;

import java.util.Random;
// import app.stack.LinkedListStack;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test1() {
        // ArrayStack<Integer> stack = new ArrayStack<>();
        LinkedListStack<Integer> stack = new LinkedListStack<>();

        for (int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println(stack);
        }

        // System.out.println(stack);

        stack.pop();

        System.out.println(stack);
    }

    private static void test2() {
        // 通过链表栈和数组栈的对比，两者性能差异不大，因为他们的时间复杂度是在一个级别上的。
        int opCount = 10_000_000;

        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        double time1 = testStack(arrayStack, opCount);
        System.out.println("ArrayStack, time: " + time1 + "s");

        LinkedListStack<Integer> linkedStack = new LinkedListStack<>();
        double time2 = testStack(linkedStack, opCount);
        System.out.println("LinkedListStack, time: " + time2 + "s");
    }

    private static double testStack(Stack<Integer> stack, int opCount) {
        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < opCount; i++) {
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            stack.pop();
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1_000_000_000.0;
    }
}