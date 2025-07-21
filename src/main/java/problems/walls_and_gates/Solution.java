package problems.walls_and_gates;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static class State {
        int x, y, step;

        public State(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }

    public void islandsAndTreasure(int[][] grid) {
        Queue<State> ll = new LinkedList<>();
        int[][] dirs = new int[][] {
                { -1, 0 },
                { 0, 1 },
                { 1, 0 },
                { 0, -1 }
        };

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    ll.offer(new State(i, j, 0));
                }
            }
        }

        while (!ll.isEmpty()) {
            var cur = ll.poll();

            for (int[] dir : dirs) {
                int nx = cur.x + dir[0];
                int ny = cur.y + dir[1];

                // we don't need to check if cur.step + 1 < grid[nx][ny], because with BFS the
                // first reacher will have smaller step count
                if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[nx].length
                        && grid[nx][ny] != Integer.MAX_VALUE) {
                    grid[nx][ny] = cur.step + 1;
                    ll.offer(new State(nx, ny, cur.step + 1));
                }
            }
        }
    }

    public static void main(String[] args) {
        var m = Integer.MAX_VALUE;
        var in = new int[][] {
                { m, -1, 0, m },
                { m, m, m, -1 },
                { m, -1, m, -1 },
                { 0, -1, m, m }
        };
        new Solution().islandsAndTreasure(in);
        return;
    }
}
