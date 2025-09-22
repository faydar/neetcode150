package problems.longest_increasing_path_in_matrix;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SolutionTopologicalSort {

    private final int[][] DIRS = new int[][] {
            { -1, 0 },
            { 0, 1 },
            { 1, 0 },
            { 0, -1 }
    };

    private boolean inBoundaries(int x, int y, int n, int m) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    static record State(int x, int y, int len) {

    }

    // Nice solution with topological sort
    // start from cells with no smaller element around, hence starting nodes for
    // topological sort
    public int longestIncreasingPath(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        int[][] ins = new int[n][m];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                for (int[] dir : DIRS) {
                    int nx = i + dir[0];
                    int ny = j + dir[1];

                    if (inBoundaries(nx, ny, n, m) && matrix[nx][ny] > matrix[i][j]) {
                        ins[nx][ny]++;
                    }
                }
            }
        }

        Queue<State> q = new LinkedList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (ins[i][j] == 0) {
                    q.add(new State(i, j, 1));
                }
            }
        }

        int res = 0;
        while (!q.isEmpty()) {
            var p = q.poll();
            res = Math.max(res, p.len);

            for (int[] dir : DIRS) {
                int nx = p.x + dir[0];
                int ny = p.y + dir[1];

                if (inBoundaries(nx, ny, n, m) && matrix[nx][ny] > matrix[p.x][p.y]) {
                    ins[nx][ny]--;

                    if (ins[nx][ny] == 0) {
                        q.add(new State(nx, ny, p.len + 1));
                    }
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        var in = new int[][] {
                { 1, 2, 3 },
                { 2, 1, 4 },
                { 3, 0, 5 },
                { 7, 4, 5 }
        };
        var s = new SolutionTopologicalSort();
        var r = s.longestIncreasingPath(in);
        return;
    }
}
