package problems.max_area_of_island;

import utils.DisjointSetUnion;

public class SolutionWithDisjointSetUnion {
    private int[][] dirs = new int[][] {
            { 0, -1 },
            { -1, 0 },
    };

    public int maxAreaOfIsland(int[][] grid) {
        var dsu = new DisjointSetUnion(grid.length * grid[0].length);
        int rowLength = grid[0].length;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < rowLength; j++) {
                if (grid[i][j] == 1) {
                    int h1 = i * rowLength + j;
                    dsu.makeSet(i * rowLength + j);

                    for (int[] dir : dirs) {
                        int nx = i + dir[0];
                        int ny = j + dir[1];

                        if (nx >= 0 && nx < grid.length && ny >= 0 && ny < rowLength && grid[nx][ny] == 1) {
                            dsu.union(h1, nx * rowLength + ny);
                        }
                    }
                }
            }
        }

        return dsu.getMaxSize();
    }
}
