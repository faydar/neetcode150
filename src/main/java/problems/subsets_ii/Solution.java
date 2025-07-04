package problems.subsets_ii;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    private List<List<Integer>> result = new ArrayList<>();

    public void rec(int[] nums, int index, List<Integer> current) {
        if (index == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        current.add(nums[index]);
        rec(nums, index + 1, current);

        current.removeLast();
        while (index + 1 < nums.length && nums[index] == nums[index + 1]) {
            index++;
        }

        rec(nums, index + 1, current);
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        rec(nums, 0, new ArrayList<>());
        return result;
    }

    public static void main(String[] args) {
        var res = new Solution().subsetsWithDup(new int[] { 1, 2, 1 });
        return;
    }
}
