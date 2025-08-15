package problems.longest_common_subsequence;

public class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] d = new int[text1.length() + 1][text2.length() + 1];

        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    d[i][j] = d[i - 1][j - 1] + 1;
                } else {
                    d[i][j] = Math.max(d[i - 1][j], d[i][j - 1]);
                }
            }
        }

        return d[text1.length()][text2.length()];
    }

    public static void main(String[] args) {
        var s = new Solution();
        System.out.println(s.longestCommonSubsequence("abc", "abc"));
    }
}
