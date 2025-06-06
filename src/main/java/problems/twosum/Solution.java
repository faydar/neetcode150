package problems.twosum;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import utils.Pair;

public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        List<Integer> input = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            input.add(scanner.nextInt());
        }

        var target = scanner.nextInt();
        int[] output = twoSum(input.stream().mapToInt(i -> i).toArray(), target);

        for (int i = 0; i < output.length; i++) {
            System.out.println(output[i]);
        }

        scanner.close();
    }

    private static int[] twoSum(int[] nums, int target) {
        List<Pair<Integer>> pairs = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            pairs.add(new Pair<Integer>(nums[i], i));
        }

        pairs.sort(Comparator.comparingInt(Pair<Integer>::getFirst));

        for (int i = 0, j = pairs.size() - 1; i < j;) {
            var first = pairs.get(i).getFirst();
            var second = pairs.get(j).getFirst();

            if (first + second == target) {
                return new int[] { pairs.get(i).getSecond(), pairs.get(j).getSecond() };
            }

            if (first + second < target) {
                i++;
            } else {
                j--;
            }
        }

        return new int[] { -1, -1 };
    }
}
