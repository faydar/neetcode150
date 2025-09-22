package problems.distinct_subsequences;

import java.util.Arrays;

public class Solution {

    int[][] d;

    // top down
    public int rec(String s, int is, String t, int it) {
        if (it < 0) {
            return 1;
        }

        if (is < 0) {
            return 0;
        }

        if (d[it][is] != -1) {
            return d[it][is];
        }

        d[it][is] = rec(s, is - 1, t, it);

        if (s.charAt(is) == t.charAt(it)) {
            d[it][is] += rec(s, is - 1, t, it - 1);
        }

        return d[it][is];
    }

    public int numDistinct(String s, String t) {
        d = new int[t.length()][s.length()];
        for (int i = 0; i < t.length(); i++) {
            Arrays.fill(d[i], -1);
        }

        rec(s, s.length() - 1, t, t.length() - 1);
        return d[t.length() - 1][s.length() - 1];
    }

    // bottom up
    public int numDistinct2(String s, String t) {
        int[][] d = new int[s.length() + 1][t.length() + 1];

        for (int i = 0; i <= s.length(); i++) {
            d[i][t.length()] = 1;
        }

        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = t.length() - 1; j >= 0; j--) {
                d[i][j] += d[i + 1][j];

                if (s.charAt(i) == t.charAt(j)) {
                    d[i][j] += d[i + 1][j + 1];
                }
            }
        }

        return d[0][0];
    }

    public static void main(String[] args) {
        var res = new Solution().numDistinct2("rabbbit", "rabbit");
        return;
    }
}
