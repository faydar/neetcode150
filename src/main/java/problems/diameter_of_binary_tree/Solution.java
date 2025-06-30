package problems.diameter_of_binary_tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import utils.TreeNode;

public class Solution {

    /**
     * SOLUTION 2
     * Initialize max depths first
     * then calculate diameters
     */

    private static int maxPath_(TreeNode root, Map<TreeNode, Integer> d) {
        if (root == null || (root.left == null && root.right == null)) {
            return 0;
        }

        var left = maxPath_(root.left, d);
        var right = maxPath_(root.right, d);

        var res = Math.max(left, right) + 1;
        d.put(root, res);
        return res;
    }

    public static int diameterOfBinaryTree_(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();

        if (root == null) {
            return 0;
        }

        Map<TreeNode, Integer> d = new HashMap<>();
        maxPath_(root, d);

        q.offer(root);
        int res = 0;

        while (!q.isEmpty()) {
            var cur = q.poll();
            var leftLongest = d.getOrDefault(cur.left, 0);
            var rightLongest = d.getOrDefault(cur.right, 0);

            var curRes = leftLongest + rightLongest + (cur.left != null ? 1 : 0) + (cur.right != null ? 1 : 0);
            res = Math.max(res, curRes);

            if (cur.left != null) {
                q.offer(cur.left);
            }

            if (cur.right != null) {
                q.offer(cur.right);
            }
        }

        return res;
    }

    /**
     * SOLUTION 2
     */

    // can be done in same pass as well

    static int result = 0;

    // calculate max depth of node, and update diameter on the go
    public static int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // calculate max depths
        var left = dfs(root.left);
        var right = dfs(root.right);

        result = Math.max(result, left + right);

        return 1 + Math.max(left, right);
    }

    public static int diameterOfBinaryTree(TreeNode root) {
        result = 0;
        dfs((root));
        return result;
    }

    public static void main(String[] args) {
        var root = new TreeNode(1)
                .left(new TreeNode(2)
                        .left(new TreeNode(4))
                        .right(new TreeNode(5)))
                .right(new TreeNode(3));
        var root2 = new TreeNode(1).left(new TreeNode(2));
        var root3 = new TreeNode(4)
                .left(new TreeNode(2).left(new TreeNode(1)).right(new TreeNode(3)));
        System.out.println(diameterOfBinaryTree(root));
        System.out.println(diameterOfBinaryTree(root2));
        System.out.println(diameterOfBinaryTree(root3));

    }
}
