package problems.maximum_product_subarray;

public class Solution {

    public int maxProduct(int[] nums) {
        int max = Math.max(1, nums[0]), min = Math.min(nums[0], 1);
        int res = nums[0];
        int nmax, nmin;

        for (int i = 1; i < nums.length; i++) {

            if (nums[i] == 0) {
                nmax = nmin = 0;
            }

            nmax = Math.max(nums[i], Math.max(nums[i] * max, nums[i] * min));
            nmin = Math.min(nums[i], Math.min(nums[i] * max, nums[i] * min));
            res = Math.max(res, nmax);

            max = nmax;
            min = nmin;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maxProduct(new int[] { -2, 0, -1 }));
    }
}
