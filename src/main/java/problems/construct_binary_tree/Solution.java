package problems.construct_binary_tree;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public class TreeNode {
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

    int preOrderIndex = 0;

    private TreeNode rec(int is, int ie, int[] preorder,
            Map<Integer, Integer> inOrders) {
        if (is > ie) {
            return null;
        }

        var val = preorder[preOrderIndex++];
        var n = new TreeNode(val);

        n.left = rec(is, inOrders.get(val) - 1, preorder, inOrders);
        n.right = rec(inOrders.get(val) + 1, ie, preorder, inOrders);

        return n;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inOrderIndexes = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            inOrderIndexes.put(inorder[i], i);
        }

        return rec(0, preorder.length - 1, preorder, inOrderIndexes);
    }
}
