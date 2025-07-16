package problems.longest_consecutive_sequence;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {

    // SOLUTION WITH UNION FIND
    // intuition: union numbers in the array with their -1 and +1
    class DisjointSet {
        Map<Integer, Integer> parents;
        Map<Integer, Integer> sizes;

        public DisjointSet(int[] nums) {
            this.parents = new HashMap<>();
            this.sizes = new HashMap<>();

            for (Integer n : nums) {
                this.parents.put(n, n);
                this.sizes.put(n, 1);
            }
        }

        public int find(int a) {
            if (parents.get(a) != a) {
                parents.put(a, find(parents.get(a)));
            }

            return parents.get(a);
        }

        public void union(int a, int b) {
            int ga = find(a), gb = find(b);
            int sa = sizes.get(ga), sb = sizes.get(gb);

            if (ga != gb) {
                if (sa >= sb) {
                    parents.put(gb, parents.get(ga));
                    sizes.put(ga, sa + sb);
                } else {
                    parents.put(ga, parents.get(gb));
                    sizes.put(gb, sb + sa);
                }
            }
        }

        public int maxSize() {
            return sizes.values().stream().mapToInt(i -> i).max().orElse(0);
        }
    }

    public int longestConsecutive(int[] nums) {
        var ds = new DisjointSet(nums);
        Set<Integer> s = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            s.add(nums[i]);

            if (s.contains(nums[i] - 1)) {
                ds.union(nums[i], nums[i] - 1);
            }

            if (s.contains(nums[i] + 1)) {
                ds.union(nums[i], nums[i] + 1);
            }
        }

        return ds.maxSize();
    }

    // HashSet solution
    public int longestConsecutive2(int[] nums) {
        Set<Integer> s = new HashSet<>();
        Arrays.stream(nums).forEach(n -> s.add(n));
        int result = 0;

        for (int n : s) {
            if (!s.contains(n - 1)) {
                // new sequence
                int l = 0;
                int it = n;
                while (s.contains(it++)) {
                    l++;
                }

                result = Math.max(result, l);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().longestConsecutive2(new int[] { 100, 4, 200, 1, 3, 2 }));
    }
}
