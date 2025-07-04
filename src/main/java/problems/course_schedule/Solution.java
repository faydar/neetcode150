package problems.course_schedule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;

public class Solution {

    public Optional<List<Integer>> topologicalSort(int nodeCount, Map<Integer, Set<Integer>> adj) {
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

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> adj = new HashMap<>();

        for (int i = 0; i < prerequisites.length; i++) {
            adj.computeIfAbsent(prerequisites[i][1], k -> new HashSet<>()).add(prerequisites[i][0]);
        }

        return topologicalSort(numCourses, adj).isPresent();
    }

    public boolean dfs(Map<Integer, Set<Integer>> adj, int cur, boolean[] visitedOnThisCall, boolean[] visitedGeneral) {
        if (visitedOnThisCall[cur]) {
            // has cycle
            return true;
        }

        if (visitedGeneral[cur]) {
            // visited before, no cycle found
            return false;
        }

        visitedOnThisCall[cur] = true;
        visitedGeneral[cur] = true;

        for (Integer ne : adj.getOrDefault(cur, Set.of())) {
            if (dfs(adj, ne, visitedOnThisCall, visitedGeneral)) {
                return true;
            }
        }

        visitedOnThisCall[cur] = false;
        return false;
    }

    public boolean canFinishCycleDetection(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> adj = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            adj.computeIfAbsent(prerequisites[i][1], k -> new HashSet<>()).add(prerequisites[i][0]);
        }

        boolean[] visitedGeneral = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (!visitedGeneral[i] && dfs(adj, i, new boolean[numCourses], visitedGeneral)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(
                new Solution().canFinishCycleDetection(4, new int[][] { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } }));
        return;
    }
}
