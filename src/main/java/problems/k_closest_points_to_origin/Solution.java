package problems.k_closest_points_to_origin;

import java.util.PriorityQueue;

public class Solution {

    public class Pair<F, S> {
        F first;
        S second;

        public Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }

        public F getFirst() {
            return first;
        }

        public S getSecond() {
            return second;
        }
    }

    private double euclideanToOrigin(Pair<Integer, Integer> p) {
        int x = p.getFirst(), y = p.getSecond();
        return Math.sqrt(x * x + y * y);
    }

    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>((p1, p2) -> {
            return euclideanToOrigin(p1) - euclideanToOrigin(p2) < 0 ? 1 : -1;
        });

        for (int[] p : points) {
            pq.add(new Pair<Integer, Integer>(p[0], p[1]));

            if (pq.size() == k + 1) {
                pq.poll();
            }
        }

        int[][] res = new int[k][2];

        for (int i = 0; i < k; i++) {
            var p = pq.poll();
            res[i][0] = p.getFirst();
            res[i][1] = p.getSecond();
        }

        return res;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.kClosest(new int[][] {
                { 1, 3 }, { -2, 2 }
        }, 1);
    }

}
