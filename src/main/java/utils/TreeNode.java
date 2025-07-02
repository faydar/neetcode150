package utils;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public TreeNode left(TreeNode node) {
        this.left = node;
        return this;
    }

    public TreeNode right(TreeNode node) {
        this.right = node;
        return this;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    public static TreeNode buildTree(Integer[] values) {
        if (values == null || values.length == 0 || values[0] == null) {
            return null;
        }

        TreeNode[] nodes = new TreeNode[values.length];

        for (int i = 0; i < values.length; i++) {
            if (values[i] != null) {
                nodes[i] = new TreeNode(values[i]);
            }
        }

        for (int i = 0; i < values.length; i++) {
            if (nodes[i] != null) {
                int leftIndex = 2 * i + 1;
                int rightIndex = 2 * i + 2;

                if (leftIndex < values.length) {
                    nodes[i].left = nodes[leftIndex];
                }
                if (rightIndex < values.length) {
                    nodes[i].right = nodes[rightIndex];
                }
            }
        }

        return nodes[0]; // root node
    }
}