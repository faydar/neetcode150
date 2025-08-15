package problems.longest_increasing_subsequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    // DP O(n^2)
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

    // O (NlogN)
    // uses binary search to maintain:
    // tails[k] = the smallest possible last element of an increasing subsequence of
    // length k+1.
    // Why smallest last element? Because a smaller tail leaves more room for future
    // numbers to extend the subsequence.
    public int lengthOfLIS2(int[] nums) {
        List<Integer> tails = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int ind = leftMostGreaterOrEqual(tails, nums[i]);
            if (ind == -1) {
                tails.add(nums[i]);
            } else {
                tails.set(ind, nums[i]);
            }
        }

        return tails.size();
    }

    private int leftMostGreaterOrEqual(List<Integer> tails, int num) {
        int l = 0, r = tails.size() - 1;
        int res = -1;

        while (l <= r) {
            int m = l + (r - l) / 2;

            if (tails.get(m) >= num) {
                r = m - 1;
                res = m;
            } else {
                l = m + 1;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        // System.out.println(new Solution().lengthOfLIS(new int[] { 1, 3, 6, 7, 9, 4,
        // 10, 5, 6 }));
        System.out.println(new Solution().lengthOfLIS2(new int[] { 0, 1, 0, 3, 2, 3 }));

    }
}
