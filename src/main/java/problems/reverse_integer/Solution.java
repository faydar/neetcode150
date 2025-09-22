package problems.reverse_integer;

public class Solution {

    // essentially O(1)
    // only trick is to need to carefully check we won't exceed integer limits when
    // adding the new digit
    public int reverse(int x) {
        int res = 0;
        int minv = Integer.MIN_VALUE, maxv = Integer.MAX_VALUE;

        while (x != 0) {
            int d = x % 10;
            x /= 10;

            if (res > maxv / 10 || (res == maxv / 10 && d > maxv % 10)) {
                return 0;
            }

            if (res < minv / 10 || (res == minv / 10 && d < minv % 10)) {
                return 0;
            }

            res = res * 10 + d;
        }

        return res;
    }

    public static void main(String[] args) {
        var s = new Solution();
        var r = s.reverse(15349);
        return;
    }
}
