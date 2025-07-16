package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// ElogV
// DIJKSTRA DOES NOT WORK WITH NEGATIVE WEIGHTS!
public class Dijkstra {

    Map<Integer, Map<Integer, Integer>> adj;
    int nodes;

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // x, y, weight
    public Dijkstra(int n, int[][] edges) {
        this.nodes = n;
        this.adj = new HashMap<>();

        for (int[] e : edges) {
            adj.computeIfAbsent(e[0], k -> new HashMap<>()).put(e[1], e[2]);
        }
    }

    public void apply(int start) {
        Map<Integer, Integer> dist = new HashMap<>();
        dist.put(start, 0);

        for (int i = 1; i <= nodes; i++) {
            if (i != start) {
                dist.put(i, Integer.MAX_VALUE);
            }
        }

        PriorityQueue<Pair> q = new PriorityQueue<>((p1, p2) -> p1.y - p2.y);
        q.offer(new Pair(start, 0));

        while (!q.isEmpty()) {
            var cur = q.poll();
            var curNode = cur.x;
            var currentDistance = cur.y;

            for (Map.Entry<Integer, Integer> kv : adj.getOrDefault(curNode, Map.of()).entrySet()) {
                int neighborNode = kv.getKey();
                int distToNode = kv.getValue();

                int newDistance = currentDistance + distToNode;

                if (newDistance < dist.get(neighborNode)) {
                    dist.put(neighborNode, newDistance);
                    q.offer(new Pair(neighborNode, newDistance));
                }
            }
        }

        return;
    }
}
