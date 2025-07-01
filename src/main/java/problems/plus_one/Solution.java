package problems.plus_one;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public int[] plusOne(int[] digits) {
        boolean carry = true;
        List<Integer> result = new ArrayList<>();

        for (int i = digits.length - 1; i >= 0; i--) {
            var s = digits[i] + (carry ? 1 : 0);
            result.add(s % 10);
            carry = s >= 10;
        }

        if (carry) {
            result.add(1);
        }

        var r = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            r[i] = result.get(result.size() - i - 1);
        }

        return r;
    }

    public int[] plusOneNoExtraSpace(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }

            digits[i] = 0;
        }

        int[] result = new int[digits.length + 1];
        result[0] = 1;

        return result;
    }

    public static void main(String[] args) {
        var s = new Solution();

        int[] res = s.plusOne(new int[] { 0 });
        return;
    }
}
