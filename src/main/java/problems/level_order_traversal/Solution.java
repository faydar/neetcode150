package problems.level_order_traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import utils.TreeNode;

public class Solution {

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return List.of();
        }

        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();

        q.add(root);

        while (!q.isEmpty()) {
            List<Integer> l = new ArrayList<>();
            var qs = q.size();

            for (int i = 0; i < qs; i++) {
                var p = q.poll();
                l.add(p.val);

                if (p.left != null) {
                    q.add(p.left);
                }

                if (p.right != null) {
                    q.add(p.right);
                }
            }

            result.add(l);
        }

        return result;
    }

    public static void main(String[] args) {

    }
}
