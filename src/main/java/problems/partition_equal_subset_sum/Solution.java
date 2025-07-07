package problems.partition_equal_subset_sum;

import java.util.Arrays;

public class Solution {

    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();

        if (sum % 2 == 1) {
            return false;
        }

        boolean[] d = new boolean[sum * 2];
        d[0] = true;

        for (int n : nums) {
            for (int i = sum; i >= n; i--) {
                if (d[i - n]) {
                    d[i] = true;
                }
            }
        }

        return d[sum / 2];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().canPartition(new int[] { 1, 2, 2, 5 }));
    }
}
