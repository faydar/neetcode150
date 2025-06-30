package problems.missing_number;

public class Solution {

    public static int missingNumber(int[] n) {
        var maxn = n.length;
        int expectedSum = (maxn * (maxn + 1)) / 2;

        for (int i = 0; i < maxn; i++) {
            expectedSum -= n[i];
        }

        return expectedSum;
    }

    /**
     * Depends on the XOR properties:
     * <ul>
     * <li>A ^ A = 0</li>
     * <li>A ^ 0 = A</li>
     * </ul>
     * 
     * <p>
     * "If we XOR all numbers from 0 to n, and XOR them with all numbers in the
     * array, the numbers that appear in both will cancel out — leaving us with the
     * missing number."
     * </p>
     * 
     * @param n
     * @return
     */
    public static int missingNumberXor(int[] n) {
        var maxn = n.length;
        int maskAll = 0;

        for (int i = 0; i <= maxn; i++) {
            maskAll = maskAll ^ i;
        }

        int maskArr = 0;
        for (int i = 0; i < n.length; i++) {
            maskArr = maskArr ^ n[i];
        }

        return maskAll ^ maskArr;
    }

    public static void main(String[] args) {
        System.out.println(missingNumberXor(new int[] { 0, 1, 2, 4 }));
    }
}
