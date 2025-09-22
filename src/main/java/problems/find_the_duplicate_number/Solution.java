package problems.find_the_duplicate_number;

public class Solution {

    // intuition: use the same array to mark seen elements
    // if a number n exists in array, update index (n-1) with negative of the number
    // in the index
    // O(N) and O(1) space
    public int findDuplicate(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            var e = Math.abs(nums[i]);

            if (nums[e - 1] < 0) {
                return e;
            }

            nums[e - 1] *= -1;
        }

        return -1;
    }

    // O(N) or O(32 * N)
    // intuition: compare {1, 2, 3, 3, 4} with {1, 2, 3, 4}
    // bits of extra 3 will add up in the loop
    public int findDuplicateBitManipulation(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int ithBitCountActual = 0;
            int mask = 1 << i;

            for (int n : nums) {
                if ((n & mask) != 0) {
                    ithBitCountActual++;
                }
            }

            int ithBitCountExpected = 0;
            for (int n = 1; n < nums.length; n++) {
                if ((n & mask) != 0) {
                    ithBitCountExpected++;
                }
            }

            if (ithBitCountActual > ithBitCountExpected) {
                // ith bit is 1 in our result
                res = res | mask;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        var r = new Solution().findDuplicateBitManipulation(new int[] { 3, 1, 3, 4, 2 });
        return;
    }
}
