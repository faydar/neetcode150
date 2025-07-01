package problems.pow_xn;

public class Solution {

    public double myPow(double x, int n) {
        if (n == 1) {
            return x;
        }

        if (n == 0) {
            return 1;
        }

        int s1 = n / 2;
        int s2 = n - s1;
        double nx = x;

        if (n < 0) {
            nx = 1 / x;
            s1 *= -1;
            s2 *= -1;
        }

        double r1 = myPow(nx, s1);
        double r2 = s1 == s2 ? r1 : myPow(nx, s2);
        return r1 * r2;
    }
}
