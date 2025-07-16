package utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class DetectCyclesKahn {
    /**
     * Intuition: Kahn's topological sort, detect cycles
     * * A tree is connected and acyclic
     * 
     * Computing the in-degree of each node.
     * 
     * Initializing a queue with all nodes having in-degree 1 (normally 0, but edges
     * are bidirectional).
     * 
     * Iteratively:
     * 
     * Removing a node from the queue.
     * 
     * Decreasing the in-degree of its neighbors.
     * 
     * Adding neighbors with now-one in-degree to the queue.
     * 
     * If you process all nodes, the graph is acyclic.
     * 
     * If some nodes remain unprocessed, a cycle exists—because those nodes depend
     * on each other and can never reach in-degree 1.
     */
    private boolean hasNoCycle(int n, int[][] edges) {
        int[] inDegree = new int[n];
        Map<Integer, Set<Integer>> adj = new HashMap<>();

        for (int[] edge : edges) {
            adj.computeIfAbsent(edge[0], k -> new HashSet<>()).add(edge[1]);
            adj.computeIfAbsent(edge[1], k -> new HashSet<>()).add(edge[0]);

            inDegree[edge[1]]++;
            inDegree[edge[0]]++;
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 1) {
                q.add(i);
            }
        }

        int cnt = 0;

        while (!q.isEmpty()) {
            var p = q.poll();
            cnt++;

            for (Integer neighbor : adj.getOrDefault(p, Set.of())) {
                inDegree[neighbor]--;

                if (inDegree[neighbor] == 1) {
                    q.offer(neighbor);
                }
            }
        }

        return cnt == n;
    }
}
