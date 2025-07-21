package problems.trapping_rain_water;

import java.util.Stack;

public class Solution {

    public int trap(int[] height) {
        int[] nextBiggerIndex = new int[height.length];

        // use monotonic stack to find first index with bigger element
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            // System.out.println("height[i]: " + height[i] + "\n");
            while (!st.isEmpty() && height[i] > height[st.peek()]) {
                System.out.println("Popping: " + st.peek());
                var p = st.pop();
                nextBiggerIndex[p] = i;
            }
            System.out.println();

            st.add(i);
        }

        return -1;
    }

    public static void main(String[] args) {
        // System.out.println(new Solution().trap(new int[] { 6, 5, 4, 1, 1, 1, 2, 3, 5,
        // 5 }));
        // System.out.println(new Solution().trap(new int[] { 5, 4, 1, 2 }));
        // System.out.println(new Solution().trap(new int[] { 6, 8, 5, 0, 0, 6, 5 }));

        System.out.println(new Solution().trap(new int[] { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 }));

        // System.out.println(new Solution().trap(new int[] { 4, 2, 0, 3, 2, 4, 3, 4
        // }));

    }

    public void passing() {

    }
}
