package problems.longest_increasing_subsequence;

import java.util.Arrays;

public class Solution {

    public int lengthOfLIS(int[] nums) {
        int[] d = new int[nums.length];
        int res = 1;

        Arrays.fill(d, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > nums[i]) {
                    d[j] = Math.max(d[j], d[i] + 1);
                    res = Math.max(res, d[j]);
                }
            }
        }

        return res;
    }

    // solve in nlogn as well
    public int lengthOfLIS2(int[] nums) {

    }

    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLIS(new int[] { 1, 3, 6, 7, 9, 4, 10, 5, 6 }));
    }
}
