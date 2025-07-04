package problems.course_schedule_ii;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Solution {

    public List<Integer> topologicalSort(int numCourses, Map<Integer, Set<Integer>> adj) {
        int[] inDegrees = new int[numCourses];

        for (int e1 : adj.keySet()) {
            for (int e2 : adj.get(e1)) {
                inDegrees[e2]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) {
                q.add(i);
            }
        }

        List<Integer> result = new ArrayList<>();

        while (!q.isEmpty()) {
            var polled = q.poll();
            result.add(polled);

            for (int ne : adj.getOrDefault(polled, Set.of())) {
                inDegrees[ne]--;

                if (inDegrees[ne] == 0) {
                    q.add(ne);
                }
            }
        }

        return result.size() == numCourses ? result : List.of();
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> adj = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            adj.computeIfAbsent(prerequisites[i][1], k -> new HashSet<>()).add(prerequisites[i][0]);
        }

        var result = topologicalSort(numCourses, adj);
        return result.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
