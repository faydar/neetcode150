package problems.swim_in_rising_water;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolutionMinimumSpanningTree {
    static class DisjointSetUnion {
        int[] parents;
        int[] ranks;

        public DisjointSetUnion(int n) {
            this.parents = new int[n];
            this.ranks = new int[n];
            Arrays.fill(this.ranks, 1);
            for (int i=0; i<n; i++) {
                parents[i] = i;
            }
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

            if (ranks[ga] > ranks[gb]) {
                parents[gb] = ga;
                ranks[ga] += ranks[gb];
            } else {
                parents[ga] = gb;
                ranks[gb] += ranks[ga];
            }
        }
    }

    static record Cell(int x, int y, int val) {
    }

    static final int[][] DIRS = new int[][] {
            { -1, 0 },
            { 0, 1 },
            { 1, 0 },
            { 0, -1 }
    };

    public int swimInWater(int[][] grid) {
        List<Cell> cells = new ArrayList<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                cells.add(new Cell(i, j, grid[i][j]));
            }
        }

        cells.sort((c1, c2) -> Integer.compare(c1.val, c2.val));
        int rowLength = grid[0].length;
        var dsu = new DisjointSetUnion(cells.size());

        for (Cell cell : cells) {
            int cellId = cell.x * rowLength + cell.y;
            for (int[] dir : DIRS) {
                int nx = cell.x + dir[0];
                int ny = cell.y + dir[1];

                if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[nx].length && grid[nx][ny] <= cell.val) {
                    dsu.union(cellId, nx * rowLength + ny);
                }
            }

            if (dsu.find(0) == dsu.find(cells.size()-1)) {
                return cell.val;
            }
        }

        return -1;
    }
}
