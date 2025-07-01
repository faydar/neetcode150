package utils;

import java.util.Arrays;

public class DisjointSetUnion {

    private int[] parents;
    private int[] sizes;

    public DisjointSetUnion(int size) {
        this.parents = new int[size];
        this.sizes = new int[size];
    }

    public void makeSet(int val) {
        this.parents[val] = val;
        this.sizes[val] = 1;
    }

    public int find(int val) {
        if (parents[val] != val) {
            parents[val] = find(parents[val]);
        }

        return parents[val];
    }

    public int getMaxSize() {
        return Arrays.stream(sizes).max().orElse(0);
    }

    public void union(int v1, int v2) {
        int g1 = find(v1);
        int g2 = find(v2);

        if (g1 == g2) {
            return;
        }

        if (sizes[g1] < sizes[g2]) {
            parents[g1] = g2;
            sizes[g2] += sizes[g1];
        } else {
            parents[g2] = g1;
            sizes[g1] += sizes[g2];
        }
    }
}