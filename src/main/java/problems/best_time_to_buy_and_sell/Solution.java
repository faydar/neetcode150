package problems.best_time_to_buy_and_sell;

import java.util.List;
import java.util.Set;

class Solution {

    public static int maxProfit(int[] prices) {
        int res = 0;
        int mini = prices[0];

        for (int i = 0; i < prices.length; i++) {
            res = Math.max(res, prices[i] - mini);
            mini = Math.min(mini, prices[i]);
        }

        System.out.println(res);
        return res;
    }

    public static void main(String[] args) {
        var outputs = List.of(
                maxProfit(new int[] { 2, 4, 1, 11, 7 }),
                maxProfit(new int[] { 2, 1, 2, 1, 0, 1, 2 }),
                maxProfit(new int[] { 10, 1, 5, 6, 7, 1 }),
                maxProfit(new int[] { 1, 2, 11, 4, 7 }),
                maxProfit(new int[] { 3, 2, 6, 5, 0, 3 }),
                maxProfit(new int[] { 2, 1, 4, 5, 2, 9, 7 }),
                maxProfit(new int[] { 1, 2, 4, 2, 5, 7, 2, 4, 9, 0 })

        );

    }
}