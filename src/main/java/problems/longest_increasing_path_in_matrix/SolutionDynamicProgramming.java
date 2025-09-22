package problems.longest_increasing_path_in_matrix;

import java.util.Arrays;

public class SolutionDynamicProgramming {

    private final int[][] DIRS = new int[][] {
            { -1, 0 },
            { 0, 1 },
            { 1, 0 },
            { 0, -1 }
    };

    int[][] d;

    public int rec(int x, int y, int[][] matrix) {
        if (d[x][y] != -1) {
            return d[x][y];
        }

        d[x][y] = 1;
        for (int[] dir : DIRS) {
            int nx = x + dir[0];
            int ny = y + dir[1];

            if (nx >= 0 && nx < matrix.length && ny >= 0 && ny < matrix[nx].length && matrix[x][y] < matrix[nx][ny]) {
                d[x][y] = Math.max(d[x][y], rec(nx, ny, matrix) + 1);
            }
        }

        return d[x][y];
    }

    public int longestIncreasingPath(int[][] matrix) {
        d = new int[matrix.length][matrix[0].length];
        int res = 0;

        for (int i = 0; i < matrix.length; i++) {
            Arrays.fill(d[i], -1);
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                res = Math.max(res, rec(i, j, matrix));
            }
        }

        return res;
    }

    public static void main(String[] args) {
        var in = new int[][] {
                { 9, 9, 4 },
                { 6, 6, 8 },
                { 2, 1, 1 }
        };
        var s = new SolutionDynamicProgramming();
        var r = s.longestIncreasingPath(in);
        return;
    }
}
