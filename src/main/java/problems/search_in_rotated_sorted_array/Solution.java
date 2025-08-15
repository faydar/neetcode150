package problems.search_in_rotated_sorted_array;

public class Solution {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (target == nums[mid]) {
                return mid;
            }

            if (nums[l] <= nums[mid]) {
                // P1
                if (target > nums[mid] || target < nums[l]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            } else {
                // P2
                if (target < nums[mid] || target > nums[r]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        in1();
        in2();
        in3();
    }

    public static void in1() {
        var s = new Solution();
        var in = new int[] { 4, 5, 6, 7, 0, 1, 2 };
        for (int c : in) {
            System.out.println(s.search(in, c));
        }

        System.out.println();
    }

    public static void in2() {
        var s = new Solution();
        var in = new int[] { 4, 5, 6, 7, 8, 0, 1, 2 };
        for (int c : in) {
            System.out.println(s.search(in, c));
        }

        System.out.println();

    }

    public static void in3() {
        var s = new Solution();
        var in = new int[] { 4, 5, 6, 7, 8, 0, 1, 2 };
        for (int c : in) {
            System.out.println(s.search(in, c));
        }

        System.out.println();

    }
}
