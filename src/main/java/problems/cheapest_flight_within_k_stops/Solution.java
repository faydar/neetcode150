package problems.cheapest_flight_within_k_stops;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {

    static class State {
        int node, cost, steps;

        public State(int node, int cost, int steps) {
            this.node = node;
            this.cost = cost;
            this.steps = steps;
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, Map<Integer, Integer>> dist = new HashMap<>();
        Map<Integer, Map<Integer, Integer>> adj = new HashMap<>();

        for (int[] flight : flights) {
            adj.computeIfAbsent(flight[0], key -> new HashMap<>()).put(flight[1], flight[2]);
        }

        for (int i = 0; i < n; i++) {
            dist.put(i, new HashMap<>());

            for (int j = 0; j <= k + 2; j++) {
                dist.get(i).put(j, Integer.MAX_VALUE);
            }
        }

        dist.get(src).put(0, 0);

        PriorityQueue<State> q = new PriorityQueue<>((s1, s2) -> {
            return s1.cost - s2.cost;
        });

        q.offer(new State(src, 0, 0));

        while (!q.isEmpty()) {
            var state = q.poll();

            for (Map.Entry<Integer, Integer> neighbor : adj.getOrDefault(state.node, Map.of()).entrySet()) {
                var newCost = state.cost + neighbor.getValue();
                var newStep = state.steps + 1;

                if (newCost < dist.get(neighbor.getKey()).get(newStep) && newStep <= k + 1) {
                    dist.get(neighbor.getKey()).put(newStep, newCost);
                    q.offer(new State(neighbor.getKey(), newCost, state.steps + 1));
                }
            }
        }

        int res = Integer.MAX_VALUE;
        for (int val : dist.get(dst).values()) {
            res = Math.min(res, val);
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public int findCheapestPriceBellmanFord(int n, int[][] flights, int src, int dst, int k) {
        int[] dist = new int[n];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for (int i = 1; i <= k + 1; i++) {
            /*
             * we have to use a tmp array here.
             * As soon as you update dist[d] for one edge, subsequent edges in the same
             * pass could immediately read that fresher value. That means an edge processed
             * later in the same pass might effectively chain two relaxations, producing a
             * path that already uses i + 1 edges.
             * 
             * Think of each outer loop as a “round” in which all edges fire simultaneously.
             * The temporary array is the whiteboard where you write the next distances; you
             * only erase the old distances and copy the new ones when the round is
             * completely over.
             * 
             * Using a temp array guarantees that every relaxation round respects the
             * intended edge limit (k in this snippet or V – 1 in classic Bellman‑Ford) and
             * makes the algorithm independent of edge‑processing order.
             */
            int[] updatedDist = Arrays.copyOf(dist, n);

            for (int[] flight : flights) {
                if (dist[flight[0]] != Integer.MAX_VALUE && dist[flight[0]] + flight[2] <= updatedDist[flight[1]]) {
                    updatedDist[flight[1]] = dist[flight[0]] + flight[2];
                }
            }

            dist = updatedDist;
        }

        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }

    public static void main(String[] args) {
        int[][] flights = new int[][] {
                { 0, 1, 5 },
                { 1, 2, 5 },
                { 0, 3, 2 },
                { 3, 1, 2 },
                { 1, 4, 1 },
                { 4, 2, 1 }
        };
        var res = new Solution().findCheapestPriceBellmanFord(5, flights, 0, 2, 2);
        System.out.println(res);
    }
}
