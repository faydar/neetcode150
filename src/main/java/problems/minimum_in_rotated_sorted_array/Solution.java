package problems.minimum_in_rotated_sorted_array;

public class Solution {

    public static int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;

        while (l < r) {
            int mid = l + (r - l) / 2;

            if (nums[r] < nums[mid]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return nums[l];
    }

    public static void main(String[] args) {
        var inputs = new int[][] {
                { 1, 2, 3, 4, 5 },
                { 2, 3, 4, 5, 1 },
                { 3, 4, 5, 1, 2 },
                { 4, 5, 1, 2, 3 },
                { 5, 1, 2, 3, 4 }
        };

        for (int[] inp : inputs) {
            findMin(inp);
        }
    }
}
