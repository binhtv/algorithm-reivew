package array;

/**
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 *
 * Note that you must do this in-place without making a copy of the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [0]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 104
 * -231 <= nums[i] <= 231 - 1
 *
 *
 * Follow up: Could you minimize the total number of operations done?
 */
public class MoveZeros {
    private static void moveZeros(int[] nums) {
        int l = 0, r = 1;
        while(r < nums.length) {
            if(nums[l] == 0 && nums[r] != 0) {
                int tmp = nums[l];
                nums[l] = nums[r];
                nums[r] = tmp;
            }
            if(nums[l] != 0) {
                l++;
            }
            if(nums[r] == 0 || l == r) {
                r++;
            }
        }
    }

    public static void main(String...args) {
//        int[] nums = new int[] {4, 2, 4, 0, 0, 3, 0, 5, 1, 0};
//        int[] nums = new int[] {1};
//        int[] nums = new int[] {0};
//        int[] nums = new int[] {1,1,1,1,1,1};
        int[] nums = new int[] {1,1,1,1,1,1, 0,0,0,0,0};


        moveZeros(nums);
        for(int i : nums) {
            System.out.print(i);
            System.out.print(" ");
        }
    }
}
