package problems.word_ladder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Solution {

    private void addToMap(Map<String, List<String>> adj, String word) {
        for (int i = 0; i < word.length(); i++) {
            String ns = word.substring(0, i) + "*" + word.substring(i + 1, word.length());
            adj.computeIfAbsent(ns, k -> new ArrayList<>()).add(word);
            adj.computeIfAbsent(word, k -> new ArrayList<>()).add(ns);
        }
    }

    static class State {
        String val;
        int steps;

        public State(String val, int steps) {
            this.val = val;
            this.steps = steps;
        }
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> adj = new HashMap<>();
        addToMap(adj, beginWord);
        for (String word : wordList) {
            addToMap(adj, word);
        }

        Queue<State> q = new LinkedList<>();
        Set<String> v = new HashSet<>();

        q.offer(new State(beginWord, 0));
        v.add(beginWord);

        while (!q.isEmpty()) {
            var cs = q.poll();

            if (cs.val.equals(endWord)) {
                return cs.steps / 2 + 1;
            }

            for (String neighbor : adj.getOrDefault(cs.val, new ArrayList<>())) {
                if (!v.contains(neighbor)) {
                    v.add(neighbor);
                    q.offer(new State(neighbor, cs.steps + 1));
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        var s = new Solution();
        var val = s.ladderLength("cat", "sag", List.of("bat", "bag", "sag", "dag", "dot"));
        var val2 = s.ladderLength("cat", "sag", List.of("bat", "bag", "sat", "dag", "dot"));
        var val3 = s.ladderLength("hit", "cog", List.of("hot", "dot", "dog", "lot", "log", "cog"));

        return;
    }
}
