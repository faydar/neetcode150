package problems.target_sum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    private int rec(int[] nums, int index, int sum, int target) {
        if (index == nums.length) {
            if (sum == target) {
                return 1;
            }

            return 0;
        }

        return rec(nums, index + 1, sum + nums[index], target) + rec(nums, index + 1, sum - nums[index], target);
    }

    public int targetSumRec(int[] nums, int target) {
        return rec(nums, 0, 0, target);
    }

    public int findTargetSumWays(int[] nums, int target) {
        Map<Integer, Integer> m = new HashMap<>();

        m.put(0, 1);

        for (int n : nums) {
            Map<Integer, Integer> nm = new HashMap<>();

            for (Map.Entry<Integer, Integer> e : m.entrySet()) {
                int sum = e.getKey();
                int numWaysToSum = e.getValue();

                nm.put(sum + n, nm.getOrDefault(sum + n, 0) + numWaysToSum);
                nm.put(sum - n, nm.getOrDefault(sum - n, 0) + numWaysToSum);
            }

            m = nm;
        }

        return m.getOrDefault(target, 0);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findTargetSumWays(new int[] { 1, 1, 1, 1, 1 }, 3));
    }
}
