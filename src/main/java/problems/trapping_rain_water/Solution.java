package problems.trapping_rain_water;

import java.util.Arrays;
import java.util.Stack;

public class Solution {

    // intuition: an index can hold water as much as ->
    // LM: max height to the left (not just the first greater!),
    // RM: max height to the right
    // min(LM, RM) - curHeight

    // solve with two pointers

    // solve with monotonic stack
    public int trap(int[] height) {

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
}
