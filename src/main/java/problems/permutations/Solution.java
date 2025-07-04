package problems.permutations;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {

    private List<List<Integer>> permutations = new ArrayList<>();

    public List<List<Integer>> permuteIterative(int[] nums) {

        Queue<List<Integer>> q = new LinkedList<>();
        q.offer(new ArrayList<>());

        for (int n : nums) {
            int qs = q.size();

            for (int i = 0; i < qs; i++) {
                var cur = q.poll();

                for (int j = 0; j <= cur.size(); j++) {
                    var newCur = new ArrayList<>(cur);
                    newCur.add(j, n);
                    q.offer(newCur);
                }
            }
        }

        List<List<Integer>> res = new ArrayList<>();

        while (!q.isEmpty()) {
            res.add(q.poll());
        }

        return res;
    }

    public void permute(int[] nums, int index, List<Integer> cur, boolean[] picked) {
        if (index == nums.length) {
            if (cur.size() == nums.length) {
                permutations.add(new ArrayList<>(cur));
            }
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!picked[i]) {
                cur.add(nums[i]);
                picked[i] = true;

                permute(nums, index + 1, cur, picked);

                picked[i] = false;
                cur.removeLast();
            }
        }
    }

    public void permuteBitMask(int[] nums, int index, List<Integer> cur, int mask) {
        if (index == nums.length) {
            if (cur.size() == nums.length) {
                permutations.add(new ArrayList<>(cur));
            }
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if ((mask & (1 << i)) == 0) {
                cur.add(nums[i]);
                permuteBitMask(nums, index + 1, cur, mask | (1 << i));
                cur.removeLast();
            }
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        permute(nums, 0, new ArrayList<>(), new boolean[nums.length]);
        return permutations;
    }

    public static void main(String[] args) {
        var res = new Solution().permute(new int[] { 1, 2, 3 });
        return;
    }
}
