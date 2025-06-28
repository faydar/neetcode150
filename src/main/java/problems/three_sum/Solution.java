package problems.three_sum;

import static utils.Print.printList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            // to avoid duplicates
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            for (int j = i + 1, k = nums.length - 1; k > j && j < nums.length;) {
                var sum = nums[i] + nums[j] + nums[k];

                if (sum == 0) {
                    result.add(List.of(nums[i], nums[j], nums[k]));

                    j++;

                    // to avoid duplicates
                    while (nums[j] == nums[j - 1] && j < k) {
                        j++;
                    }
                }

                if (sum > 0) {
                    k--;
                }

                if (sum < 0) {
                    j++;
                }
            }
        }

        return result;
    }

    private static void solveFor(int[] arr) {
        var result = threeSum(arr);
        printList(result);
    }

    public static void main(String[] args) {
        solveFor(new int[] { -1, 0, 1, 2, -1, -4 });
        // solveFor(new int[] { 0, 1, 1 });
        // solveFor(new int[] { 0, 0, 0 });
        // solveFor(new int[] { 0, 0, 0, 0 });
        solveFor(new int[] { 2, -3, 0, -2, -5, -5, -4, 1, 2, -2, 2, 0, 2, -4, 5, 5, -10 });
    }
}
