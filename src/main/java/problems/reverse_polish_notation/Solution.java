package problems.reverse_polish_notation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Solution {

    private static final Set<String> OPS = Set.of("+", "*", "-", "/");

    private int evalRpnRecursion(List<String> tokens) {
        var lastElem = tokens.removeLast();

        if (!OPS.contains(lastElem)) {
            return Integer.parseInt(lastElem);
        }

        var rightOperand = evalRpnRecursion(tokens);
        var leftOperand = evalRpnRecursion(tokens);

        return switch (lastElem) {
            case "+":
                yield (leftOperand + rightOperand);
            case "-":
                yield (leftOperand - rightOperand);
            case "*":
                yield (leftOperand * rightOperand);
            case "/":
                yield (leftOperand / rightOperand);
            default:
                yield 0;
        };
    }

    public int evalRpnRecursion(String[] tokens) {
        return evalRpnRecursion(new ArrayList<>(Arrays.asList(tokens)));
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> st = new Stack<>();

        for (String token : tokens) {
            if (OPS.contains(token)) {
                var op2 = st.pop();
                var op1 = st.pop();

                switch (token) {
                    case "+":
                        st.push(op1 + op2);
                        break;
                    case "-":
                        st.push(op1 - op2);
                        break;
                    case "*":
                        st.push(op1 * op2);
                        break;
                    case "/":
                        st.push(op1 / op2);
                        break;
                    default:
                        break;
                }
            } else {
                st.push(Integer.parseInt(token));
            }
        }

        return st.pop();
    }

    public static void main(String[] args) {
        var s = new Solution();
        var res = s.evalRpnRecursion(new String[] { "2", "1", "+", "3", "*" });
        return;
    }
}
