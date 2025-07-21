package problems.reverse_bits;

public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i <= 31; i++) {
            res += (((n >> i) & 1) << (31 - i));
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().reverseBits(-3));
    }
    // 10111111111111111111111111111111
}
