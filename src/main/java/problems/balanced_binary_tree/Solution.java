package problems.balanced_binary_tree;

import utils.TreeNode;

public class Solution {

    boolean valid = true;

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        var left = dfs(root.left);
        var right = dfs(root.right);

        valid = valid && Math.abs(left - right) <= 1;
        return 1 + Math.max(left, right);
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        dfs(root);
        return valid;
    }
}
