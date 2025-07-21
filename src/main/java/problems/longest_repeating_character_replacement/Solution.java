package problems.longest_repeating_character_replacement;

public class Solution {

    public int characterReplacement(String s, int k) {
        int[] counts = new int[26];
        int primaryCount = 0;
        int i = 0;
        int res = 0;
        for (int j = 0; j < s.length(); j++) {
            counts[s.charAt(j) - 'A']++;
            primaryCount = Math.max(primaryCount, counts[s.charAt(j) - 'A']);

            while (j - i + 1 - primaryCount > k) {

                // TRICK! SO THAT WE DON'T HAVE TO CHECK MAX COUNT AT EVERY WINDOW BY
                // O(26)!

                // intuition & trick: we don't have to decrement the max frequency even when we
                // increment Left pointer.
                // reason: if we ever to increase our "res", we would need a higher max
                // frequency for it.
                // if it stays the same or decreases, our "res" will never change, so we don't
                // need to care.

                // for a "len" to be valid: len - maxFrequency <= k
                // if out maxFrequency didn't increase, we won't get a higher len
                // hence res stays correct even if we don't hold the current maxFreq correctly
                counts[s.charAt(i) - 'A']--;
                i++;
            }

            res = Math.max(res, j - i + 1);
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().characterReplacement("AABABBA", 1));
        System.out.println(new Solution().characterReplacement("ABAB", 2));
        System.out.println(new Solution().characterReplacement("AAAB", 1));
        System.out.println(new Solution().characterReplacement("ABBB", 1));
        System.out.println(new Solution().characterReplacement("AAAAABBBBCBB", 4));
        System.out.println(new Solution().characterReplacement("AAAABBCDEBBBBBBBBBB", 4));
        System.out.println(new Solution().characterReplacement("AAAB", 0));
    }

    void cases() {

    }
}
