package problems.number_of_one_bits;

public class Solution {
    public int hammingWeight(int n) {
        int res = 0;

        for (int i = 0; i <= 31; i++) {
            if ((n & (1 << i)) > 0) {
                res++;
            }
        }

        return res;
    }

    public int hammingWeightCountLeastSignificantBit(int n) {
        int res = 0;

        while (n > 0) {
            res += n & 1;
            n >>= 1;
        }

        return res;
    }

    /*
     * n & (n - 1) clears the least significant 1 bit in n.
     * Example:
     * n = 00101100 (binary for 44)
     * n - 1 = 00101011
     * n & (n-1) = 00101000
     */

    public int hammingWeightClearLeastSignificantBit(int n) {
        int res = 0;
        while (n > 0) {
            res++;
            n &= n - 1;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().hammingWeight(2147483645));
    }
}
