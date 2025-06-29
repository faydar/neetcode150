package problems.invert_binary_tree;

import java.util.LinkedList;
import java.util.Queue;

import utils.TreeNode;

public class Solution {
    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        var left = invertTree(root.right);
        var right = invertTree(root.left);
        root.left = left;
        root.right = right;

        return root;
    }

    public static TreeNode invertTreeBfs(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            var cur = q.poll();

            if (cur != null) {
                var tmp = cur.left;
                cur.left = cur.right;
                cur.right = tmp;

                q.offer(cur.left);
                q.offer(cur.right);
            }
        }

        return root;
    }

    public static void main(String[] args) {
        var root = new TreeNode(4)
                .left(new TreeNode(2)
                        .left(new TreeNode(1))
                        .right(new TreeNode(3)))
                .right(new TreeNode(7)
                        .left(new TreeNode(6))
                        .right(new TreeNode(9)));
        invertTreeBfs(root);
    }
}
