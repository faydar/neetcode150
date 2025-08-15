package problems.coin_change_ii;

import java.util.Arrays;

public class Solution {

    public int rec(int remaining, int ind, int[] coins, int[][] d) {
        if (d[remaining][ind] >= 0) {
            return d[remaining][ind];
        }

        if (ind >= coins.length) {
            return 0;
        }

        int res = 0;

        if (remaining >= coins[ind]) {
            res += rec(remaining, ind + 1, coins, d); // don't take the coin (meaning we need to switch to next coin)
            res += rec(remaining - coins[ind], ind, coins, d); // take the coin, possibly will take it again
        }

        d[remaining][ind] = res;
        return res;
    }

    public int changeTopDown(int amount, int[] coins) {
        int[][] d = new int[amount + 1][coins.length + 1];

        Arrays.sort(coins);
        for (int i = 0; i <= amount; i++) {
            Arrays.fill(d[i], -1);
        }

        for (int i = 0; i < coins.length; i++) {
            d[0][i] = 1;
        }

        rec(amount, 0, coins, d);
        return d[amount][0];
    }

    // bottom up
    public int change(int amount, int[] coins) {
        int[][] d = new int[amount + 1][coins.length];

        Arrays.sort(coins);

        Arrays.fill(d[0], 1);

        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (j >= 1) {
                    d[i][j] = d[i][j - 1]; // count the scenarios where we DON'T take this coin (i.e last coin we took
                                           // is the previous one)
                }

                if (i >= coins[j]) {
                    d[i][j] += d[i - coins[j]][j]; // count the scenarios where we take this coin (given that we can
                                                   // take it)
                }
            }
        }

        return d[amount][coins.length - 1];
    }

    public static void main(String[] args) {
        var s = new Solution();
        var r = s.change(10, new int[] { 1, 2, 5 });
        System.out.println(r);
    }
}
