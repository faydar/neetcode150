package problems.rotting_oranges;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    private int[][] dirs = new int[][] {
            { 1, 0 },
            { 0, -1 },
            { -1, 0 },
            { 0, 1 }
    };

    public int orangesRotting(int[][] grid) {
        Queue<int[]> q = new LinkedList<>();
        int totalOranges = 0;
        int currentRotten = 0;
        int minute = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != 0) {
                    totalOranges++;
                }
                if (grid[i][j] == 2) {
                    q.add(new int[] { i, j });
                    grid[i][j] = 0;
                }
            }
        }

        while (!q.isEmpty()) {
            int qs = q.size();
            minute++;

            for (int i = 0; i < qs; i++) {
                var t = q.poll();

                currentRotten++;

                for (int[] dir : dirs) {
                    int nx = t[0] + dir[0];
                    int ny = t[1] + dir[1];

                    if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length && grid[nx][ny] == 1) {
                        q.add(new int[] { nx, ny });
                        grid[nx][ny] = 2;
                    }
                }
            }
        }

        if (currentRotten == totalOranges) {
            if (totalOranges == 0) {
                return 0;
            }

            return minute - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        var s = new Solution();
        var in = new int[][] {
                { 2, 1, 1 },
                { 1, 1, 0 },
                { 0, 1, 1 }
        };
        var in2 = new int[][] {
                { 2, 1, 1 },
                { 0, 1, 1 },
                { 1, 0, 1 }
        };
        var in3 = new int[][] {
                { 0 }
        };
        var res = s.orangesRotting(in3);
        return;
    }
}
