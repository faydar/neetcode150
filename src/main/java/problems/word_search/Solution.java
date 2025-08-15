package problems.word_search;

public class Solution {

    int[][] dirs = new int[][] {
            { -1, 0 },
            { 0, 1 },
            { 1, 0 },
            { 0, -1 }
    };

    public boolean dfs(char[][] board, int x, int y, String word, int ind) {
        if (ind == word.length() - 1) {
            return true;
        }

        char tmp = board[x][y];
        board[x][y] = '*';
        boolean res = false;

        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];

            if (nx >= 0 && nx < board.length && ny >= 0 && ny < board[nx].length
                    && board[nx][ny] == word.charAt(ind + 1)) {
                res |= dfs(board, nx, ny, word, ind + 1);
            }
        }

        board[x][y] = tmp;
        return res;
    }

    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == word.charAt(0) && dfs(board, i, j, word, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        var board = new char[][] {
                { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' }
        };

        var r = new Solution().exist(board, "SEE");
        return;
    }
}
