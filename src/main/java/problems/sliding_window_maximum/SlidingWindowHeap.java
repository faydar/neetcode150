package problems.sliding_window_maximum;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class SlidingWindowHeap {
    // Heap O(NlogN)
    // add to heap, cleanup heap until current maximum is in the range
    static record Pair(int x, int y) {
    }

    public int[] maxSlidingWindowHeap(int[] nums, int k) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((p1, p2) -> (p2.x - p1.x));
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            pq.add(new Pair(nums[i], i));

            while (!pq.isEmpty() && i - pq.peek().y >= k) {
                pq.poll();
            }

            if (i >= k - 1) {
                res.add(pq.peek().x);

            }
        }

        return res.stream().mapToInt(i -> i).toArray();
    }
}
