package problems.decode_ways;

import java.util.Arrays;

public class Solution {

    public int dp(String s, int index, int[] d) {
        if (d[index] != -1) {
            return d[index];
        }

        if (index == 0) {
            d[0] = s.charAt(0) != '0' ? 1 : 0;
            return d[0];
        }

        int lastTwo = (s.charAt(index) - '0') + 10 * (s.charAt(index - 1) - '0');

        if (index == 1) {
            int r = 0;
            if (s.charAt(1) != '0') {
                r += dp(s, 0, d);
            }

            if (lastTwo >= 10 && lastTwo <= 26) {
                r++;
            }

            d[1] = r;
            return r;
        }

        int total = 0;

        if (s.charAt(index) != '0') {
            total += dp(s, index - 1, d);
        }

        if (index >= 1 && lastTwo >= 10 && lastTwo <= 26) {
            total += dp(s, index - 2, d);
        }

        d[index] = total;
        return total;
    }

    public int numDecodings(String s) {
        int[] d = new int[s.length()];
        Arrays.fill(d, -1);
        var res = dp(s, s.length() - 1, d);
        return res;
    }

    public int numDecodings2(String s) {
        if (s.charAt(0) == '0') {
            return 0;
        }

        if (s.length() <= 1) {
            return 1;
        }

        int[] d = new int[s.length()];

        d[0] = 1;
        d[1] += s.charAt(1) > '0' ? 1 : 0;

        int lastTwo = (s.charAt(1) - '0') + 10 * (s.charAt(0) - '0');
        d[1] += lastTwo >= 10 && lastTwo <= 26 ? 1 : 0;

        for (int i = 2; i < s.length(); i++) {
            if (s.charAt(i) != '0') {
                d[i] += d[i - 1];
            }

            lastTwo = (s.charAt(i) - '0') + 10 * (s.charAt(i - 1) - '0');
            if (lastTwo >= 10 && lastTwo <= 26) {
                d[i] += d[i - 2];
            }
        }

        return d[s.length() - 1];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().numDecodings2("2611055971756562"));
    }
}
