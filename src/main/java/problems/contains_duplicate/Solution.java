package problems.contains_duplicate;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int n : nums) {
            if (s.contains(n)) {
                return true;
            }

            s.add(n);
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().containsDuplicate(new int[] { 1, 2 }));
    }
}
