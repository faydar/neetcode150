package problems.best_time_to_buy_and_sell;

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

    }
}