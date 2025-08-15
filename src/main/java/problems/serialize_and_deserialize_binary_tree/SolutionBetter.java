package problems.serialize_and_deserialize_binary_tree;

import java.util.LinkedList;
import java.util.Queue;

public class SolutionBetter {
    static class TreeNode {

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

    static record Pair(TreeNode node, int index) {

    }

    // Intuition: Every node has its left and right child next to it
    // (We don't need to fill the full tree with null elements) -> Since we can not
    // add children of null nodes, this doesn't create a problem

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        StringBuilder res = new StringBuilder();

        while (!q.isEmpty()) {
            var p = q.poll();

            if (p == null) {
                res.append("N");
            } else {
                res.append(p.val);
                q.offer(p.left);
                q.offer(p.right);
            }

            res.append(",");
        }

        var rr = res.toString();
        return rr;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodes = data.split(",");

        if (nodes[0].equals("N")) {
            return null;
        }
        
        Queue<TreeNode> q = new LinkedList<>();

        var root = new TreeNode(Integer.parseInt(nodes[0]));
        q.offer(root);

        int index = 0;
        while (!q.isEmpty()) {
            var p = q.poll();

            index++;
            // left child
            if (!nodes[index].equals("N")) {
                var nn = new TreeNode(Integer.parseInt(nodes[index]));
                p.left = nn;
                q.offer(nn);
            }

            index++;
            // right child
            if (!nodes[index].equals("N")) {
                var nn = new TreeNode(Integer.parseInt(nodes[index]));
                p.right = nn;
                q.offer(nn);
            }
        }

        return root;
    }

    public static void main(String[] args) {
        var root = new TreeNode(1, new TreeNode(2, new TreeNode(4), null),
                new TreeNode(3, null, new TreeNode(5, new TreeNode(6), new TreeNode(7))));
        var s = new SolutionBetter();
        var res = s.deserialize(s.serialize(null));
        return;
    }
}
