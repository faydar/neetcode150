package problems.product_of_array_except_self;

public class Solution {

    public static int[] productExceptSelf(int[] nums) {
        int[] leftToRight = new int[nums.length];
        int[] rightToLeft = new int[nums.length];

        for (int i = 0, m = 1; i < nums.length; i++) {
            m *= nums[i];
            leftToRight[i] = m;
        }

        for (int i = nums.length - 1, m = 1; i >= 0; i--) {
            m *= nums[i];
            rightToLeft[i] = m;
        }

        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            var l = i > 0 ? leftToRight[i - 1] : 1;
            var r = i < nums.length - 1 ? rightToLeft[i + 1] : 1;
            res[i] = l * r;
        }

        return res;
    }

    public static int[] productExceptSelfO1Space(int[] nums) {
        int[] result = new int[nums.length];

        for (int i = 0, m = 1; i < nums.length; i++) {
            m *= nums[i];
            result[i] = m;
        }

        for (int i = nums.length - 1, m = 1; i >= 0; i--) {
            result[i] = (i > 0 ? result[i - 1] : 1) * m;
            m *= nums[i];
        }

        return result;
    }

    public static void main(String[] args) {
        productExceptSelfO1Space(new int[] { 1, 2, 3, 4 });
    }
}
