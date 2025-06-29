package problems.longest_substr_without_repetition;

import java.util.HashMap;
import java.util.HashSet;

public class Solution {

    // my initial solution ( Runtime is much better for this one? )
    public int lengthOfLongestSubstringV0(String s) {
        var h = new HashMap<Character, Integer>();
        int res = 0;
        var start = 0;

        for (int i = 0; i < s.length(); i++) {
            if (h.containsKey(s.charAt(i))) {
                start = Math.max(start, h.get(s.charAt(i)) + 1);
            }

            h.put(s.charAt(i), i);
            res = Math.max(res, i - start + 1);
        }
        return res;
    }

    // simpler
    public static int lengthOfLongestSubstring(String s) {
        var h = new HashSet<Character>();
        int res = 0;
        var start = 0;

        for (int i = 0; i < s.length(); i++) {
            while (h.contains(s.charAt(i))) {
                h.remove(s.charAt(start++));
            }

            h.add(s.charAt(i));
            res = Math.max(res, i - start + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));

    }
}
