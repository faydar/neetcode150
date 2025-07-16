package problems.graph_valid_tree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import utils.LeetCodeUtil;

public class Solution {

    static class DisjointSetUnion {
        int[] parents;
        int[] sizes;

        int maxSize = 1;

        public DisjointSetUnion(int n) {
            this.parents = new int[n];
            this.sizes = new int[n];

            for (int i = 0; i < n; i++) {
                this.parents[i] = i;
            }

            Arrays.fill(this.sizes, 1);
        }

        public int find(int a) {
            if (parents[a] != a) {
                parents[a] = find(parents[a]);
            }

            return parents[a];
        }

        public void union(int a, int b) {
            int ga = find(a);
            int gb = find(b);

            if (ga == gb) {
                return;
            }

            if (sizes[ga] >= sizes[gb]) {
                parents[gb] = ga;
                sizes[ga] += sizes[gb];
                maxSize = Math.max(sizes[ga], maxSize);
            } else {
                parents[ga] = gb;
                sizes[gb] += sizes[ga];
                maxSize = Math.max(sizes[gb], maxSize);
            }
        }
    }

    public boolean validTree(int n, int[][] edges) {
        var dsu = new DisjointSetUnion(n);

        for (int[] edge : edges) {
            int sizeBefore = dsu.maxSize;
            dsu.union(edge[0], edge[1]);
            if (dsu.maxSize == sizeBefore) {
                return false;
            }
        }

        return dsu.maxSize == n;
    }

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // BFS and track parent, because nodes are bidirectional. We shouldn't return
    // false because we saw the parent node as visited
    public boolean validTreeUsingBfs(int n, int[][] edges) {
        Map<Integer, Set<Integer>> adj = new HashMap<>();

        for (int[] edge : edges) {
            adj.computeIfAbsent(edge[0], k -> new HashSet<>()).add(edge[1]);
            adj.computeIfAbsent(edge[1], k -> new HashSet<>()).add(edge[0]);
        }

        Queue<Pair> q = new LinkedList<>();
        Set<Integer> h = new HashSet<>();

        q.offer(new Pair(0, -1));
        h.add(0);

        while (!q.isEmpty()) {
            var polled = q.poll();
            var node = polled.x;
            var parent = polled.y;

            for (Integer neighbor : adj.getOrDefault(node, Set.of())) {
                if (neighbor != parent) {
                    if (h.contains(neighbor)) {
                        // cyclic graph, cannot be tree
                        return false;
                    }

                    h.add(neighbor);
                    q.offer(new Pair(neighbor, node));
                }
            }
        }

        // disconnected graph, cannot be tree
        return h.size() == n;
    }

    public static void main(String[] args) {
        // valid
        var in1 = new int[][] {
                { 0, 1 },
                { 0, 2 },
                { 0, 3 },
                { 1, 4 }
        };

        // invalid
        var in2 = new int[][] {
                { 0, 1 },
                { 1, 2 },
                { 2, 3 },
                { 1, 3 },
                { 1, 4 }
        };

        var res = new Solution().validTreeUsingBfs(5, in2);
        return;
    }
}
