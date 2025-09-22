package problems.non_overlapping_intervals;

import java.util.Arrays;

public class Solution {

    // Greedy
    // intuition:
    // sort intervals,
    // if one interval is clashing with the next one of them will have to be removed
    // keep the interval with smaller range end
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));
        int rangeEnd = intervals[0][1];
        int count = 0;

        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i + 1][0] < rangeEnd) {
                count++;
                rangeEnd = Math.min(intervals[i + 1][1], rangeEnd);
            } else {
                rangeEnd = intervals[i + 1][1];
            }
        }

        return count;
    }

    public static void main(String[] args) {
        var s = new Solution();
        var in = new int[][] {
                { 1, 2 },
                { 2, 3 },
                { 3, 4 },
                { 1, 3 }
        };

        // [-52,31],[-73,-26],[82,97],[-65,-11],[-62,-49],[95,99],[58,95],[-31,49],[66,98],[-63,2],[30,47],[-40,-26]
        var in2 = new int[][] {
                { -52, 31 },
                { -73, -26 },
                { 82, 97 },
                { -65, -11 },
                { -62, -49 },
                { 95, 99 },
                { 58, 95 },
                { -31, 49 },
                { 66, 98 },
                { -63, 2 },
                { 30, 47 },
                { -40, -26 }
        };

        var r = s.eraseOverlapIntervals(in2);
        return;
    }
}
