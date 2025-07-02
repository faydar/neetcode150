package problems.binary_tree_right_side_view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import utils.TreeNode;

public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return List.of();
        }

        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int qs = q.size();
            TreeNode rightMost = null;

            for (int i = 0; i < qs; i++) {
                var p = q.poll();

                if (p != null) {
                    q.add(p.left);
                    q.add(p.right);
                    rightMost = p;
                }
            }

            if (rightMost != null) {
                result.add(rightMost.val);
            }
        }

        return result;
    }
}
