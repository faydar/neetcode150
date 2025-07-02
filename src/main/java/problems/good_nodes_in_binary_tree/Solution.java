package problems.good_nodes_in_binary_tree;

import utils.TreeNode;

public class Solution {

    private int goodNodes(TreeNode root, int max) {
        if (root == null) {
            return 0;
        }

        var isGood = root.val >= max;
        var nMax = Math.max(max, root.val);

        return (isGood ? 1 : 0) + goodNodes(root.left, nMax) + goodNodes(root.right, nMax);
    }

    public int goodNodes(TreeNode root) {
        return goodNodes(root, Integer.MIN_VALUE);
    }

    public static void main(String[] args) {
        var s = new Solution();

        var root = TreeNode.buildTree(new Integer[] { -1, 5, -2, 4, 4, 2, -2, null, null, -4, null, -2, 3, null, -2, 0,
                null, -1, null, -3, null, -4, -3, 3, null, null, null, null, null, null, null, 3, -3 });

        var res = s.goodNodes(root);
        return;
    }
}
