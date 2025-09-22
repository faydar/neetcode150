package problems.multiply_strings;

public class Solution {

    public String add(String a, String b) {
        StringBuilder res = new StringBuilder();
        var ar = new StringBuilder(a).reverse().toString();
        var br = new StringBuilder(b).reverse().toString();

        int carry = 0;
        for (int i = 0; i < Math.max(a.length(), b.length()); i++) {
            var ca = ar.length() > i ? ar.charAt(i) - '0' : 0;
            var cb = br.length() > i ? br.charAt(i) - '0' : 0;
            var s = ca + cb + carry;
            res.append(Integer.toString(s % 10));
            carry = s / 10;
        }

        if (carry > 0) {
            res.append(Integer.toString(carry));
        }

        return res.reverse().toString();
    }

    public String multiply(String num1, String num2) {
        String res = "0";

        for (int i = num2.length() - 1; i >= 0; i--) {
            int d2 = num2.charAt(i) - '0';
            int carry = 0;
            StringBuilder cres = new StringBuilder();

            for (int j = num1.length() - 1; j >= 0; j--) {
                int d1 = num1.charAt(j) - '0';
                int mul = d2 * d1 + carry;
                carry = mul / 10;
                cres.append(mul % 10);
            }

            if (carry > 0) {
                cres.append(carry);
            }
            cres.reverse();

            for (int z = 0; z < num2.length() - i - 1; z++) {
                cres.append(0);
            }

            res = add(res, cres.toString());
        }
        return res.charAt(0) == '0' ? "0" : res;
    }

    // cleaner/better
    public String multiplyBetter(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        
        int[] res = new int[num1.length() + num2.length()];
        num1 = new StringBuilder(num1).reverse().toString();
        num2 = new StringBuilder(num2).reverse().toString();

        for (int i = 0; i < num1.length(); i++) {
            for (int j = 0; j < num2.length(); j++) {
                int s = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                res[i + j] += s;
                res[i + j + 1] += res[i + j] / 10;
                res[i + j] %= 10;
            }
        }

        StringBuilder r = new StringBuilder();
        int it = res.length - 1;
        while (it >= 0 && res[it] == 0) {
            it--;
        }

        while (it >= 0) {
            r.append(res[it--]);
        }

        return r.toString();
    }

    public static void main(String[] args) {
        var s = new Solution();
        var r = s.multiplyBetter("3", "322");
        return;
    }
}
