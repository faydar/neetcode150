package problems.single_number;

public class Solution {

    public static int singleNumber(int[] nums) {
        int mask = 0;

        for (int n : nums) {
            mask = mask ^ n;
        }

        return mask;
    }

    public static void main(String[] args) {
        System.out.println(singleNumber(new int[] { 2, 2, 1 }));
    }
}
