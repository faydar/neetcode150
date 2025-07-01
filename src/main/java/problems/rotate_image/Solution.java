package problems.rotate_image;

public class Solution {

    /**
     * Solution 1: go in layers, do 4 way rotate
     */

    public void rotate(int[][] matrix) {
        for (int l = 0, r = matrix.length - 1; l < r; l++, r--) {
            for (int i = 0; i < r - l; i++) {
                int c = matrix[l][l + i];

                matrix[l][l + i] = matrix[r - i][l];
                matrix[r - i][l] = matrix[r][r - i];
                matrix[r][r - i] = matrix[l + i][r];
                matrix[l + i][r] = c;
            }
        }
    }

    /**
     * Solution 2: Do horizontal reverse and transpose OR do transpose and vertical
     * reverse (mirror)
     */

    private void transpose(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[0].length; j++) {
                var tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }

    private void reverseHorizontal(int[][] matrix) {
        for (int i = 0; i < matrix.length / 2; i++) {
            var tmp = matrix[i];
            matrix[i] = matrix[matrix.length - 1 - i];
            matrix[matrix.length - 1 - i] = tmp;
        }
    }

    public static void main(String[] args) {
        var in = new int[][] {
                { 5, 1, 9, 11 },
                { 2, 4, 8, 10 },
                { 13, 3, 6, 7 },
                { 15, 14, 12, 16 }
        };

        var in2 = new int[][] {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };

        var s = new Solution();

        s.reverseHorizontal(in2);
        s.transpose(in2);
        return;
    }
}
