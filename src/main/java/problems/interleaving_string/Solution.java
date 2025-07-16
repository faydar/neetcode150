package problems.interleaving_string;

import java.util.Arrays;

public class Solution {

    private Boolean[][] d;

    private boolean bottomUp(String s1, String s2, String s3) {
        boolean[][] dd = new boolean[s1.length() + 1][s2.length() + 1];

        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        dd[s1.length()][s2.length()] = true;

        for (int i = s1.length(); i >= 0; i--) {
            for (int j = s2.length(); j >= 0; j--) {
                if (i < s1.length() && s3.charAt(i + j) == s1.charAt(i) && dd[i + 1][j]) {
                    dd[i][j] = true;
                } else if (j < s2.length() && s3.charAt(i + j) == s2.charAt(j) && dd[i][j + 1]) {
                    dd[i][j] = true;
                }
            }
        }

        return dd[0][0];
    }

    private boolean rec(char[] s1, int s1p, char[] s2, int s2p, char[] s3, int s3p) {
        if (s3p == s3.length) {
            return s1.length == s1p && s2.length == s2p;
        }

        if (d[s1p][s2p] != null) {
            return d[s1p][s2p];
        }

        boolean res = false;

        if (s1p < s1.length && s1[s1p] == s3[s3p]) {
            res = rec(s1, s1p + 1, s2, s2p, s3, s3p + 1);
        }

        if (!res && s2p < s2.length && s2[s2p] == s3[s3p]) {
            res = rec(s1, s1p, s2, s2p + 1, s3, s3p + 1);
        }

        d[s1p][s2p] = res;

        return res;
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        d = new Boolean[s1.length() + 1][s2.length() + 1];
        return bottomUp(s1, s2, s3);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isInterleave("aabcc", "dbbca", "aadbbcbcac"));
    }
}
