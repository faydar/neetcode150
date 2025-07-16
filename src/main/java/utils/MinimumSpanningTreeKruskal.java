package utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Set;

/*
 * Think of it as: “build the tree edge-by-edge from scratch”
 * Steps:
 * Sort all edges by weight.
 * Pick the smallest edge that doesn't form a cycle.
 * Repeat until the tree spans all vertices (has V−1 edges).
 * 
 * Greedy choice: Always add the smallest possible edge that won’t form a cycle.
 * 
 * Uses a Union-Find (Disjoint Set) data structure to efficiently check for cycles.
 * We could also use Kahn's algorithm. Union-find is more suitable for un-directed graphs.
 */
public class MinimumSpanningTreeKruskal {

    static class UnionFind {
        int[] parents;
        int[] sizes;

        public UnionFind(int n) {
            this.parents = new int[n + 1];
            this.sizes = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                parents[i] = i;
                sizes[i] = 1;
            }
        }

        public void union(int a, int b) {
            int ga = find(a), gb = find(b);

            if (ga == gb) {
                return;
            }

            if (sizes[ga] > sizes[gb]) {
                parents[gb] = ga;
                sizes[ga] += sizes[gb];
            } else {
                parents[ga] = gb;
                sizes[gb] += sizes[ga];
            }
        }

        public int find(int a) {
            if (parents[a] != a) {
                parents[a] = find(parents[a]);
            }

            return parents[a];
        }
    }

    static class Edge {
        int src, dst, cost;

        public Edge(int src, int dst, int cost) {
            this.src = src;
            this.dst = dst;
            this.cost = cost;
        }
    }

    public static Optional<List<Edge>> kruskal(int n, int[][] edges) {
        var uf = new UnionFind(n);
        List<Edge> e = new ArrayList<>();

        for (int[] edge : edges) {
            e.add(new Edge(edge[0], edge[1], edge[2]));
        }

        e.sort((e1, e2) -> Integer.compare(e1.cost, e2.cost));
        List<Edge> mst = new ArrayList<>();

        int edgesUsed = 0;
        int idx = 0;
        while (edgesUsed < n - 1 && idx < e.size()) {
            Edge cur = e.get(idx++);
            if (uf.find(cur.src) != uf.find(cur.dst)) {
                uf.union(cur.src, cur.dst);
                mst.add(cur);
                edgesUsed++;
            }
        }

        if (edgesUsed != n - 1) {
            return Optional.empty();
        }

        return Optional.of(mst);
    }
}
