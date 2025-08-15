package problems.reorder_list;

public class Solution {

    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode setNext(ListNode node) {
            this.next = node;
            return this;
        }
    }

    // TODO RECURSION
    public void reorderListRec(ListNode head) {

    }

    // ITERATIVE
    // Intuition: End list = First half of the list merged with the second half of
    // the list as reversed
    // Find the second half start (fast-slow pointers) -> reverse second half ->
    // stitch first and second half together
    public void reorderList(ListNode head) {
        if (head.next == null) {
            return;
        }

        var reversedHead = reverseList(midOfList(head));
        mergeLists(head, reversedHead);
    }

    private ListNode midOfList(ListNode head) {
        ListNode fast = head, slow = head, prev = null;

        while (fast != null) {
            fast = fast.next;
            if (fast == null) {
                break;
            }

            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }

        if (prev != null) {
            prev.next = null;
        }
        return slow;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null, it = head;

        while (it != null) {
            var tmp = it.next;
            it.next = prev;
            prev = it;
            it = tmp;
        }

        return prev;
    }

    private void mergeLists(ListNode regular, ListNode reverse) {
        int i = 0;

        while (regular != null && reverse != null) {
            if (i++ % 2 == 0) {
                var tmp = regular.next;
                regular.next = reverse;
                regular = tmp;
            } else {
                var tmp = reverse.next;
                reverse.next = regular;
                reverse = tmp;
            }
        }
    }

    public static void main(String[] args) {
        in4();
    }

    public static void in4() {
        var s = new Solution();
        var head = new ListNode(1).setNext(new ListNode(2).setNext(new ListNode(3)));
        s.reorderList(head);
        return;
    }

    public static void in3() {
        var s = new Solution();
        var head = new ListNode(1);
        s.reorderList(head);
        return;
    }

    public static void in1() {
        var s = new Solution();
        var head = new ListNode(1)
                .setNext(new ListNode(2).setNext(new ListNode(3)
                        .setNext(new ListNode(4)
                                .setNext(new ListNode(5).setNext(new ListNode(6).setNext(new ListNode(7)))))));
        s.reorderList(head);
    }

    public static void in2() {
        var s = new Solution();
        var head = new ListNode(1)
                .setNext(new ListNode(2).setNext(new ListNode(3)
                        .setNext(new ListNode(4)
                                .setNext(new ListNode(5).setNext(new ListNode(6))))));
        s.reorderList(head);
    }
}
