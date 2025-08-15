package problems.swim_in_rising_water;

import java.util.PriorityQueue;

public class SolutionDijkstra {

    static record State(int x, int y, int time) {
    }

    static final int[][] DIRS = new int[][] {
            { -1, 0 },
            { 0, 1 },
            { 1, 0 },
            { 0, -1 }
    };

    // with heap/dijkstra O(N*N*logN)
    public int swimInWater(int[][] grid) {
        PriorityQueue<State> pq = new PriorityQueue<>((s1, s2) -> Integer.compare(s1.time, s2.time));
        boolean[][] visited = new boolean[grid.length][grid.length];

        pq.offer(new State(0, 0, grid[0][0]));
        visited[0][0] = true;
        while (!pq.isEmpty()) {
            var p = pq.poll();

            if (p.x == grid.length - 1 && p.y == grid[0].length - 1) {
                return p.time;
            }

            for (int[] dir : DIRS) {
                int nx = p.x + dir[0];
                int ny = p.y + dir[1];

                if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[nx].length && !visited[nx][ny]) {
                    pq.offer(new State(nx, ny, Math.max(p.time, grid[nx][ny])));
                    visited[nx][ny] = true;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[][] in = new int[][] {
                { 0, 1, 2, 3, 4 },
                { 24, 23, 22, 21, 5 },
                { 12, 13, 14, 15, 16 },
                { 11, 17, 18, 19, 20 },
                { 10, 9, 8, 7, 6 }
        };
        int[][] in2 = new int[][] {
                { 3, 2 },
                { 0, 1 }
        };
        var res = new SolutionDijkstra().swimInWater(in2);
        return;
    }
}
