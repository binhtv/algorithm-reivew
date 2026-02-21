package array;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
    public static void backtrack(List<Integer> tempList, int start, int[] nums, List<List<Integer>> result) {
        result.add(new ArrayList<>(tempList));

        for(int i = start; i < nums.length; i++) {
            if(i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            tempList.add(nums[i]);
            backtrack(tempList, i + 1, nums, result);
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 3};
        final List<List<Integer>> result = new ArrayList<>();
        backtrack(new ArrayList<>(), 0, nums, result);

        System.out.println(result);
    }
}
