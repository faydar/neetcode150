package problems.reverse_list;

import utils.ListNode;

public class Solution {

    private ListNode reverseListRec(ListNode prev, ListNode head) {
        if (head == null) {
            return prev;
        }

        var tmp = head.next;
        head.next = prev;
        prev = head;

        return reverseListRec(head, tmp);
    }

    public ListNode reverseListRec(ListNode head) {
        return reverseListRec(null, head);
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;

        while (cur != null) {
            var tmp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tmp;
        }

        return prev;
    }

    public static void main(String[] args) {
        var s = new Solution();
        var in = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        var out = s.reverseListRec(in);
        return;
    }
}
