package problems.partition_labels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public List<Integer> partitionLabels(String s) {
        Map<Character, Integer> l = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            l.put(s.charAt(i), i);
        }

        int it = 0;
        int curLast = l.get(s.charAt(0));
        int curLen = 0;
        List<Integer> result = new ArrayList<>();

        while (it < s.length()) {
            curLast = Math.max(curLast, l.get(s.charAt(it)));

            curLen++;

            if (it == curLast) {
                result.add(curLen);
                curLen = 0;
            }

            it++;
        }

        return result;
    }

    public static void main(String[] args) {
        var res = new Solution().partitionLabels("ababcbacadefegdehijhklij");
        return;
    }
}
