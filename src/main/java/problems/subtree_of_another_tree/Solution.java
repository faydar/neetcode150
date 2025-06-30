package problems.subtree_of_another_tree;

import utils.TreeNode;

public class Solution {
    public static boolean dfs(TreeNode root, TreeNode subRoot, boolean strict) {
        if (root == null || subRoot == null) {
            return subRoot == null && root == null;
        }

        if (root.val == subRoot.val
                && dfs(root.left, subRoot.left, true)
                && dfs(root.right, subRoot.right, true)) {
            return true;
        }

        return !strict && (dfs(root.left, subRoot, strict) || dfs(root.right, subRoot, strict));
    }

    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        return dfs(root, subRoot, false);
    }
}
