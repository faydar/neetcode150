package problems.number_of_connected_components;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// can also solve with Disjoint Set Union -> start counter from n, union all vertices for edges, if they are not already in the same group decrease counter
public class Solution {

    public void dfs(int n, Map<Integer, Set<Integer>> adj, Set<Integer> visited) {
        visited.add(n);

        for (Integer neighbor : adj.getOrDefault(n, Set.of())) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, adj, visited);
            }
        }
    }

    public int countComponents(int n, int[][] edges) {
        Map<Integer, Set<Integer>> adj = new HashMap<>();

        for (int[] edge : edges) {
            adj.computeIfAbsent(edge[0], k -> new HashSet<>()).add(edge[1]);
            adj.computeIfAbsent(edge[1], k -> new HashSet<>()).add(edge[0]);
        }

        Set<Integer> visited = new HashSet<>();
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                count++;
                dfs(i, adj, visited);
            }
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().countComponents(12, new int[][] {
                { 0, 1 },
                { 1, 2 },
                { 2, 3 },
                { 3, 0 },
                { 4, 5 },
                { 6, 7 },
                { 7, 4 },
                { 8, 9 },
                { 10, 11 }
        }));
    }
}
