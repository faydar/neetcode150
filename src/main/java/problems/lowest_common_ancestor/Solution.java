package problems.lowest_common_ancestor;

import utils.TreeNode;

public class Solution {

    // can be converted to iteration very easily
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        int max = Math.max(p.val, q.val);
        int min = Math.min(p.val, q.val);

        // both nodes should be in left sub-tree
        if (max < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }

        // both nodes should be in right sub-tree
        if (min > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }

        // we are the first node that are between p and q
        return root;
    }

    public static void main(String[] args) {
        var s = new Solution();
        var root = new TreeNode(6);
        var _2 = new TreeNode(2);
        var _0 = new TreeNode(0);
        var _4 = new TreeNode(4);
        var _3 = new TreeNode(3);
        var _5 = new TreeNode(5);
        var _8 = new TreeNode(8);
        var _7 = new TreeNode(7);
        var _9 = new TreeNode(9);

        root.left(_2);
        _2.left(_0);
        _2.right(_4);
        _4.left(_3);
        _4.right(_5);
        root.right(_8);
        _8.left(_7);
        _8.right(_9);

        var res = s.lowestCommonAncestor(root, _2, _8);
        return;
    }
}
