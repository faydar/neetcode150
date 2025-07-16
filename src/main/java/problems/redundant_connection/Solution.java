package problems.redundant_connection;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution {

    // UNION FIND SOLUTION STARTS
    static class DisjointSet {
        int[] parents;
        int[] sizes;

        public DisjointSet(int n) {
            this.parents = new int[n + 1];
            this.sizes = new int[n + 1];

            for (int i = 0; i <= n; i++) {
                this.parents[i] = i;
            }
            Arrays.fill(sizes, 1);
        }

        private int find(int a) {
            if (parents[a] == a) {
                return a;
            }

            parents[a] = find(parents[a]);
            return parents[a];
        }

        public void union(int a, int b) {
            int p1 = find(a), p2 = find(b);

            if (p1 != p2) {
                if (sizes[p1] >= sizes[p2]) {
                    parents[p2] = p1;
                    sizes[p1] += sizes[p2];
                } else {
                    parents[p1] = p2;
                    sizes[p2] += sizes[p1];
                }
            }
        }

        public int size(int a) {
            return sizes[find(a)];
        }
    }

    public int[] findRedundantConnection(int[][] edges) {
        var ds = new DisjointSet(edges.length);

        for (int[] edge : edges) {
            int sizeBeforeUnion = ds.size(edge[0]);
            ds.union(edge[0], edge[1]);

            if (sizeBeforeUnion == ds.size(edge[0])) {
                return edge;
            }
        }

        return new int[] { 0, 0 };
    }
    // UNION FIND SOLUTION ENDS

    // DFS SOLUTION STARTS
    public int[] findRedundantConnectionDfs(int[][] edges) {
        Map<Integer, List<Integer>> adj = new HashMap<>();

        for (int[] edge : edges) {
            adj.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            adj.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);

            var h = new HashSet<Integer>();
            if (hasCycle(adj, edge[1], -1, h)) {
                return edge;
            }
        }

        return new int[] { 0, 0 };
    }

    private boolean hasCycle(Map<Integer, List<Integer>> adj, Integer node, Integer parent, Set<Integer> h) {
        if (h.contains(node)) {
            return true;
        }

        h.add(node);

        for (Integer neighbor : adj.getOrDefault(node, List.of())) {
            if (!neighbor.equals(parent)) {
                if (hasCycle(adj, neighbor, node, h)) {
                    return true;
                }
            }
        }

        return false;
    }

    // DFS SOLUTION ENDS
}
