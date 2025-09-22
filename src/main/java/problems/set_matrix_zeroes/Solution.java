package problems.set_matrix_zeroes;

public class Solution {
    // weird intuition:
    // to achieve O(1) space:
    // in first pass -> use first row and column to indicate if that row or col has
    // specially handle first row: otherwise we would think that 0th column/row may
    // need
    // to be zeroed unnecessarily

    /*
     * example where it could go wrong:
     * 1 0 2
     * 3 4 5
     * first pass ->
     * 0 0 2
     * 3 4 5
     * second pass, we think that 0 column should be zeroed ->
     * 0 0 0
     * 0 0 5
     * 
     * instead correct answer should be:
     * 0 0 0
     * 3 0 5
     */
    public void setZeroes(int[][] matrix) {
        boolean firstRowZero = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0) {
                        firstRowZero = true;
                    } else {
                        matrix[0][j] = 0;
                        matrix[i][0] = 0;
                    }
                }
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (matrix[0][0] == 0) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }

        if (firstRowZero) {
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[0][i] = 0;
            }
        }
    }

    public static void main(String[] args) {
        var in = new int[][] {
                { 1, 0, 3 }
        };

        new Solution().setZeroes(in);
        return;
    }
}
