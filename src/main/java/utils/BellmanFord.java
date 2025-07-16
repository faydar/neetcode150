package utils;

import java.util.HashMap;
import java.util.Map;

/**
 * E*V complexity, but works with negative weights
 * If the graph has non-negative edge weights only, Dijkstra’s is faster.
 * 
 * If you only need shortest path to one node, Bellman-Ford may be overkill
 * unless negative weights are involved.
 * 
 */
public class BellmanFord {

    public void shortestPath(int n, int[][] edges, int source) {
        Map<Integer, Map<Integer, Integer>> adj = new HashMap<>();

        for (int[] e : edges) {
            adj.computeIfAbsent(e[0], k -> new HashMap<>()).put(e[1], e[2]);
        }

        Map<Integer, Integer> dist = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            dist.put(i, i == source ? 0 : Integer.MAX_VALUE);
        }

        for (int i = 1; i <= n; i++) {
            for (int[] e : edges) {
                int src = e[0], dest = e[1], w = e[2];
                if (dist.get(src) != Integer.MAX_VALUE && dist.get(src) + w <= dist.get(dest)) {
                    dist.put(dest, dist.get(src) + w);
                }
            }
        }

        // check for "negative-weight cycles"
        for (int[] e : edges) {
            int src = e[0], dest = e[1], w = e[2];
            if (dist.get(src) != Integer.MAX_VALUE && dist.get(src) + w < dist.get(dest)) {
                // graph contains a negative weight cycle
                // you can start and finish at the same node, and this path has total of
                // negative cost
                // this means you can keep looping this path and get a lower cost :)
                return;
            }
        }
    }
}
