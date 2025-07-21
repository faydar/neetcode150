package problems.edit_distance;

import java.util.Arrays;

public class Solution {

    // top-down
    // calculates result-1
    private int rec(String word1, String word2, int i1, int i2, int[][] d) {
        if (i1 == -1 || i2 == -1) {
            // if i1 == -1 -> add i2 + 1 elements to construct word2[0:i2]
            // if i2 == -1 -> remove i1+1 elements to construct empty string
            return Math.max(i1, i2);
        }

        if (d[i1][i2] != -1) {
            return d[i1][i2];
        }

        if (word1.charAt(i1) == word2.charAt(i2)) {
            return rec(word1, word2, i1 - 1, i2 - 1, d);
        }

        int del = rec(word1, word2, i1 - 1, i2, d) + 1;
        int rep = rec(word1, word2, i1 - 1, i2 - 1, d) + 1;
        int add = rec(word1, word2, i1, i2 - 1, d) + 1;
        d[i1][i2] = Math.min(add, Math.min(del, rep));
        return d[i1][i2];
    }

    private int bottomUp(String word1, String word2) {
        int[][] d = new int[word1.length() + 1][word2.length() + 1];

        for (int i = 1; i <= word2.length(); i++) {
            d[0][i] = i;
        }

        for (int i = 1; i <= word1.length(); i++) {
            d[i][0] = i;
        }

        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    d[i][j] = d[i - 1][j - 1];
                } else {
                    d[i][j] = Math.min(d[i - 1][j - 1], Math.min(d[i][j - 1], d[i - 1][j])) + 1;
                }
            }
        }

        return d[word1.length()][word2.length()];
    }

    public int minDistance(String word1, String word2) {
        return bottomUp(word1, word2);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minDistance("spartan", "part"));
        System.out.println(new Solution().minDistance("intention", "execution"));
        System.out.println(new Solution().minDistance("horse", "ros"));
        System.out.println(new Solution().minDistance("abc", "aec"));

    }
}
