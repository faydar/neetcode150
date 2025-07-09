package problems.daily_temperatures;

import java.util.Stack;

public class Solution {
    static class Pair<F, S> {
        F first;
        S second;

        public static <F, S> Pair<F, S> of(F first, S second) {
            return new Pair<>(first, second);
        }

        public Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }

        public F getFirst() {
            return first;
        }

        public S getSecond() {
            return second;
        }
    }

    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Pair<Integer, Integer>> st = new Stack<>();
        int[] res = new int[temperatures.length];

        st.add(new Pair<Integer, Integer>(temperatures[0], 0));

        for (int i = 1; i < temperatures.length; i++) {
            while (st.size() > 0 && st.peek().first < temperatures[i]) {
                var popped = st.pop();
                res[popped.second] = i - popped.second;
            }

            st.add(new Pair<Integer, Integer>(temperatures[i], i));
        }

        return res;
    }

    // interesting solution
    public int[] dailyTemperatures2(int[] temperatures) {
        int[] res = new int[temperatures.length];

        for (int i = temperatures.length - 2; i >= 0; i--) {
            int j = i + 1;

            // start from next index, jump until we found the next smallest temp bigger than
            // temp[i]
            while (j < temperatures.length && temperatures[j] <= temperatures[i]) {
                if (res[j] == 0) {
                    j = temperatures.length;
                    break;
                }
                j += res[j];
            }

            res[i] = j < temperatures.length ? j - i : 0;
        }

        return res;
    }

    public static void main(String[] args) {
        var res = new Solution().dailyTemperatures2(new int[] { 73, 74, 75, 71, 69, 72, 76, 73 });

    }
}
