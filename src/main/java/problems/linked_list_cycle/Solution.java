package problems.linked_list_cycle;

import utils.ListNode;

public class Solution {

    public boolean hasCycle(ListNode head) {
        var slow = head;
        var fast = head != null ? head.next : null;

        while (fast != null) {
            fast = fast.next;

            if (fast == null) {
                return false;
            }

            fast = fast.next;
            slow = slow.next;

            if (fast == slow) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        var s = new Solution();

        var ln3 = new ListNode(3);
        var ln2 = new ListNode(2);
        var ln0 = new ListNode(0);
        var ln4 = new ListNode(-4);

        ln3.setNext(ln2);
        ln2.setNext(ln0);
        ln0.setNext(ln4);
        ln4.setNext(ln2);

        System.out.println(s.hasCycle(new ListNode(1)));
    }
}
