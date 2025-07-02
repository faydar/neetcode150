package problems.validate_binary_search_tree;

import utils.TreeNode;

public class Solution {

    private boolean isValidBST(TreeNode root, Long minVal, Long maxVal) {
        if (root == null) {
            return true;
        }

        if (!(root.val > minVal && root.val < maxVal)) {
            return false;
        }

        return isValidBST(root.left, minVal, Math.min(maxVal, root.val))
                && isValidBST(root.right, Math.max(minVal, root.val), maxVal);
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
}
