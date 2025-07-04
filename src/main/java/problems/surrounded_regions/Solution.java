package problems.surrounded_regions;

import java.util.Arrays;

public class Solution {

    private int[][] dirs = new int[][] {
            { 0, -1 },
            { -1, 0 },
            { 1, 0 },
            { 0, 1 }
    };

    static class UnionFind {
        int[] parents;
        int[] sizes;

        public UnionFind(int size) {
            this.parents = new int[size + 1];
            this.sizes = new int[size + 1];

            for (int i = 0; i <= size; i++) {
                this.parents[i] = i;
            }

            Arrays.fill(this.sizes, 1);
        }

        public int find(int in) {
            if (parents[in] != in) {
                parents[in] = find(parents[in]);
            }

            return parents[in];
        }

        public void union(int s1, int s2) {
            var g1 = find(s1);
            var g2 = find(s2);

            if (g1 == g2) {
                return;
            }

            if (sizes[g1] > sizes[g2]) {
                parents[g2] = g1;
                sizes[g1] += sizes[g2];
            } else {
                parents[g1] = g2;
                sizes[g2] += sizes[g1];
            }
        }
    }

    private void dfs(char[][] board, int x, int y) {
        if (board[x][y] != 'O') {
            return;
        }

        board[x][y] = 'R';

        for (int i = 0; i < dirs.length; i++) {
            int nx = x + dirs[i][0];
            int ny = y + dirs[i][1];

            if (nx >= 0 && nx < board.length && ny >= 0 && ny < board[0].length) {
                dfs(board, nx, ny);
            }
        }

    }

    public void solveDfs(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            dfs(board, i, 0);
            dfs(board, i, board[i].length - 1);
        }

        for (int i = 0; i < board[0].length; i++) {
            dfs(board, 0, i);
            dfs(board, board.length - 1, i);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = board[i][j] == 'R' ? 'O' : 'X';
            }
        }
        return;
    }

    public void solve(char[][] board) {
        var uf = new UnionFind(board.length * board[0].length);
        var rowl = board[0].length;
        var BOARD_EDGE_GROUP = board.length * board[0].length;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') {
                    if (i == 0 || j == 0 || i == board.length - 1 || j == board[0].length - 1) {
                        // in shell, attach to a special group
                        uf.union(BOARD_EDGE_GROUP, rowl * i + j);
                    } else {
                        // attach to neighboring 'O'
                        for (int[] d : dirs) {
                            int nx = i + d[0];
                            int ny = j + d[1];

                            if (nx >= 0 && nx < board.length && ny >= 0 && ny < board[0].length
                                    && board[nx][ny] == 'O') {
                                int h1 = rowl * i + j;
                                int h2 = rowl * nx + ny;
                                uf.union(h1, h2);
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O' && uf.find(i * rowl + j) != uf.find(BOARD_EDGE_GROUP)) {
                    board[i][j] = 'X';
                }
            }
        }
        return;
    }

    public static void main(String[] args) {
        var s = new Solution();
        var in = new char[][] {
                { 'X', 'X', 'X', 'X' },
                { 'X', 'O', 'O', 'X' },
                { 'X', 'X', 'O', 'X' },
                { 'X', 'O', 'X', 'X' }
        };
        var in2 = new char[][] {
                { 'O', 'O', 'O' },
                { 'O', 'O', 'O' },
                { 'O', 'O', 'O' }
        };
        var in3 = new char[][] {
                { 'X', 'O', 'X', 'O', 'X', 'O' },
                { 'O', 'X', 'O', 'X', 'O', 'X' },
                { 'X', 'O', 'X', 'O', 'X', 'O' },
                { 'O', 'X', 'O', 'X', 'O', 'X' }
        };
        var in4 = new char[][] {
                { 'X', 'X', 'X', 'X', 'O', 'X' },
                { 'O', 'X', 'X', 'O', 'O', 'X' },
                { 'X', 'O', 'X', 'O', 'O', 'O' },
                { 'X', 'O', 'O', 'O', 'X', 'O' },
                { 'O', 'O', 'X', 'X', 'O', 'X' },
                { 'X', 'O', 'X', 'O', 'X', 'X' }
        };

        s.solveDfs(in);
    }
}
