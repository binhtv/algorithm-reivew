package twopointers;

/**
 * Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-4,-1,0,3,10]
 * Output: [0,1,9,16,100]
 * Explanation: After squaring, the array becomes [16,1,0,9,100].
 * After sorting, it becomes [0,1,9,16,100].
 * Example 2:
 *
 * Input: nums = [-7,-3,2,3,11]
 * Output: [4,9,9,49,121]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums is sorted in non-decreasing order.
 *
 *
 * Follow up: Squaring each element and sorting the new array is very trivial, could you find an O(n) solution using a different approach?
 */
public class SquareOfSortedArray {
    public static int[] sortedSquares(int[] nums) {
        int start = 0, end = nums.length - 1;
        int index = end;
        int[] result = new int[nums.length];
        while(start <= end) {
            if (nums[start] * nums[start] > nums[end] * nums[end]) {
                result[index--] = nums[start]*nums[start];
                start++;
            } else {
                result[index--] = nums[end]*nums[end];
                end--;
            }
        }
        return result;
    }

    public static void main(String...args) {
        int[] result = sortedSquares(new int[] {-10, -7, -5, -5, 0, 1, 2, 3, 4, 5, 6});
        for(int i : result) {
            System.out.print(i);
            System.out.print(" ");
        }

        System.out.println(5%2);
    }
}
