package problems.unique_paths;

public class Solution {

    /*
     * This is calculated by combination as well, because all we care about is: in
     * that total number of moves, which steps are the "downs" (or equivalently,
     * which are the "rights")?
     */
    private int uniquePathsCombination(int m, int n) {
        int max = Math.max(m - 1, n - 1), min = Math.min(m - 1, n - 1);
        long result = 1;

        for (int i = 1; i <= min; i++) {
            result *= max + i;
            result /= i;
        }

        return (int) result;
    }

    private int uniquePathsDp(int m, int n) {
        int[][] d = new int[m][n];
        for (int i = 0; i < m; i++) {
            d[i][0] = 1;
        }

        for (int i = 0; i < n; i++) {
            d[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                d[i][j] += d[i - 1][j] + d[i][j - 1];
            }
        }

        return d[m - 1][n - 1];
    }

    public int uniquePaths(int m, int n) {
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().uniquePathsCombination(51, 9));
    }
}
