package dp;

/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * <p>
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * Example 2:
 * <p>
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 */
public class Robber {
    /**
     * It could be overwhelming thinking of all possibilities on which houses to rob.
     * <p>
     * A natural way to approach this problem is to work on the simplest case first.
     * <p>
     * Let us denote that:
     * <p>
     * f(k) = Largest amount that you can rob from the first k houses.
     * Ai = Amount of money at the ith house.
     * <p>
     * Let us look at the case n = 1, clearly f(1) = A1.
     * <p>
     * Now, let us look at n = 2, which f(2) = max(A1, A2).
     * <p>
     * For n = 3, you have basically the following two options:
     * <p>
     * Rob the third house, and add its amount to the first house's amount.
     * <p>
     * Do not rob the third house, and stick with the maximum amount of the first two houses.
     * <p>
     * Clearly, you would want to choose the larger of the two options at each step.
     * <p>
     * Therefore, we could summarize the formula as following:
     * <p>
     * f(k) = max(f(k – 2) + Ak, f(k – 1))
     * <p>
     * We choose the base case as f(–1) = f(0) = 0, which will greatly simplify our code as you can see.
     * <p>
     * The answer will be calculated as f(n). We could use an array to store and calculate the result, but since at each step you only need the previous two maximum values, two variables are suffice.
     * <p>
     * Time complexity O(n), space complexity O(n)
     *
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //dp[i][0] without robbing the current house i
        //dp[i][1] with robbing the current house i
        int[][] dp = new int[nums.length + 1][2];
        for (int i = 1; i <= nums.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            dp[i][1] = dp[i - 1][0] + nums[i - 1];
        }

        return Math.max(dp[nums.length][0], dp[nums.length][1]);
    }

    /**
     * Time complexity O(n), space complexity O(1)
     *
     * @param nums
     * @return
     */
    public static int robV2(int[] nums) {
        int prevMax = 0;
        int currMax = 0;
        for (int i : nums) {
            int tmp = currMax;
            currMax = Math.max(prevMax + i, currMax);
            prevMax = tmp;
        }

        return currMax;
    }

    public static void main(String... strings) {
        int[] nums = new int[]{2, 7, 9, 3, 1};
        System.out.println(rob(nums));
        System.out.println(robV2(nums));
    }
}
