package problems.last_stone_weight;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {

    public int lastStoneWeight(int[] stones) {
        Queue<Integer> q = new PriorityQueue<>(Comparator.reverseOrder());
        for (int s : stones) {
            q.add(s);
        }

        while (q.size() > 1) {
            var s1 = q.poll();
            var s2 = q.poll();

            if (s1 - s2 != 0) {
                q.add(s1 - s2);
            }
        }

        return q.size() == 0 ? 0 : q.poll();
    }

    public static void main(String[] args) {
        var s = new Solution();
        System.out.println(s.lastStoneWeight(new int[] { 2, 7, 4, 1, 8, 1 }));
    }
}
