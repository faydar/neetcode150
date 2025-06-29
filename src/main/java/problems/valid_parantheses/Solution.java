package problems.valid_parantheses;

import java.util.Stack;

public class Solution {
    public static boolean isValid(String s) {
        Stack<Character> st = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '{' || c == '(' || c == '[') {
                st.push(c);
            } else if (st.isEmpty()) {
                return false;
            } else {
                char popped = st.pop();

                if ((popped == '{' && c != '}') ||
                        (popped == '(' && c != ')') ||
                        (popped == '[' && c != ']')) {
                    return false;
                }
            }
        }

        return st.isEmpty();
    }

    public static void main(String[] args) {
        isValid("[]");
        isValid("([{}])");
        isValid("[(])");
    }
}
