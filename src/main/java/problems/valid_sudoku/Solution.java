package problems.valid_sudoku;

public class Solution {

    public static boolean isValidSudoku(char[][] board) {

        // we can also use an array of hashsets
        int[] rows = new int[9];
        int[] cols = new int[9];
        int[] groups = new int[9];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                int group = (i / 3) * 3 + j / 3;

                if (Character.isDigit(board[i][j])) {
                    int h = 2 << board[i][j] - '0';

                    if ((rows[i] & h) != 0 || (cols[j] & h) != 0 || (groups[group] & h) != 0) {
                        return false;
                    }

                    rows[i] += h;
                    cols[j] += h;
                    groups[group] += h;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] board = new char[][] {
                { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
                { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
                { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
                { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
        };

        char[][] invalidBoard = new char[][] {
                { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '3', '.', '1', '9', '5', '.', '.', '.' },
                { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
                { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
                { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
        };

        isValidSudoku(invalidBoard);
        isValidSudoku(board);
    }

}
