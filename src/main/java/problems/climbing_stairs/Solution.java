package problems.climbing_stairs;

public class Solution {

    public int climbStairs(int n) {
        int[] d = new int[46];

        d[0] = 0;
        d[1] = 1;
        d[2] = 2;

        for (int i = 3; i <= n; i++) {
            d[i] = d[i - 2] + d[i - 1];
        }

        return d[n];
    }

    public static void main(String[] args) {
        var s = new Solution();

        s.climbStairs(4);
    }
}
