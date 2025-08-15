package problems.serialize_and_deserialize_binary_tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Solution {
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

    // Encodes a tree to a single string.
    // in format val:index:leftIndex:rightIndex
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        Queue<Pair> q = new LinkedList<>();
        StringBuilder result = new StringBuilder();

        q.offer(new Pair(root, 0));
        int index = 0;

        while (!q.isEmpty()) {
            var p = q.poll();
            result.append(p.index);
            result.append(":");
            result.append(p.node.val);
            result.append(":");

            if (p.node.left != null) {
                q.offer(new Pair(p.node.left, ++index));
                result.append(index);
            } else {
                result.append("N");
            }

            result.append(":");
            if (p.node.right != null) {
                q.offer(new Pair(p.node.right, ++index));
                result.append(index);
            } else {
                result.append("N");
            }

            result.append(",");
        }

        return result.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }
        String[] nodesArr = data.split(",");
        Map<Integer, TreeNode> nodes = new HashMap<>();

        for (String elem : nodesArr) {
            String[] meta = elem.split(":");
            int index = Integer.parseInt(meta[0]);
            int val = Integer.parseInt(meta[1]);

            if (nodes.containsKey(index)) {
                nodes.get(index).val = val;
            } else {
                nodes.put(index, new TreeNode(val));
            }

            if (!meta[2].equals("N")) {
                int leftChildIndex = Integer.parseInt(meta[2]);
                var leftChild = new TreeNode();

                nodes.get(index).left = leftChild;
                nodes.put(leftChildIndex, leftChild);
            }

            if (!meta[3].equals("N")) {
                int rightChildIndex = Integer.parseInt(meta[3]);
                var rightChild = new TreeNode();

                nodes.get(index).right = rightChild;
                nodes.put(rightChildIndex, rightChild);
            }
        }

        return nodes.get(0);
    }

}
