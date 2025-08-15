package problems.jump_game_ii;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    // dp solution o(n^2)
    public int jumpDp(int[] nums) {
        int[] d = new int[nums.length];
        Arrays.fill(d, Integer.MAX_VALUE);

        d[0] = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < i + 1 + nums[i]; j++) {
                if (j < nums.length) {
                    d[j] = Math.min(d[j], d[i] + 1);

                }
            }
        }

        return d[nums.length - 1];
    }

    // greedy & bfs o(n)
    // we loop through index ranges, never repeat same range, so o(N)
    public int jump(int[] nums) {
        int steps = 0, rangeStart = 0, rangeEnd = 0;

        while (rangeEnd < nums.length - 1) {
            int newRangeEnd = rangeEnd;
            for (int i = rangeStart; i <= rangeEnd; i++) {
                newRangeEnd = Math.max(newRangeEnd, i + nums[i]);
            }

            steps++;
            rangeStart = rangeEnd + 1;
            rangeEnd = newRangeEnd;
        }

        return steps;
    }

    public static void main(String[] args) {
        var res = new Solution().jump(new int[] { 2, 3, 1, 4, 0, 1, 1, 2, 3 });
        return;
    }
}
