package problems.merge_two_sorted_lists;

import utils.ListNode;

public class Solution {

    public ListNode mergeTwoListsIteration(ListNode l1, ListNode l2) {
        var it = new ListNode(-1);
        var root = it;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                it.next = l1;
                l1 = l1.next;
            } else {
                it.next = l2;
                l2 = l2.next;
            }

            it = it.next;
        }

        if (l1 != null) {
            it.next = l1;
        } else {
            it.next = l2;
        }

        return root.next;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        if (l1.val <= l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    public static void main(String[] args) {
        var n1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        var n2 = new ListNode(1, new ListNode(3, new ListNode(4)));

        var res = new Solution().mergeTwoListsIteration(n1, n2);
        return;
    }
}
