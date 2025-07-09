package problems.letter_combinations_of_phone_number;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Solution {
    private static final Map<Character, String> m = Map.of(
            '2', "abc",
            '3', "def",
            '4', "ghi",
            '5', "jkl",
            '6', "mno",
            '7', "pqrs",
            '8', "tuv",
            '9', "wxyz");

    private List<String> result = new ArrayList<>();

    private void rec(String digits, int index, String cur) {
        if (index == digits.length()) {
            if (cur.length() > 0) {
                result.add(cur);
            }
            return;
        }

        var letters = m.get(digits.charAt(index)).toCharArray();
        for (Character c : letters) {
            rec(digits, index + 1, cur + c);
        }
    }

    public List<String> letterCombinations(String digits) {
        rec(digits, 0, "");
        return result;
    }

    public List<String> letterCombinationsIteration(String digits) {
        List<String> q = new ArrayList<>();

        if (digits.length() == 0) {
            return List.of();
        }

        q.add("");

        for (Character cur : digits.toCharArray()) {
            List<String> step = new ArrayList<>();

            for (String s : q) {
                for (Character c : m.get(cur).toCharArray()) {
                    step.add(s + c);
                }
            }

            q = step;
        }

        return q;
    }

    public static void main(String[] args) {
        var res = new Solution().letterCombinationsIteration("2");
        return;
    }
}
