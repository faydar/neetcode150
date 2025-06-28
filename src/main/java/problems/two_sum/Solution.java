package problems.two_sum;

import static utils.Print.printArray;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int len = sc.nextInt();
        int[] arr = new int[len];

        for (int i = 0; i < len; i++) {
            arr[i] = sc.nextInt();
        }

        int target = sc.nextInt();
        int[] res = twoSum(arr, target);

        printArray(res);
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> sumAtIndex = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            var rest = sumAtIndex.get(target - nums[i]);
            if (rest != null) {
                return new int[] { rest, i };
            }

            sumAtIndex.put(nums[i], i);
        }

        return new int[] { -1, -1 };
    }
}
