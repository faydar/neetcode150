package problems.container_with_most_water;

class Solution {
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1;
        int res = 0;
        while (i < j) {
            res = Math.max(res, Math.min(height[i], height[j]) * (j - i));
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maxArea(new int[] { 1, 3, 2, 5, 25, 24, 5 }));
    }
}