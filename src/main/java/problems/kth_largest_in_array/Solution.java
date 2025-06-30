package problems.kth_largest_in_array;

import java.util.PriorityQueue;

public class Solution {

    // O(N*logN)
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int n : nums) {
            pq.add(n);

            if (pq.size() == k + 1) {
                pq.poll();
            }
        }

        return pq.poll();
    }

    // O(N)
    public static int findKthLargestByCounting(int[] nums, int k) {
        int[] c = new int[2 * 10000 + 1];

        int slide = 10000;

        for (int n : nums) {
            c[n + slide]++;
        }

        int p = 2 * 10000;

        while (k > 0) {
            k -= c[p];
            p--;
        }

        return p - slide + 1;
    }

    public static void main(String[] args) {

        System.out.println(findKthLargestByCounting(new int[] { 3, 2, 1, 5, 6, 4 }, 2));
        System.out.println(findKthLargestByCounting(new int[] { 3, 2, 3, 1, 2, 4, 5, 5, 6 }, 4));
    }
}
