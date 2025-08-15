package problems.copy_list_with_random_pointer;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Node;

public class Solution {
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    // With recursion
    public Map<Node, Node> nodesRec = new HashMap<>();

    public Node copyRandomListRec(Node head) {
        if (head == null) {
            return null;
        }

        if (nodesRec.containsKey(head)) {
            return nodesRec.get(head);
        }

        var nn = new Node(head.val);
        nodesRec.put(head, nn);
        nn.next = copyRandomList(head.next);
        nn.random = copyRandomList(head.random);

        return nn;
    }

    // With iterative, one pass to prepare copies, one pass to connect them
    public Node copyRandomList(Node head) {
        Map<Node, Node> nodes = new HashMap<>();

        Node tmp = head;
        while (tmp != null) {
            var nn = new Node(tmp.val);
            nodes.put(tmp, nn);
            tmp = tmp.next;
        }

        tmp = head;
        while (tmp != null) {
            var cn = nodes.get(tmp);
            cn.next = nodes.get(tmp.next);
            cn.random = nodes.get(tmp.random);
            tmp = tmp.next;
        }

        return nodes.get(head);
    }

    public static void main(String[] args) {
        var s = new Solution();

        var n1 = new Node(1);
        var n2 = new Node(2);

        n1.next = n2;
        n1.random = n2;
        n2.random = n2;

        var copied = s.copyRandomListRec(n1);
        return;
    }
}
