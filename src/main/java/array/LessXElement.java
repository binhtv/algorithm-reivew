package array;

import java.util.Arrays;
import java.util.Stack;

public class LessXElement {
    private int[] previousLess;
    private int[] nextLess;
    private int[] nums;

    public LessXElement(int[] _nums) throws Exception {
        if (_nums == null) {
            throw new Exception("Invalid argument");
        }
        nums = _nums;
        previousLess = new int[nums.length];
        nextLess = new int[nums.length];
        Arrays.fill(previousLess, -1);
        Arrays.fill(nextLess, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                stack.pop();
            }
            previousLess[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        stack.clear();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                nextLess[stack.pop()] = i;
            }
            stack.push(i);
        }
    }

    public void printPreviousLess() {
        for (int i = 0; i < previousLess.length; i++) {
            System.out.println(String.format("%d -> %d", nums[i], previousLess[i] == -1 ? -1 : nums[previousLess[i]]));
        }
    }

    public void printNextLess() {
        for (int i = 0; i < nextLess.length; i++) {
            System.out.println(String.format("%d -> %d", nums[i], nextLess[i] == -1 ? -1 : nums[nextLess[i]]));
        }
    }

    public static void main(String... strings) throws Exception {
        int[] nums = new int[]{3, 7, 8, 4};
        LessXElement lessXElement = new LessXElement(nums);
        lessXElement.printPreviousLess();
        System.out.println("-------");
        lessXElement.printNextLess();
    }
}
