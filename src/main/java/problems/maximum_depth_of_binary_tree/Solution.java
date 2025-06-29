package problems.maximum_depth_of_binary_tree;

import java.util.LinkedList;
import java.util.Queue;

import utils.TreeNode;

public class Solution {
    public int maxDepth(TreeNode root) {
        return maxDepthWithDfs(root);
    }

    public static int maxDepthWithDfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepthWithDfs(root.left), maxDepthWithDfs(root.right)) + 1;
    }

    public static int maxDepthWithQueue(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();

        if (root != null) {
            q.offer(root);
        }
        int res = 0;

        while (!q.isEmpty()) {
            int qs = q.size();
            res++;
            for (int i = 0; i < qs; i++) {
                var cur = q.poll();

                if (cur.left != null) {
                    q.offer(cur.left);
                }

                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        // var root = new TreeNode(1).left(new TreeNode(2)).right(new
        // TreeNode(3).left(new TreeNode(4)));
    }
}
