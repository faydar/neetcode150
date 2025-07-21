package problems.sum_of_two_integers;

public class Solution {
    public int getSum(int a, int b) {
        int res = 0;
        int carry = 0;

        for (int i = 0; i <= 31; i++) {
            int aa = (a >> i) & 1;
            int bb = (b >> i) & 1;

            res ^= (aa ^ bb ^ carry) << i;
            carry = ((aa & bb) != 0 || (aa & carry) != 0 || (bb & carry) != 0) ? 1 : 0;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().getSum(-1000, 3));
    }
}
