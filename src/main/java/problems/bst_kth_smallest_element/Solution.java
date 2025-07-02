package problems.bst_kth_smallest_element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.TreeNode;

public class Solution {

    private void inOrderTraversal(TreeNode node, List<Integer> inOrder) {
        if (node == null) {
            return;
        }

        inOrderTraversal(node.left, inOrder);
        inOrder.add(node.val);
        inOrderTraversal(node.right, inOrder);
    }

    public int kthSmallestWithInOrderTraversal(TreeNode root, int k) {
        List<Integer> l = new ArrayList<>();
        inOrderTraversal(root, l);
        return l.get(k - 1);
    }

    private int sizeAugmentedTreeNodes(TreeNode node, Map<TreeNode, Integer> sizes) {
        if (node == null) {
            return 0;
        }

        var res = sizeAugmentedTreeNodes(node.left, sizes) + sizeAugmentedTreeNodes(node.right, sizes) + 1;
        sizes.put(node, res);
        return res;
    }

    private int kthSmallest(TreeNode root, int k, Map<TreeNode, Integer> sizes) {
        var tmp = root;
        var kn = k;

        while (tmp != null) {
            int leftSize = sizes.getOrDefault(tmp.left, 0);

            if (leftSize + 1 == kn) {
                return tmp.val;
            }

            if (leftSize >= kn) {
                tmp = root.left;
            } else {
                tmp = root.right;
                kn = kn - leftSize - 1;
            }
        }

        return 0;
    }

    public int kthSmallest(TreeNode root, int k) {
        Map<TreeNode, Integer> sizes = new HashMap<>();
        sizeAugmentedTreeNodes(root, sizes);
        return kthSmallest(root, k, sizes);
    }

    public static void main(String[] args) {
        var s = new Solution();
        var in = TreeNode.buildTree(new Integer[] { 5, 3, 6, 2, 4, null, null, 1 });
        for (int i = 1; i <= 6; i++) {
            System.out.println(s.kthSmallest(in, i));
        }
        return;
    }
}
