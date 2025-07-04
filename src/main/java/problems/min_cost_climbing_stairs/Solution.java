package problems.min_cost_climbing_stairs;


public class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int[] d = new int[cost.length + 1];

        for (int i = 2; i <= cost.length ; i++) {
            d[i] = Math.min(d[i - 1] + cost[i - 1], d[i - 2] + cost[i - 2]);
        }

        return d[d.length - 1];
    }
}
