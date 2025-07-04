package problems.house_robber;

public class Solution {

    public int rob(int[] nums) {
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
}
