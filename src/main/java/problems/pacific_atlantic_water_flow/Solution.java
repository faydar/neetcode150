package problems.pacific_atlantic_water_flow;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    private final int[][] dirs = new int[][] {
            { -1, 0 },
            { 0, 1 },
            { 1, 0 },
            { 0, -1 }
    };

    private boolean inRange(int[][] heights, int x, int y) {
        return x >= 0 && y >= 0 && x < heights.length && y < heights[x].length;
    }

    private void dfs(int[][] heights, int x, int y, int fill, int[][] h) {
        h[x][y] = h[x][y] | fill;

        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];

            if (inRange(heights, nx, ny) && (h[nx][ny] & fill) == 0 && heights[nx][ny] >= heights[x][y]) {
                dfs(heights, x + dir[0], y + dir[1], fill, h);
            }
        }
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {

        int[][] h = new int[heights.length][heights[0].length];

        for (int i = 0; i < h.length; i++) {
            dfs(heights, i, 0, 1, h);
            dfs(heights, i, h[i].length - 1, 2, h);
        }

        for (int i = 0; i < h[0].length; i++) {
            dfs(heights, 0, i, 1, h);
            dfs(heights, h.length - 1, i, 2, h);
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[i].length; j++) {
                if (h[i][j] == 3) {
                    result.add(List.of(i, j));
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        var in = new int[][] {
                { 3, 3, 3, 3, 3, 3 },
                { 3, 0, 3, 3, 0, 3 },
                { 3, 3, 3, 3, 3, 3 } };
        new Solution().pacificAtlantic(in);
    }
}
