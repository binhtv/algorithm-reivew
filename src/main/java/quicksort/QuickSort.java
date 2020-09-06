package quicksort;

import java.util.Arrays;

/**
 * Quicksort implementation
 */
public class QuickSort {
    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private static int partition(int[] nums, int l, int r) {
        int p = (r + l) / 2, i = l, j = r - 1;
        int pivot = nums[p];
        swap(nums, p, r);
        while (i <= j) {
            while (i < r && nums[i] < pivot) {
                i++;
            }
            while (j >= l && nums[j] > pivot) {
                j--;
            }
            if (i < j) {
                swap(nums, i, j);
                i++;
                j--;
            }
        }
        swap(nums, i, r);

        return i;
    }

    private static void inPlaceQuickSort(int[] nums, int l, int r) {
        if (l > r) {
            return;
        }
        int i = partition(nums, l, r);
        if (l + 1 < i - 1) {
            inPlaceQuickSort(nums, l, i - 1);
        }
        if (i + 1 < r - 1) {
            inPlaceQuickSort(nums, i + 1, r);
        }
    }

    public static void quickSort(int[] nums) {
        inPlaceQuickSort(nums, 0, nums.length - 1);
    }

    public static int quickSelect(int[] nums, int l, int r, int k) {
        if (k >= 0 && k <= r - l + 1) {
            int pos = partition(nums, l, r);
            if (pos - l == k) {
                return nums[pos];
            }
            if (pos - l > k) {
                return quickSelect(nums, l, pos - 1, k);
            }
            return quickSelect(nums, pos + 1, r, k - pos + l - 1);
        }

        return 0;
    }

    public static void main(String... strings) {
        int[] nums = new int[]{10, 7, 2, 8, 6, 4, 3, 1, 5, 9, 0};
        quickSort(nums);
        System.out.println(Arrays.toString(nums));
        System.out.println(quickSelect(nums, 0, nums.length - 1, 5));
    }
}
