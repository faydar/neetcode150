package utils;

public class QuickSelect {

    public static void main(String[] args) {
        var res = QuickSelect.kthSmallest(new int[] { 7, 10, 4, 3, 20, 1 }, 1);
        return;
    }

    public static int kthSmallest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, k);
    }

    /**
     * Quickselect is closely related to Quicksort:
     * 
     * Choose a pivot element.
     * Partition the array:
     * All elements smaller than the pivot go to the left,
     * Larger go to the right.
     * 
     * After partitioning:
     * If the pivot is at index k - 1, return it.
     * If pivot index > k - 1, recurse on the left.
     * If pivot index < k - 1, recurse on the right.
     * 
     * Unlike Quicksort, only one side of the partition is processed.
     * 
     */
    private static int quickSelect(int[] nums, int l, int r, int k) {
        if (l == r) {
            return nums[l];
        }

        int pivotIndex = partition(nums, l, r);

        if (pivotIndex == k - 1) {
            return nums[pivotIndex];
        }

        if (pivotIndex > k - 1) {
            return quickSelect(nums, l, pivotIndex - 1, k);
        }

        return quickSelect(nums, pivotIndex, r, k);
    }

    /**
     * Partition the array:
     * All elements smaller than the pivot go to the left,
     * Larger go to the right.
     * 
     */
    private static int partition(int[] nums, int l, int r) {
        int pivot = nums[r];
        int pivotCorrectIndex = l;

        for (int j = l; j < r; j++) {
            if (nums[j] < pivot) {
                swap(nums, pivotCorrectIndex, j);
                pivotCorrectIndex++;
            }
        }

        swap(nums, pivotCorrectIndex, r);

        return pivotCorrectIndex;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
