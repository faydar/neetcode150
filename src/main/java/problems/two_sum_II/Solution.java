package problems.two_sum_II;

public class Solution {
    public static int[] twoSum(int[] numbers, int target) {
        for (int i = 0, j = numbers.length - 1; i < j;) {
            var sum = numbers[i] + numbers[j];

            if (sum == target) {
                return new int[] { i+1, j+1 };
            } else if (sum > target) {
                j--;
            } else {
                i++;
            }
        }

        return new int[] { -1, -1 };
    }

    public static void main(String[] args) {
        twoSum(new int[] { 2, 7, 11, 15 }, 9);
    }
}
