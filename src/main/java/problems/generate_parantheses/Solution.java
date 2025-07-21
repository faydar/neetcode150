package problems.generate_parantheses;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    List<String> result = new ArrayList<>();

    // dp solution
    public List<String> generateParanthesesDp(int n) {
        List<List<String>> d = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            d.add(new ArrayList<>());
        }

        d.get(0).add("");

        for (int i = 1; i <= n; i++) {
            System.out.println("n: " + i);
            for (int j = 0; j < i; j++) {
                for (String left : d.get(j)) {
                    for (String right : d.get(i - j - 1)) {
                        d.get(i).add("(" + left + ")" + right);
                    }
                }
            }
        }

        return d.get(n);
    }

    // stack/backtracking solution
    public void rec(int openRem, int closeRem, StringBuilder cur) {
        if (openRem == 0 && closeRem == 0) {
            result.add(cur.toString());
            return;
        }

        if (closeRem > 0 && openRem < closeRem) {
            cur.append(')');
            rec(openRem, closeRem - 1, cur);
            cur.deleteCharAt(cur.length() - 1);
        }

        if (openRem > 0) {
            cur.append('(');
            rec(openRem - 1, closeRem, cur);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    public List<String> generateParenthesis(int n) {
        rec(n, n, new StringBuilder());
        return result;
    }

    public static void main(String[] args) {
        var res = new Solution().generateParanthesesDp(4);
        return;
    }
}
