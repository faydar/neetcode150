package problems.sliding_window_maximum;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SlidingWindowDequeue {

    // Dequeue O(N)
    // intuition: clear smaller elements from right when we receive a new number
    // remove out of bounds elements from left
    // this will leave current biggest in window element in the beginning(left) of
    // the queue
    public int[] maxSlidingWindow(int[] nums, int k) {
        LinkedList<Integer> dq = new LinkedList<Integer>();
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
                dq.pollLast();
            }

            while (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst();
            }

            dq.addLast(i);

            if (i >= k - 1) {
                res.add(nums[dq.getFirst()]);
            }
        }

        return res.stream().mapToInt(x -> x).toArray();
    }

    public static void main(String[] args) {
        var s = new SlidingWindowDequeue();
        // s.maxSlidingWindow(new int[] { 1, -1 }, 1);
        s.maxSlidingWindow(new int[] { 1, 3, 1, 2, 0, 5 }, 3);
        return;
    }
}
