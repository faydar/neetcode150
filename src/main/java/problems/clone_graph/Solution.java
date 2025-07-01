package problems.clone_graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.w3c.dom.Node;

public class Solution {

    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }

        public void setNeighbors(List<Node> _neighbors) {
            this.neighbors = _neighbors;
        }
    }

    public Node cloneGraph(Node node) {
        return cloneGraph(node, new HashMap<>());
    }

    private Node cloneGraph(Node node, Map<Integer, Node> m) {
        if (node == null) {
            return null;
        }

        if (m.containsKey(node.val)) {
            return m.get(node.val);
        }

        var rn = new Node(node.val);
        m.put(node.val, rn);

        ArrayList<Node> neighbors = new ArrayList<>();

        for (Node n : node.neighbors) {
            var newNeighbor = cloneGraph(n, m);
            neighbors.add(newNeighbor);
        }

        rn.neighbors = neighbors;
        return rn;
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);

        var s = new Solution();
        var res = s.cloneGraph(n1);
        return;
    }
}
