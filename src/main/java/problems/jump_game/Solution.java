package problems.jump_game;

public class Solution {
    public boolean canJump(int[] nums) {
        boolean[] h = new boolean[nums.length];
        int curMax = 0;

        h[0] = true;
        for (int i = 0; i < nums.length; i++) {
            if (i <= curMax) {
                h[i] = true;
            }

            if (h[i]) {
                curMax = Math.max(curMax, i + nums[i]);
            }
        }

        return h[nums.length - 1];
    }

    public boolean canJump2(int[] nums) {
        int target = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= target) {
                target = i;
            }
        }

        return target == 0;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().canJump2(new int[] { 3, 2, 1, 0, 4 }));
    }
}
