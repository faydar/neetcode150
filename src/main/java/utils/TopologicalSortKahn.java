package utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;

public class TopologicalSortKahn {

    public static Optional<List<Integer>> topologicalSort(int nodeCount, Map<Integer, Set<Integer>> adj) {
        int[] inDegrees = new int[nodeCount];

        for (int e1 : adj.keySet()) {
            for (int e2 : adj.get(e1)) {
                inDegrees[e2]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < nodeCount; i++) {
            if (inDegrees[i] == 0) {
                q.add(i);
            }
        }

        List<Integer> result = new ArrayList<>();

        while (!q.isEmpty()) {
            var polled = q.poll();
            result.add(polled);

            var neighbors = adj.getOrDefault(polled, Set.of());

            for (int n : neighbors) {
                inDegrees[n]--;

                if (inDegrees[n] == 0) {
                    q.offer(n);
                }
            }
        }

        if (result.size() == nodeCount) {
            return Optional.of(result);
        }

        return Optional.empty();
    }
}
