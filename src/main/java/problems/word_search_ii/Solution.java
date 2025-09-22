package problems.word_search_ii;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

    static class TrieNode {
        TrieNode[] children;
        boolean isLeaf;

        public TrieNode() {
            this.children = new TrieNode[26];
            isLeaf = false;
        }
    }

    static class Trie {
        TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        public void insert(String s) {
            var tmp = root;

            for (int i = 0; i < s.length(); i++) {
                var cc = s.charAt(i) - 'a';
                if (tmp.children[cc] == null) {
                    tmp.children[cc] = new TrieNode();
                }

                tmp = tmp.children[cc];
            }

            tmp.isLeaf = true;
        }

        public TrieNode find(String s) {
            var tmp = root;

            for (Character c : s.toCharArray()) {
                if (tmp.children[c - 'a'] == null) {
                    return null;
                }

                tmp = tmp.children[c - 'a'];
            }

            return tmp;
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        var trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        Set<String> result = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                dfs(i, j, board, trie.root.children[board[i][j] - 'a'], result, "" + board[i][j]);
            }
        }

        return new ArrayList<>(result);
    }

    private final int[][] DIRS = new int[][] {
            { -1, 0 },
            { 0, 1 },
            { 1, 0 },
            { 0, -1 }
    };

    private void dfs(int x, int y, char[][] board, TrieNode node, Set<String> result, String cur) {
        if (node == null) {
            return;
        }

        if (node.isLeaf) {
            result.add(cur);
        }

        var tmp = board[x][y];
        board[x][y] = '#';

        for (int[] dir : DIRS) {
            int nx = x + dir[0];
            int ny = y + dir[1];

            if (nx >= 0 && nx < board.length && ny >= 0 && ny < board[nx].length && board[nx][ny] != '#') {
                dfs(nx, ny, board, node.children[board[nx][ny] - 'a'], result, cur + board[nx][ny]);
            }
        }

        board[x][y] = tmp;
    }

    public static void main(String[] args) {
        var s = new Solution();
        var in = new char[][] {
                { 'o', 'a', 'a', 'n' },
                { 'e', 't', 'a', 'e' },
                { 'i', 'h', 'k', 'r' },
                { 'i', 'f', 'l', 'v' }
        };
        var words = new String[] { "oath", "pea", "eat", "rain" };
        var r = s.findWords(in, words);
        return;
    }
}
