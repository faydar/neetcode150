package problems.remove_nth_from_end;

import utils.ListNode;

public class Solution {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode front = head;
        ListNode back = head;
        ListNode backParent = null;
        int count = 0;

        while (front != null) {
            if (count >= n) {
                backParent = back;
                back = back.next;
            }

            front = front.next;
            count++;
        }

        if (backParent != null) {
            backParent.next = back.next;
            return head;
        }

        return head.next;
    }

    // h is actually one variable, but is an int array to pass by pointer
    private ListNode rec(ListNode cur, int[] h) {
        if (cur == null) {
            return null;
        }

        cur.next = rec(cur.next, h);
        h[0]--;

        // we found the nth from last
        if (h[0] == 0) {
            return cur.next;
        }

        return cur;
    }

    public ListNode removeNthFromEndRec(ListNode head, int n) {
        return rec(head, new int[] { n });
    }

    public static void main(String[] args) {
        var in = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        var res = new Solution().removeNthFromEndRec(in, 1);
        return;
    }
}
