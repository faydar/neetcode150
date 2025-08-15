package problems.binary_tree_maximum_path_sum;

import java.util.List;

public class Solution {

    static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private int result = Integer.MIN_VALUE;

    private int rec(TreeNode cur, int sum) {
        if (cur == null) {
            return 0;
        }

        var leftMaxPath = rec(cur.left, sum);
        var rightMaxPath = rec(cur.right, sum);

        int maxPathWithSelf = Math.max(0, Math.max(rightMaxPath, leftMaxPath)) + cur.val;

        // only cur, cur + left, cur + right, cur + left + right
        result = Math.max(result, Math.max(maxPathWithSelf, leftMaxPath + cur.val + rightMaxPath));

        // have to pick left or right sub tree + self
        // only cur, cur + left, cur + right
        return maxPathWithSelf;
    }

    public int maxPathSum(TreeNode root) {
        rec(root, 0);
        return result;
    }
}
