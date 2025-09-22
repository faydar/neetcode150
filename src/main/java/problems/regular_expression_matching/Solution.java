package problems.regular_expression_matching;

public class Solution {
    Boolean[][] d;

    private boolean rec(String s, int sIndex, String p, int pIndex) {
        if (pIndex == p.length()) {
            return sIndex == s.length();
        }

        if (d[sIndex][pIndex] != null) {
            return d[sIndex][pIndex];
        }

        boolean match = sIndex < s.length() && (p.charAt(pIndex) == '.' || s.charAt(sIndex) == p.charAt(pIndex));

        if (pIndex + 1 < p.length() && p.charAt(pIndex + 1) == '*') {
            // stop using * || keep using *
            d[sIndex][pIndex] = rec(s, sIndex, p, pIndex + 2) || (match && rec(s, sIndex + 1, p, pIndex));
        } else {
            d[sIndex][pIndex] = match && rec(s, sIndex + 1, p, pIndex + 1);
        }

        return d[sIndex][pIndex];
    }

    public boolean isMatch(String s, String p) {
        d = new Boolean[s.length() + 1][p.length() + 1];
        var r = rec(s, 0, p, 0);
        return r;
    }

    public static void main(String[] args) {
        var in = "bbbba";
        var reg = ".*a*a";
        var r = new Solution().isMatch(in, reg);
        return;
    }
}
