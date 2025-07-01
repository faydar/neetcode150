package problems.happy_number;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    private int sumOfDigitSquares(int n) {
        int sum = 0;

        while (n > 0) {
            sum += (n % 10) * (n % 10);
            n /= 10;
        }

        return sum;
    }

    public boolean isHappy(int n) {
        Set<Integer> visited = new HashSet<>();

        while (n != 1) {
            if (visited.contains(n)) {
                return false;
            }
            visited.add(n);
            n = sumOfDigitSquares(n);
        }

        return true;
    }

    public boolean isHappyNoExtraSpace(int n) {
        int slow = n, fast = sumOfDigitSquares(n);

        while (slow != fast && fast != 1) {
            slow = sumOfDigitSquares(slow);
            fast = sumOfDigitSquares(sumOfDigitSquares(fast));
        }

        return fast == 1;
    }

    public static void main(String[] args) {
        var s = new Solution();

        s.isHappyNoExtraSpace(19);
        s.isHappyNoExtraSpace(2);
    }
}
