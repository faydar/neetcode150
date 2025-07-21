package problems.merge_intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Solution {

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<Pair> l = new ArrayList<>();
        int i = 0, j = 0;

        while (i < intervals.length) {
            j = i;

            int ix = intervals[j][0], iy = intervals[j][1];

            while (j + 1 < intervals.length && iy >= intervals[j + 1][0]) {
                ix = Math.min(ix, intervals[j + 1][0]);
                iy = Math.max(iy, intervals[j + 1][1]);
                j++;
            }

            l.add(new Pair(ix, iy));
            i = j + 1;
        }

        int[][] res = new int[l.size()][2];
        int index = 0;
        for (Pair p : l) {
            res[index++] = new int[] { p.x, p.y };
        }

        return res;
    }

    // intuition: Sweep line algorithm
    // treat everything as event
    // interval starts are marked with +1, interval ends are marked with -1
    // whenever sum of events are at 0, new interval starts
    public int[][] mergeWithSweepLine(int[][] intervals) {
        // treemap in order to keep time points (keys) in order
        Map<Integer, Integer> events = new TreeMap<>();

        for (int[] interval : intervals) {
            events.put(interval[0], events.getOrDefault(interval[0], 0) + 1);
            events.put(interval[1], events.getOrDefault(interval[1], 0) - 1);
        }

        int s = 0, nx = 0;
        List<Pair> l = new ArrayList<>();

        for (Integer time : events.keySet()) {
            if (s == 0) {
                nx = time;
            }

            s += events.get(time);

            if (s == 0) {
                l.add(new Pair(nx, time));
            }
        }

        int[][] res = new int[l.size()][2];
        int index = 0;
        for (Pair p : l) {
            res[index++] = new int[] { p.x, p.y };
        }

        return res;
    }

    public static void main(String[] args) {
        var in = new int[][] {
                { 2, 3 },
                { 4, 5 },
                { 6, 7 },
                { 8, 9 },
                { 1, 10 }
        };

        var res = new Solution().mergeWithSweepLine(in);
        return;
    }
}
