package problems.largest_rectangle_in_histogram;

import java.util.Stack;

public class Solution {

    public record Pair(int x, int y) {
    }

    // Monotonic Stack o(N)
    // intuition:
    // for every index i, find the first index j to the left that height[j] >
    // height[i] (using monotonic stack)
    // and the same to the right
    // then we have horizontal boundaries. find maximum of horizontalBoundary *
    // height[i]
    public int largestRectangleArea(int[] heights) {
        Stack<Pair> st = new Stack<>();
        int[] lefts = new int[heights.length];
        int[] rights = new int[heights.length];

        for (int i = 0; i < heights.length; i++) {
            lefts[i] = i;

            while (!st.isEmpty() && st.peek().x >= heights[i]) {
                var popped = st.pop();
                lefts[i] = lefts[popped.y];
            }

            st.add(new Pair(heights[i], i));
        }

        st.clear();
        for (int i = heights.length - 1; i >= 0; i--) {
            rights[i] = i;

            while (!st.isEmpty() && st.peek().x >= heights[i]) {
                var popped = st.pop();
                rights[i] = rights[popped.y];
            }

            st.add(new Pair(heights[i], i));
        }

        int res = 0;

        for (int i = 0; i < lefts.length; i++) {
            res = Math.max(res, heights[i] * (rights[i] - lefts[i] + 1));
        }

        return res;
    }

    public static void main(String[] args) {
        var s = new Solution();
        s.largestRectangleArea(new int[] { 7, 1, 7, 2, 2, 4, 2, 1 });
        return;
    }
}
