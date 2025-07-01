package problems.max_area_of_island;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    private int[][] dirs = new int[][] {
            { 1, 0 },
            { 0, -1 },
            { -1, 0 },
            { 0, 1 }
    };

    private int bfs(int[][] grid, int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        int area = 0;

        q.offer(new int[] { x, y });

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];
            area++;

            grid[cx][cy] = 0;

            for (int[] dir : dirs) {
                int nx = cx + dir[0];
                int ny = cy + dir[1];

                if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length && grid[nx][ny] == 1) {
                    grid[nx][ny] = 0;
                    q.offer(new int[] { nx, ny });
                }
            }
        }

        return area;
    }

    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    res = Math.max(res, bfs(grid, i, j));
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        var s = new Solution();
        var in = new int[][] {
                { 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
                { 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0 },
                { 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0 }
        };
        s.maxAreaOfIsland(in);
    }
}
