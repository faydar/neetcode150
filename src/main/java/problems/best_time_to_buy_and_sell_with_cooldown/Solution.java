package problems.best_time_to_buy_and_sell_with_cooldown;

import java.util.Arrays;

public class Solution {

    private int[][] d;

    public int rec(int[] prices, int day, int state) {
        if (day < 0) {
            return 0;
        }

        if (d[day][state] != Integer.MIN_VALUE) {
            return d[day][state];
        }

        if (state == 0) {
            // switch from sold to bought, or keep previous bought state
            d[day][state] = Math.max(rec(prices, day - 2, 1) - prices[day], rec(prices, day - 1, 0));
        } else {
            // switch from bought to boughsoldt, or keep previous sold state
            d[day][state] = Math.max(rec(prices, day - 1, 0) + prices[day], rec(prices, day - 1, 1));
        }

        return d[day][state];
    }

    public int maxProfit(int[] prices) {
        d = new int[prices.length][2];

        for (int i = 0; i < d.length; i++) {
            d[i][0] = d[i][1] = Integer.MIN_VALUE;
        }

        d[0][0] = -prices[0];
        d[0][1] = 0;

        var res = rec(prices, prices.length - 1, 1);
        return res;
    }

    public int maxProfit2(int[] prices) {
        int[][] d = new int[prices.length][2];

        if (prices.length == 1) {
            return 0;
        }

        d[0][0] = -prices[0];
        d[0][1] = 0;
        d[1][0] = -Math.min(prices[0], prices[1]);
        d[1][1] = Math.max(0, prices[1] - prices[0]);

        for (int i = 2; i < prices.length; i++) {
            d[i][0] = Math.max(d[i - 2][1] - prices[i], d[i - 1][0]);
            d[i][1] = Math.max(d[i - 1][0] + prices[i], d[i - 1][1]);
        }

        return d[prices.length - 1][1];
    }

    public static void main(String[] args) {
        var res = new Solution().maxProfit2(new int[] { 1, 2, 4 });
        System.out.println(res);
        return;
    }
}
