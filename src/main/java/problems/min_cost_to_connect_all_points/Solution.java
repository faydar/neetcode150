package problems.min_cost_to_connect_all_points;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Solution {

    static class Edge {
        int src, dest, cost;

        public Edge(int src, int dest, int cost) {
            this.src = src;
            this.dest = dest;
            this.cost = cost;
        }
    }

    static class UnionFind {
        int[] parents;
        int[] sizes;

        public UnionFind(int n) {
            this.parents = new int[n];
            this.sizes = new int[n];

            for (int i = 0; i < n; i++) {
                this.parents[i] = i;
                this.sizes[i] = 1;
            }
        }

        public int find(int a) {
            if (parents[a] != a) {
                parents[a] = find(parents[a]);
            }

            return parents[a];
        }

        public void union(int a, int b) {
            int ga = find(a), gb = find(b);

            if (ga == gb) {
                return;
            }

            if (sizes[ga] < sizes[gb]) {
                parents[ga] = gb;
                sizes[gb] += sizes[ga];
            } else {
                parents[gb] = ga;
                sizes[ga] += sizes[gb];
            }
        }
    }

    // intuition
    /*
     * Use Kruskal. Sort all edges, take the lowest cost edge at each step, check if
     * it causes cycle using union-find, if not add to the result.
     */
    public int minCostConnectPoints(int[][] points) {
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                edges.add(
                        new Edge(i, j, Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1])));
            }
        }

        edges.sort((e1, e2) -> Integer.compare(e1.cost, e2.cost));

        int n = points.length;
        var uf = new UnionFind(n);
        int index = 0, edgeCount = 0, span = 0;

        while (index < edges.size() && edgeCount < n - 1) {
            Edge e = edges.get(index++);
            if (uf.find(e.src) != uf.find(e.dest)) {
                uf.union(e.src, e.dest);
                edgeCount++;
                span += e.cost;
            }
        }

        return span;
    }

    static class State {
        int from, to, cost;

        public State(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    public int minCostConnectPointsPrim(int[][] points) {
        Map<Integer, Map<Integer, Integer>> adj = new HashMap<>();

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                adj.computeIfAbsent(i, k -> new HashMap<>()).put(j,
                        Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]));
                adj.computeIfAbsent(j, k -> new HashMap<>()).put(i,
                        Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]));
            }
        }

        Set<Integer> visited = new HashSet<>();
        PriorityQueue<State> pq = new PriorityQueue<>((s1, s2) -> Integer.compare(s1.cost, s2.cost));

        pq.offer(new State(-1, 0, 0));
        int result = 0;

        while (!pq.isEmpty()) {
            var current = pq.poll();

            if (visited.contains(current.to)) {
                continue;
            }

            result += current.cost;
            visited.add(current.to);

            for (Map.Entry<Integer, Integer> es : adj.getOrDefault(current.to, new HashMap<>()).entrySet()) {
                if (!visited.contains(es.getKey())) {
                    pq.offer(new State(current.to, es.getKey(), es.getValue()));
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        var in = new int[][] {
                { 0, 0 },
                { 1, 1 },
                { 1, 0 },
                { -1, 1 }
        };

        System.out.println(new Solution().minCostConnectPointsPrim(in));
    }
}
