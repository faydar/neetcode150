package utils;

import java.util.Optional;

public class BinarySearchTree<T extends Comparable<T>> {

    private Node<T> rootNode;

    public BinarySearchTree() {
        this.rootNode = null;
    }

    static class Node<T> {
        T val;
        Node<T> left;
        Node<T> right;

        public Node(T val) {
            this.val = val;
        }
    }

    public Optional<Node<T>> search(T val) {
        Node<T> tmp = rootNode;

        while (tmp != null) {
            T tmpVal = tmp.val;
            var comp = val.compareTo(tmpVal);

            if (comp == 0) {
                return Optional.of(tmp);
            }

            if (comp < 0) {
                tmp = tmp.left;
            } else {
                tmp = tmp.right;
            }
        }

        return Optional.ofNullable(tmp);
    }

    public void insert(T val) throws IllegalArgumentException {
        Node<T> nn = new Node<>(val);

        if (rootNode == null) {
            this.rootNode = nn;
            return;
        }

        Node<T> tmp = rootNode;
        boolean inserted = false;

        while (!inserted) {
            T tmpVal = tmp.val;
            var comp = val.compareTo(tmpVal);

            if (comp == 0) {
                throw new IllegalArgumentException("Duplicate value");
            }

            if (comp < 0) {
                if (tmp.left != null) {
                    tmp = tmp.left;
                } else {
                    tmp.left = nn;
                    inserted = true;
                }
            } else {
                if (tmp.right != null) {
                    tmp = tmp.right;
                } else {
                    tmp.right = nn;
                    inserted = true;
                }
            }
        }
    }

    public void delete(T val) {
        Node<T> node = rootNode;
        Node<T> parent = null;

        // find node to be deleted
        while (node != null) {
            T tmpVal = node.val;
            var comp = val.compareTo(tmpVal);

            if (comp == 0) {
                break;
            }

            parent = node;

            if (comp < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        if (node == null) {
            return;
        }

        // single or no child case
        if (node.left == null || node.right == null) {
            var singleChild = node.left != null ? node.left : node.right;

            if (node == rootNode) {
                rootNode = singleChild;
            } else if (node.val.compareTo(parent.val) > 0) {
                parent.right = singleChild;
            } else {
                parent.left = singleChild;
            }

            return;
        }

        // if not, find in order successor first (smallest node bigger than deleted
        // node)
        Node<T> inOrderSuccessorParent = node;
        Node<T> inOrderSuccessor = node.right;

        while (inOrderSuccessor != null && inOrderSuccessor.left != null) {
            inOrderSuccessorParent = inOrderSuccessor;
            inOrderSuccessor = inOrderSuccessor.left;
        }

        // copy in order successor data to deleted node's data
        node.val = inOrderSuccessor.val;

        // CASE: in order successor is the right child of the node to be deleted
        if (node.right == inOrderSuccessor) {
            node.right = inOrderSuccessor.right;
        } else {
            // if: in order successor is in left sub tree of the right child of the deleted
            // node
            inOrderSuccessorParent.left = inOrderSuccessor.right;
        }

        inOrderSuccessor = null;
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(5);
        bst.insert(2);
        bst.insert(4);

        var searched = bst.search(1);

        bst.insert(1);
        bst.insert(9);
        bst.insert(3);

        searched = bst.search(1);
        bst.delete(5);
        searched = bst.search(1);
        return;
    }
}
