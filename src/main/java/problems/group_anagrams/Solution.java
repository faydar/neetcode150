package problems.group_anagrams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> m = new HashMap<>();

        for (String s : strs) {
            int[] count = new int[26];

            for (Character c : s.toCharArray()) {
                count[c - 'a']++;
            }

            // Arrays.toString basically acts as a hash function here, although it is simply
            // a string conversion of an int array
            String hash = Arrays.toString(count);

            m.computeIfAbsent(hash, k -> new ArrayList<String>()).add(s);
        }

        return new ArrayList<>(m.values());
    }

    // N*N*logN
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<Integer>> m = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            var arr = strs[i].toCharArray();
            Arrays.sort(arr);
            m.computeIfAbsent(new String(arr), key -> new ArrayList<>()).add(i);
        }

        List<List<String>> result = new ArrayList<>();

        for (String k : m.keySet()) {
            result.add(m.get(k).stream().map(i -> strs[i]).toList());
        }

        return result;
    }

    public static void main(String[] args) {
        var res = new Solution().groupAnagrams2(new String[] { "eat", "tea", "tan", "ate", "nat", "bat" });
        return;
    }
}
