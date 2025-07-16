package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Set;

public class MinimumSpanningTreePrim {
    static class State {
        int from, to, cost;

        public State(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static class Edge {
        int src, dst;

        public Edge(int src, int dst) {
            this.src = src;
            this.dst = dst;
        }
    }

    // nodes numbered 1:N
    public static Optional<List<Edge>> prim(int n, int[][] edges) {
        Map<Integer, Map<Integer, Integer>> adj = new HashMap<>();

        for (int[] edge : edges) {
            adj.computeIfAbsent(edge[0], k -> new HashMap<>()).put(edge[1], edge[2]);
            adj.computeIfAbsent(edge[1], k -> new HashMap<>()).put(edge[0], edge[2]);
        }

        PriorityQueue<State> pq = new PriorityQueue<>((e1, e2) -> Integer.compare(e1.cost, e2.cost));
        Set<Integer> visited = new HashSet<>();

        pq.offer(new State(-1, 1, 0));

        List<Edge> result = new ArrayList<>();

        while (!pq.isEmpty()) {
            var state = pq.poll();

            if (visited.contains(state.to)) {
                // we could have already visited this node between the time we added a new entry
                // to the queue and processed it
                continue;
            }

            if (state.from != -1) {
                result.add(new Edge(state.from, state.to));
            }

            visited.add(state.to);

            for (Map.Entry<Integer, Integer> entry : adj.get(state.to).entrySet()) {
                if (!visited.contains(entry.getKey())) {
                    pq.offer(new State(state.to, entry.getKey(), entry.getValue()));
                }
            }
        }

        return visited.size() == n ? Optional.of(result) : Optional.empty();
    }
}
