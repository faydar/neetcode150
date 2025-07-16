package utils;

// Works on UNDIRECTED graphs - since we check group memberships, not inbound edges!
public class DetectCyclesUnionFind {

    class UnionFind {
        int[] parents;
        int[] ranks;

        public UnionFind(int n) {
            this.parents = new int[n + 1];
            this.ranks = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                parents[i] = i;
                ranks[i] = 1;
            }
        }

        public void union(int a, int b) {
            int ga = find(a), gb = find(b);

            if (ga == gb) {
                return;
            }

            if (ranks[ga] > ranks[gb]) {
                parents[gb] = ga;
                ranks[ga] += ranks[gb];
            } else {
                parents[ga] = gb;
                ranks[gb] += ranks[ga];
            }
        }

        public int find(int a) {
            if (parents[a] != a) {
                parents[a] = find(parents[a]);
            }

            return parents[a];
        }
    }

    public boolean hasCycle(int n, int[][] edges) {
        var uf = new UnionFind(n);

        for (int[] edge : edges) {
            if (uf.find(edge[0]) == uf.find(edge[1])) {
                // two nodes already belonging in same group before adding this edge!
                return true;
            }

            uf.union(edge[0], edge[1]);
        }

        return false;
    }
}
