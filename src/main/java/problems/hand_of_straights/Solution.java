package problems.hand_of_straights;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    // greedy: start from smallest available, capture until start+groupSize
    // nlogn, due to sort
    public boolean isNStraightHand(int[] hand, int groupSize) {
        Map<Integer, Integer> counts = new HashMap<>();
        Arrays.sort(hand);

        for (int n : hand) {
            counts.put(n, counts.getOrDefault(n, 0) + 1);
        }

        for (int i = 0; i < hand.length; i++) {
            if (counts.get(hand[i]) > 0) {
                for (int j = hand[i]; j < hand[i] + groupSize; j++) {
                    counts.put(j, counts.getOrDefault(j, 0) - 1);

                    if (counts.get(j) < 0) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    // O(N), avoids sorting
    // finds ranges of counts > 1, processes groupSize in these ranges
    // unintuitive code though
    public boolean isNStraightHandWithoutSort(int[] hand, int groupSize) {
        Map<Integer, Integer> counts = new HashMap<>();

        for (int n : hand) {
            counts.put(n, counts.getOrDefault(n, 0) + 1);
        }

        for (int n : hand) {
            int start = n;
            while (counts.getOrDefault(start - 1, 0) > 0) {
                start--;
            }

            while (start <= n) {
                while (counts.getOrDefault(start, 0) > 0) {
                    for (int it = start; it < start + groupSize; it++) {
                        if (counts.getOrDefault(it, 0) <= 0) {
                            return false;
                        }

                        counts.put(it, counts.getOrDefault(it, 0) - 1);
                    }
                }

                start++;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        var in = new int[] { 2, 1 };
        System.out.println("size: " + in.length);
        var res = new Solution().isNStraightHandWithoutSort(in, 2);

        return;
    }
}
