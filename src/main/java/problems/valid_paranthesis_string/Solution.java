package problems.valid_paranthesis_string;

import java.util.Stack;

public class Solution {

    // greedy
    // track the range of possible remaining leftParantheses
    public boolean checkValidStringGreedy(String s) {
        int leftMinimum = 0, leftMaximum = 0;

        for (Character c : s.toCharArray()) {
            if (c == '(') {
                leftMinimum++;
                leftMaximum++;
            } else if (c == ')') {
                leftMinimum--;
                leftMaximum--;
            } else {
                leftMinimum--;
                leftMaximum++;
            }

            if (leftMinimum < 0) {
                leftMinimum = 0;
            }

            if (leftMaximum < 0) {
                return false;
            }
        }

        return leftMinimum == 0;
    }

    // stack. match rights with lefts immediately if present
    // match with stars as fallback
    // in the end, balance stars with remaining lefts
    public boolean checkValidString(String s) {
        Stack<Integer> stars = new Stack<>();
        Stack<Integer> lefts = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                lefts.add(i);
            } else if (s.charAt(i) == '*') {
                stars.add(i);
            } else {
                if (!lefts.isEmpty()) {
                    lefts.pop();
                } else if (!stars.isEmpty()) {
                    stars.pop();
                } else {
                    return false;
                }
            }
        }

        while (!lefts.isEmpty()) {
            if (stars.isEmpty()) {
                return false;
            }

            var curLeft = lefts.pop();
            var curStar = stars.pop();

            if (curStar < curLeft) {
                return false;
            }
        }

        return true;
    }

    // Dynamic programming, based on: at index i, how many open parantheses we
    // have remaining
    private boolean rec(String s, int index, int openCount, Boolean[][] d) {
        if (index == s.length()) {
            return openCount == 0;
        }

        if (openCount < 0) {
            return false;
        }

        if (d[index][openCount] != null) {
            return d[index][openCount];
        }

        boolean result;
        if (s.charAt(index) == '(') {
            result = rec(s, index + 1, openCount + 1, d);
        } else if (s.charAt(index) == ')') {
            result = rec(s, index + 1, openCount - 1, d);
        } else {
            result = rec(s, index + 1, openCount + 1, d) || rec(s, index + 1, openCount - 1, d)
                    || rec(s, index + 1, openCount, d);
        }

        d[index][openCount] = result;
        return result;
    }

    // dp
    public boolean checkValidStringDp(String s) {
        Boolean[][] d = new Boolean[s.length()][s.length()];
        return rec(s, 0, 0, d);
    }

    public static void main(String[] args) {
        var s = new Solution();
        System.out.println(s.checkValidStringGreedy("((**)"));
        System.out.println(s.checkValidStringGreedy("(((*)"));
        System.out.println(s.checkValidStringGreedy("()"));
        System.out.println(s.checkValidStringGreedy("(*)"));
        System.out.println(s.checkValidStringGreedy("(*)))"));
        System.out.println(s.checkValidStringGreedy("(*))"));
        System.out.println(s.checkValidStringGreedy(
                "(((((*(()((((*((**(((()()*)()()()*((((**)())*)*)))))))(())(()))())((*()()(((()((()*(())*(()**)()(())"));
    }

    public void easy() {
        var s = new Solution();
        System.out.println(s.checkValidString("((**)"));
        System.out.println(s.checkValidString("(((*)"));
        System.out.println(s.checkValidString("()"));
        System.out.println(s.checkValidString("(*)"));
        System.out.println(s.checkValidString("(*)))"));
        System.out.println(s.checkValidString("(*))"));
    }

}
