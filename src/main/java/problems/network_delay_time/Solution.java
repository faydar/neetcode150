package problems.network_delay_time;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Solution {

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int dijkstra(int[][] times, int n, int k) {
        Map<Integer, Map<Integer, Integer>> adj = new HashMap<>();
        for (int[] time : times) {
            adj.computeIfAbsent(time[0], key -> new HashMap<>()).put(time[1], time[2]);
        }

        Map<Integer, Integer> dist = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            dist.put(i, i == k ? 0 : Integer.MAX_VALUE);
        }

        PriorityQueue<Pair> q = new PriorityQueue<>((p1, p2) -> p1.y - p2.y);
        q.offer(new Pair(k, 0));

        Set<Integer> visited = new HashSet<>();

        while (!q.isEmpty()) {
            var cur = q.poll();
            var curNode = cur.x;
            var curDist = cur.y;

            visited.add(curNode);

            for (Map.Entry<Integer, Integer> keyVal : adj.getOrDefault(curNode, new HashMap<>()).entrySet()) {
                var neighbor = keyVal.getKey();
                var totalDist = curDist + keyVal.getValue();

                if (totalDist < dist.get(neighbor)) {
                    dist.put(neighbor, totalDist);
                    q.offer(new Pair(neighbor, totalDist));
                }
            }
        }

        if (visited.size() < n) {
            return -1;
        }

        return dist.values().stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElse(0);
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        return dijkstra(times, n, k);
    }

    // BELLMAN FORD
    public int shortestPathBellmanFord(int[][] times, int n, int k) {
        Map<Integer, Map<Integer, Integer>> adj = new HashMap<>();

        for (int[] e : times) {
            adj.computeIfAbsent(e[0], key -> new HashMap<>()).put(e[1], e[2]);
        }

        Map<Integer, Integer> dist = new HashMap<>();
        for (int i = 1; i <= 3; i++) {
            dist.put(i, i == k ? 0 : Integer.MAX_VALUE);
        }

        for (int i = 1; i <= n; i++) {
            for (int[] e : times) {
                int src = e[0], dest = e[1], w = e[2];
                if (dist.get(src) != Integer.MAX_VALUE && dist.get(src) + w <= dist.get(dest)) {
                    dist.put(dest, dist.get(src) + w);
                }
            }
        }

        int max = dist.values().stream().mapToInt(Integer::intValue).max().orElse(0);
        return max == Integer.MAX_VALUE ? -1 : max;
    }

    public int networkDelayTimeBellmanFord(int[][] times, int n, int k) {
        return shortestPathBellmanFord(times, n, k);
    }

    public static void main(String[] args) {
        var in = new int[][] {
                { 2, 1, 1 },
                { 2, 3, 1 },
                { 3, 4, 1 }
        };
        System.out.println(new Solution().networkDelayTimeBellmanFord(in, 3, 1));
    }
}
