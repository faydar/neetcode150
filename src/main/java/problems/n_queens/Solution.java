package problems.n_queens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    private List<List<String>> result = new ArrayList<>();
    private char[][] board;

    private void rec(int n, int row, int colMask, int leftDiagonalMask, int rightDiagonalMask) {
        if (row == n) {
            List<String> current = new ArrayList<>();
            for (int i = 0; i < board.length; i++) {
                current.add(new String(board[i]));
            }

            result.add(current);
            return;
        }

        for (int col = 0; col < n; col++) {
            int leftDiagonal = row - col;
            int rightDiagonal = row + col;

            var emptyCol = (colMask & (1 << col)) == 0;
            var emptyLeftDiagonal = (leftDiagonalMask & (1 << leftDiagonal)) == 0;
            var emptyRightDiagonal = (rightDiagonalMask & (1 << rightDiagonal)) == 0;

            if (emptyCol && emptyLeftDiagonal && emptyRightDiagonal) {
                board[row][col] = 'Q';
                rec(n, row + 1, colMask | (1 << col), (leftDiagonalMask | (1 << leftDiagonal)),
                        (rightDiagonalMask | (1 << rightDiagonal)));
                board[row][col] = '.';
            }
        }
    }

    public List<List<String>> solveNQueens(int n) {
        this.board = new char[n][n];
        for (int i = 0; i < board.length; i++) {
            Arrays.fill(board[i], '.');
        }

        rec(n, 0, 0, 0, 0);
        return result;
    }

    public static void main(String[] args) {
        var res = new Solution().solveNQueens(4);
        return;
    }
}
