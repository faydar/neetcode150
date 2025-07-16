package problems.longest_palindromic_substring;

import java.util.Arrays;

public class Solution {

    public String longestPalindrome(String s) {
        boolean[][] d = new boolean[s.length()][s.length()];

        int maxLen = 0;
        int rs=0, re=0;

        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || d[i + 1][j - 1])) {
                    d[i][j] = true;

                    if (j - i + 1 > maxLen) {
                        maxLen = j - i + 1;
                        rs = i;
                        re = j;
                    }
                }
            }
        }

        return s.substring(rs, re + 1);
    }

    public static void main(String[] args) {
        new Solution().longestPalindrome("cbbd");
    }

    // initial recursion solution, not looking so good but passes
    private int rec(String s, int i, int j, int[][] d) {
        if (i > j) {
            return 0;
        }

        if (d[i][j] != -1) {
            return d[i][j];
        }

        if (i + 1 == j) {
            d[i][j] = s.charAt(i) == s.charAt(j) ? 2 : 0;
            return d[i][j];
        }

        rec(s, i + 1, j, d);
        rec(s, i, j - 1, d);
        rec(s, i + 1, j - 1, d);

        if (s.charAt(i) == s.charAt(j)) {
            var mid = rec(s, i + 1, j - 1, d);
            d[i][j] = mid > 0 ? mid + 2 : 0;
        } else {
            d[i][j] = 0;
        }

        return d[i][j];
    }

    public String longestPalindromeDpRec(String s) {
        int[][] d = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            Arrays.fill(d[i], -1);
        }

        for (int i = 0; i < s.length(); i++) {
            d[i][i] = 1;
        }

        rec(s, 0, s.length() - 1, d);

        int maxLen = 0;
        String result = "";

        for (int i = 0; i < d.length; i++) {
            for (int j = i; j < d[i].length; j++) {
                if (d[i][j] > maxLen) {
                    maxLen = d[i][j];
                    result = s.substring(i, j + 1);
                }
            }
        }

        return result;
    }
}
