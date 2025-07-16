package problems.palindromic_substrings;

public class Solution {
    public int countSubstrings(String s) {
        boolean[][] d = new boolean[s.length()][s.length()];
        int res = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || d[i + 1][j - 1])) {
                    d[i][j] = true;
                    res++;
                }
            }
        }

        return res;
    }

    // two pointers, nothing too clever, still n^2
    public int countSubstringsTwoPointers(String s) {
        int res = 0;
        int l, r;

        for (int i = 0; i < s.length(); i++) {
            // odd lengths
            l = r = i;

            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
                res++;
            }

            // even lengths
            l = i;
            r = i + 1;
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
                res++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().countSubstrings("babad"));
    }
}
