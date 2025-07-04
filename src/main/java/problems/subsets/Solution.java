package problems.subsets;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<List<Integer>> result = new ArrayList<>();

    public void rec(int[] nums, int index, List<Integer> subset) {
        if (index == nums.length) {
            result.add(new ArrayList<>(subset));
            return;
        }

        rec(nums, index + 1, subset);
        subset.add(nums[index]);
        rec(nums, index + 1, subset);
        subset.removeLast();
    }

    public List<List<Integer>> subsets(int[] nums) {
        rec(nums, 0, new ArrayList<>());
        return result;
    }

    public List<List<Integer>> subsetsBitManipulation(int[] nums) {
        int space = 1 << nums.length;
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < space; i++) {
            List<Integer> subset = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                if ((i & (1 << j)) != 0) {
                    subset.add(nums[j]);
                }
            }

            result.add(subset);
        }

        return result;
    }

    public static void main(String[] args) {
        var s = new Solution();
        s.subsetsBitManipulation(new int[] { 1, 2, 2 });
        return;
    }
}