package problems.counting_bits;

public class Solution {
    public int[] countBits(int n) {
        int[] d = new int[n + 1];
        d[0] = 0;

        for (int i = 1; i <= n; i++) {
            d[i] = d[i >> 2] + (i & 1);
        }

        return d;
    }

    public static void main(String[] args) {
        var res = new Solution().countBits(128);
        return;
    }
}
