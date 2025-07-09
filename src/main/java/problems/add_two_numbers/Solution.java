package problems.add_two_numbers;

import utils.ListNode;

public class Solution {

    private ListNode rec(ListNode l1, ListNode l2, int rem) {
        if (l1 == null && l2 == null && rem == 0) {
            return null;
        }

        int l1Val = l1 != null ? l1.val : 0;
        int l2Val = l2 != null ? l2.val : 0;

        int nval = l1Val + l2Val + rem;
        rem = nval / 10;

        var next = rec(l1 != null ? l1.next : null, l2 != null ? l2.next : null, rem);
        return new ListNode(nval % 10, next);
    }

    public ListNode addTwoNumbersRec(ListNode l1, ListNode l2) {
        return rec(l1, l2, 0);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int rem = 0;
        var root = new ListNode(-1);
        var it = root;

        while (l1 != null || l2 != null || rem != 0) {
            int l1Val = l1 != null ? l1.val : 0;
            int l2Val = l2 != null ? l2.val : 0;

            int nval = l1Val + l2Val + rem;
            rem = nval / 10;

            it.next = new ListNode(nval % 10);
            it = it.next;

            if (l1 != null) {
                l1 = l1.next;
            }

            if (l2 != null) {
                l2 = l2.next;
            }
        }

        return root.next;
    }

    public static void main(String[] args) {
        var in1 = new ListNode(2, new ListNode(4, new ListNode(5)));
        var in2 = new ListNode(5, new ListNode(6, new ListNode(4, new ListNode(9))));

        var res = new Solution().addTwoNumbersRec(in1, in2);
        return;
    }
}
