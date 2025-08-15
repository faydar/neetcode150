package problems.alien_dictionary;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Solution {

    // Topological sort
    public String foreignDictionary(String[] words) {
        Set<Character> charset = new HashSet<>();

        for (String word : words) {
            for (Character c : word.toCharArray()) {
                charset.add(c);
            }
        }

        Map<Character, Set<Character>> adj = new HashMap<>();
        Map<Character, Integer> in = new HashMap<>();

        // build adjacency and in degress for topological sort
        for (int i = 0; i < words.length - 1; i++) {
            int j = 0;
            while (j < words[i].length()) {
                if (j >= words[i + 1].length()) {
                    return "";
                }

                char n1 = words[i].charAt(j);
                char n2 = words[i + 1].charAt(j);

                if (n1 != n2) {
                    if (!adj.getOrDefault(n1, Set.of()).contains(n2)) {
                        adj.computeIfAbsent(n1, k -> new HashSet<>()).add(n2);
                        in.put(n2, in.getOrDefault(n2, 0) + 1);
                    }

                    break;
                }

                j++;
            }
        }

        // topological sort
        Queue<Character> q = new LinkedList<>();
        for (Character c : charset) {
            if (in.getOrDefault(c, 0).equals(0)) {
                q.add(c);
            }
        }

        StringBuilder res = new StringBuilder();
        while (!q.isEmpty()) {
            var p = q.poll();
            res.append(p);

            for (Character neighbor : adj.getOrDefault(p, new HashSet<>())) {
                var nv = in.getOrDefault(neighbor, 0) - 1;
                in.put(neighbor, nv);

                if (nv == 0) {
                    q.offer(neighbor);
                }
            }
        }

        var rr = res.toString();

        // didn't manage to find all letters in alphabet
        if (rr.length() != charset.size()) {
            return "";
        }

        return rr;
    }

    public static void main(String[] args) {
        var words = new String[] {
                "hrn", "hrf", "er", "enn", "rfnn"
        };
        var words2 = new String[] {
                "wrtkj", "wrt"
        };
        var s = new Solution();
        s.foreignDictionary(words2);
    }
}
