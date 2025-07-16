package problems.word_break;

import java.util.List;

public class Solution {

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] d = new boolean[s.length() + 1];
        d[s.length()] = true;

        for (int i = s.length() - 1; i >= 0; i--) {
            for (String w : wordDict) {
                if (i + w.length() <= s.length() && s.substring(i, i + w.length()).equals(w)) {
                    d[i] = d[i + w.length()];
                }

                if (d[i]) {
                    break;
                }
            }
        }

        return d[0];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().wordBreak("leetcode", List.of("leet", "code")));
    }
}
