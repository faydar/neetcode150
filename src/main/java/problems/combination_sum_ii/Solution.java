package problems.combination_sum_ii;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.IntStream;

public class Solution {

    private List<List<Integer>> result = new ArrayList<>();

    private void combinationSum2(int[] candidates, int target, int index, int total, List<Integer> cur) {
        if (index == candidates.length) {
            if (total == target) {
                result.add(new ArrayList<>(cur));
            }

            return;
        }

        if (total > target) {
            return;
        }

        cur.add(candidates[index]);
        combinationSum2(candidates, target, index + 1, total + candidates[index], cur);
        cur.removeLast();

        while (index + 1 < candidates.length && candidates[index] == candidates[index + 1]) {
            index++;
        }

        combinationSum2(candidates, target, index + 1, total, cur);

    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        combinationSum2(candidates, target, 0, 0, new ArrayList<>());
        return result;
    }

    public static void main(String[] args) {
        int[] in = new int[] { 10, 1, 2, 7, 6, 1, 5 };
        var res = new Solution().combinationSum2(in, 8);
        return;
    }
}
