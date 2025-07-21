package problems.permutation_in_string;

public class Solution {

    // intuition:
    // increment right by default
    // reduce count by default (negative values don't harm us)
    // reduce remainingMatch if the encountered char was something we need
    // if we found all matches, true
    // if we exceeded the s1 length, increment left
    // if after the increment we removed previously matched character, add to
    // remainingMatch because we lost it
    public boolean checkInclusion(String s1, String s2) {
        int[] counts = new int[26];

        for (Character c : s1.toCharArray()) {
            counts[c - 'a']++;
        }

        int l = 0, r = 0;
        int remainingMatch = s1.length();

        while (r < s2.length()) {
            if (counts[s2.charAt(r++) - 'a']-- > 0) {
                remainingMatch--;
            }

            if (remainingMatch == 0) {
                return true;
            }

            if (r - l == s1.length()) {
                var add = s2.charAt(l++);
                if (++counts[add - 'a'] > 0) {
                    remainingMatch++;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().checkInclusion("ab", "eidboboo"));
    }
}
