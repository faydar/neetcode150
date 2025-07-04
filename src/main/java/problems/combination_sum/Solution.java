package problems.combination_sum;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    private List<List<Integer>> result = new ArrayList<>();

    private void combinationSumBacktracking(int[] candidates, int target, int index, int sum, List<Integer> current) {
        if (sum > target) {
            return;
        }

        if (index == candidates.length) {
            if (sum == target) {
                result.add(new ArrayList<>(current));
            }

            return;
        }

        // don't take the value and go to next element
        combinationSumBacktracking(candidates, target, index + 1, sum, current);

        current.add(candidates[index]);

        // take the value and stay
        combinationSumBacktracking(candidates, target, index, sum + candidates[index], current);

        current.removeLast();
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        combinationSumBacktracking(candidates, target, 0, 0, new ArrayList<>());
        return result;
    }

    public static void main(String[] args) {
        var s = new Solution();
        s.combinationSum(new int[] { 2 }, 1);
    }
}
