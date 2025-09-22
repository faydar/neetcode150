package problems.merge_k_sorted_linked_lists;

import java.util.PriorityQueue;


public class Solution {

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // O(N*K) where K is number of lists, N is number of nodes accross all lists
    // pick the smallest one by one
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode tmp = new ListNode(-1);
        ListNode root = tmp;

        while (true) {
            int minNodeVal = Integer.MAX_VALUE;
            int minNodeIndex = -1;

            for (int i = 0; i < lists.length; i++) {
                if (lists[i] != null && lists[i].val < minNodeVal) {
                    minNodeVal = lists[i].val;
                    minNodeIndex = i;
                }
            }

            if (minNodeIndex == -1) {
                break;
            }

            tmp.next = lists[minNodeIndex];
            tmp = tmp.next;

            lists[minNodeIndex] = lists[minNodeIndex].next;
        }

        return root.next;
    }

    // improved -> O(N * logK)
    // uses min heap, extra space O(N)
    // instead of finding min ourselves, use a heap
    public ListNode mergeKListsHeap(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));
        for (ListNode node : lists) {
            if (node != null) {
                pq.add(node);
            }
        }

        ListNode tmp = new ListNode(0);
        var root = tmp;

        while (!pq.isEmpty()) {
            var cur = pq.poll();
            tmp.next = cur;
            tmp = tmp.next;

            cur = cur.next;
            if (cur != null) {
                pq.add(cur);
            }
        }

        return root.next;
    }

    // still O(NlogK), but beats 99.86
    public ListNode mergeKListsDivideAndConquer(ListNode[] lists) {
        return divideAndConquer(lists, 0, lists.length - 1);
    }

    private ListNode divideAndConquer(ListNode[] lists, int l, int r) {
        if (l > r) {
            return null;
        }

        if (l == r) {
            return lists[l];
        }

        int m = l + (r - l) / 2;

        // divide
        ListNode left = divideAndConquer(lists, l, m);
        ListNode right = divideAndConquer(lists, m + 1, r);

        ListNode tmp = new ListNode(0);
        ListNode root = tmp;

        // conquer: stitch two sorted lists together
        while (left != null && right != null) {
            if (left.val < right.val) {
                tmp.next = left;
                left = left.next;
            } else {
                tmp.next = right;
                right = right.next;
            }

            tmp = tmp.next;
        }

        if (left != null) {
            tmp.next = left;
        } else {
            tmp.next = right;
        }

        return root.next;
    }

    public static void main(String[] args) {
        var l1 = new ListNode(1, new ListNode(4, new ListNode(5)));
        var l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        var l3 = new ListNode(2, new ListNode(6));
        var r = new Solution().mergeKLists(new ListNode[] { l1, l2, l3 });
    }
}
