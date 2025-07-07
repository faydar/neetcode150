package problems.maximum_subarray;

public class Solution {

    private int divideAndConquer(int[] nums, int l, int r) {
        if (l > r) {
            return Integer.MIN_VALUE;
        }

        int m = l + (r - l) / 2;
        int currentMaxLeft = 0, currentMaxRight = 0, s = 0;
        for (int i = m - 1; i >= l; i--) {
            s += nums[i];

            if (s > currentMaxLeft) {
                currentMaxLeft = s;
            }
        }

        s = 0;
        for (int i = m + 1; i <= r; i++) {
            s += nums[i];

            if (s > currentMaxRight) {
                currentMaxRight = s;
            }
        }

        return Math.max(Math.max(divideAndConquer(nums, l, m - 1),
                divideAndConquer(nums, m + 1, r)),
                currentMaxLeft + currentMaxRight + nums[m]);
    }

    public int divideAndConquer(int[] nums) {
        return divideAndConquer(nums, 0, nums.length - 1);
    }

    public int maxSubArray(int[] nums) {
        int currentMax = Integer.MIN_VALUE / 2;
        int result = currentMax;

        for (int i = 0; i < nums.length; i++) {
            currentMax = Math.max(nums[i], currentMax + nums[i]);
            result = Math.max(currentMax, result);
        }

        return result;
    }
}
