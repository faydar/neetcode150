package problems.house_robber_ii;

import java.util.Arrays;

public class Solution {
    public int _rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        int[] d = new int[nums.length];

        d[0] = nums[0];
        d[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            d[i] = Math.max(d[i - 1], nums[i] + d[i - 2]);
        }

        return d[nums.length - 1];
    }

    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        var result1 = _rob(Arrays.copyOfRange(nums, 0, nums.length - 1));
        var result2 = _rob(Arrays.copyOfRange(nums, 1, nums.length));

        return Math.max(result1, result2);
    }
}
