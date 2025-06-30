package problems.kth_largest_in_stream;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class KthLargestWithPriorityQueue {

    private Queue<Integer> pq;
    private int k;

    public KthLargestWithPriorityQueue(int k, int[] nums) {
        this.k = k;
        this.pq = new PriorityQueue<>();

        for (int n : nums) {
            this.add(n);
        }
    }

    public int add(int val) {
        pq.add(val);

        if (pq.size() == k + 1) {
            pq.poll();
        }

        return pq.peek();
    }

    public static void main(String[] args) {
        var p = new KthLargestWithPriorityQueue(3, new int[] { 4, 5, 8, 2 });

        List.of(3, 5, 10, 9, 4)
                .stream()
                .forEach(e -> {
                    System.out.println(p.add(e));
                });
    }
}
