package problems.insert_interval;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int it = 0;
        List<Pair> res = new ArrayList<>();
        while (it < intervals.length && intervals[it][1] < newInterval[0]) {
            res.add(new Pair(intervals[it][0], intervals[it][1]));
            it++;
        }

        int mergedIntervalStart = newInterval[0], mergedIntervalEnd = newInterval[1];

        while (it < intervals.length && mergedIntervalEnd >= intervals[it][0]) {
            mergedIntervalStart = Math.min(mergedIntervalStart, intervals[it][0]);
            mergedIntervalEnd = Math.max(mergedIntervalEnd, intervals[it][1]);
            it++;
        }

        res.add(new Pair(mergedIntervalStart, mergedIntervalEnd));

        while (it < intervals.length) {
            res.add(new Pair(intervals[it][0], intervals[it][1]));
            it++;
        }

        int[][] resArr = new int[res.size()][2];
        int ind = 0;
        for (Pair p : res) {
            resArr[ind++] = new int[] { p.x, p.y };
        }

        return resArr;
    }

    public static void main(String[] args) {
        var in = new int[][] {
                {}
        };

        var in2 = new int[][] {
                { 1, 2 },
                { 3, 5 },
                { 8, 10 },
                { 12, 16 }
        };
        var in3 = new int[][] {
                { 3, 5 },
                { 6, 9 }
        };
        var res2 = new Solution().insert(in, new int[] { 5, 7 });

        return;
    }
}
