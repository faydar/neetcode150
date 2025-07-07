package problems.valid_anagram;

public class Solution {
    public boolean isAnagram(String a, String b) {
        int[] count = new int[26];

        for (Character c : a.toCharArray()) {
            count[c - 'a']++;
        }

        int matched = 0;

        for (Character c : b.toCharArray()) {
            if (count[c - 'a'] == 0) {
                return false;
            }

            count[c - 'a']--;
            matched++;
        }

        return matched == a.length();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isAnagram("rat", "car"));
    }
}
