package problems.koko_eating_bananas;

import java.util.Arrays;

public class Solution {
    public static int minEatingSpeed(int[] piles, int h) {
        int l = 1, r = Arrays.stream(piles)
                .max()
                .getAsInt();
        int res = r;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            long curRes = 0;

            for (int i = 0; i < piles.length; i++) {
                curRes += (long) Math.ceil(piles[i] * 1.0 / mid);
            }

            if (curRes > h) {
                l = mid + 1;
            } else {
                res = Math.min(res, mid);
                r = mid - 1;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        // minEatingSpeed(new int[] { 30, 11, 23, 4, 20 }, 5);
        // minEatingSpeed(new int[] { 3, 6, 7, 11 }, 8);
        minEatingSpeed(new int[] { 805306368, 805306368, 805306368 }, 1000000000);

    }
}
