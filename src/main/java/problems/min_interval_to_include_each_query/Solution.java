package problems.min_interval_to_include_each_query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {

    static record Pair(int x, int y) {
    }

    // nlogn + mlogm
    // intuition: use a min heap based on interval size, and always keep a valid
    // interval at the top
    // we first add all eligible intervals to queue based on starts
    // then we remove them based on end points (outside current query range)
    public int[] minInterval(int[][] intervals, int[] queries) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<Pair> queriesList = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            queriesList.add(new Pair(queries[i], i));
        }

        Collections.sort(queriesList, (q1, q2) -> Integer.compare(q1.x, q2.x));
        PriorityQueue<Pair> pq = new PriorityQueue<>((p1, p2) -> Integer.compare(p1.y, p2.y));

        int it = 0;
        int[] res = new int[queries.length];

        for (int q = 0; q < queriesList.size(); q++) {
            var cq = queriesList.get(q);

            while (it < intervals.length && cq.x >= intervals[it][0]) {
                pq.add(new Pair(intervals[it][1], intervals[it][1] - intervals[it][0] + 1));
                it++;
            }

            while (!pq.isEmpty() && cq.x > pq.peek().x) {
                pq.poll();
            }

            res[cq.y] = pq.isEmpty() ? -1 : pq.peek().y;
        }

        return res;
    }

    public static void main(String[] args) {
        var interval = new int[][] {
                { 2, 3 },
                { 2, 5 },
                { 1, 8 },
                { 20, 25 }
        };
        var q = new int[] { 2, 19, 5, 22 };
        var r = new Solution().minInterval(interval, q);
        return;
    }
}
