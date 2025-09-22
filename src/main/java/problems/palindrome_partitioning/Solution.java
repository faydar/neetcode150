package problems.palindrome_partitioning;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    List<List<String>> result = new ArrayList<>();

    // O(n * 2 ^ n)
    // 2 ^ n for every partition, n to check for palindrome
    public void rec(String in, int curStart, int curIndex, List<String> partitions, int partitionLen, boolean[][] dp) {
        if (curIndex == in.length()) {
            if (partitionLen == in.length()) {
                result.add(new ArrayList<>(partitions));
            }
            return;
        }

        if (dp[curStart][curIndex]) { // is palindrome between curStart,curIndex?
            partitions.add(in.substring(curStart, curIndex + 1));
            rec(in, curIndex + 1, curIndex + 1, partitions, partitionLen + curIndex + 1 - curStart, dp);
            partitions.removeLast();
        }

        rec(in, curStart, curIndex + 1, partitions, partitionLen, dp);
    }

    public List<List<String>> partition(String s) {
        // pre-calculate palindromes dp[i][j] -> is string s between i,j a palindrome
        // checking only when needed inside rec also results in similar runtime
        boolean[][] dp = new boolean[s.length()][s.length()];

        for (int len = 1; len <= s.length(); len++) {
            for (int i = 0; i <= s.length() - len; i++) {
                dp[i][i + len - 1] = s.charAt(i) == s.charAt(i + len - 1)
                        && (i + 1 > i + len - 2 || dp[i + 1][i + len - 2]);
            }
        }

        rec(s, 0, 0, new ArrayList<>(), 0, dp);
        return result;
    }

    public static void main(String[] args) {
        var s = new Solution();
        var r = s.partition("aabcb");
        return;
    }
}
