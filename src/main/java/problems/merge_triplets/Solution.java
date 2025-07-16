package problems.merge_triplets;

public class Solution {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        boolean t0 = false, t1 = false, t2 = false;

        for (int[] t : triplets) {
            t0 = t0 || (t[0] == target[0] && t[1] <= target[1] && t[2] <= target[2]);
            t1 = t1 || (t[0] <= target[0] && t[1] == target[1] && t[2] <= target[2]);
            t2 = t2 || (t[0] <= target[0] && t[1] <= target[1] && t[2] == target[2]);
        }

        return t0 && t1 && t2;
    }

    public static void main(String[] args) {
        var in = new int[][] {
                { 2, 5, 3 },
                { 2, 3, 4 },
                { 1, 2, 5 },
                { 5, 2, 3 }
        };

        var res = new Solution().mergeTriplets(in, new int[] { 5, 5, 5 });
        return;
    }
}
