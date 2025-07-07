package problems.coin_change;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    public int coinChangeBfs(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        Queue<Integer> q = new LinkedList<>();

        q.add(0);
        int steps = 0;
        boolean[] reached = new boolean[amount + 1];
        reached[0] = true;

        while (!q.isEmpty()) {
            steps++;

            int qs = q.size();

            for (int i = 0; i < qs; i++) {
                var polled = q.poll();

                for (int c : coins) {
                    if (c <= amount) {
                        int next = polled + c;

                        if (next == amount) {
                            return steps;
                        }

                        if (next < amount && !reached[next]) {
                            reached[next] = true;
                            q.offer(polled + c);
                        }
                    }
                }
            }
        }

        return -1;
    }

    public int coinChange(int[] coins, int amount) {
        int[] d = new int[amount + 1];
        Arrays.fill(d, -1);

        d[0] = 0;

        for (int i = 0; i <= amount; i++) {
            if (d[i] != -1) {
                for (int c : coins) {
                    if (c <= amount && i + c <= amount) {
                        if (d[i + c] == -1) {
                            d[i + c] = d[i] + 1;
                        } else {
                            d[i + c] = Math.min(d[i + c], d[i] + 1);
                        }
                    }
                }
            }
        }

        return d[amount] != -1 ? d[amount] : -1;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().coinChangeBfs(new int[] { 186, 419, 83, 408 }, 6249));
    }
}
