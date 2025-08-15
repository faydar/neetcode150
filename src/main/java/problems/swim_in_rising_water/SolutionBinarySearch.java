package problems.swim_in_rising_water;

public class SolutionBinarySearch {

    static final int[][] DIRS = new int[][] {
            { -1, 0 },
            { 0, 1 },
            { 1, 0 },
            { 0, -1 }
    };

    // Binary Search + DFS
    // intuition: try the heights h where we have a path from 0,0 to N,N where all
    // path[i] <= h
    // binary search and test above with DFS
    // O(N*N*logN)
    public int swimInWater(int[][] grid) {
        int l = Integer.MAX_VALUE, r = Integer.MIN_VALUE;
        for (int[] row : grid) {
            for (int el : row) {
                l = Math.min(l, el);
                r = Math.max(r, el);
            }
        }

        l = Math.max(l, grid[0][0]);

        int result = -1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            boolean[][] visited = new boolean[grid.length][grid[0].length];

            if (canReach(0, 0, m, grid, visited)) {
                result = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }

        return result;
    }

    private boolean canReach(int x, int y, int maxHeight, int[][] grid, boolean[][] visited) {
        if (x == grid.length - 1 && y == grid[x].length - 1) {
            return true;
        }

        visited[x][y] = true;
        boolean res = false;

        for (int[] dir : DIRS) {
            int nx = x + dir[0];
            int ny = y + dir[1];

            if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[nx].length && grid[nx][ny] <= maxHeight
                    && !visited[nx][ny]) {
                res = res | canReach(nx, ny, maxHeight, grid, visited);
            }
        }

        return res;
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
        var res = new SolutionBinarySearch().swimInWater(in);
        return;
    }
}
