package problems.number_of_islands;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    private int[][] dirs = new int[][] {
            { 1, 0 },
            { 0, -1 },
            { -1, 0 },
            { 0, 1 }
    };

    private void dfs(char[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
            return;
        }

        if (grid[x][y] == '0') {
            return;
        }

        grid[x][y] = '0';

        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            dfs(grid, nx, ny);
        }
    }

    private void bfs(char[][] grid, int x, int y) {
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[] { x, y });

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];

            grid[cx][cy] = '0';

            for (int[] dir : dirs) {
                int nx = cx + dir[0];
                int ny = cy + dir[1];

                if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length && grid[nx][ny] == '1') {
                    grid[nx][ny] = '0';
                    q.offer(new int[] { nx, ny });
                }

            }
        }
    }

    public int numIslands(char[][] grid) {
        int res = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    bfs(grid, i, j); // or dfs(grid, i, j)
                    res++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        char[][] grid = new char[][] {
                { '1', '1', '0', '0', '0' },
                { '1', '1', '0', '0', '0' },
                { '0', '0', '1', '0', '0' },
                { '0', '0', '0', '1', '1' }
        };
        var s = new Solution();
        s.numIslands(grid);
    }
}
