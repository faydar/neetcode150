package problems.detect_squares;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class DetectSquares {

    Map<Integer, Map<Integer, Integer>> rows;

    public DetectSquares() {
        this.rows = new HashMap<>();
    }

    public void add(int[] point) {
        Map<Integer, Integer> r = rows.computeIfAbsent(point[0], k -> new HashMap<>());
        r.put(point[1], r.getOrDefault(point[1], 0) + 1);
    }

    public int count(int[] point) {
        Set<Integer> sameRowPoints = rows.getOrDefault(point[0], Map.of()).keySet();
        int res = 0;

        for (Integer column : sameRowPoints) {
            int n = column - point[1];

            if (n == 0) {
                continue;
            }

            // (x0, y0): current point
            // sq1: (x0, y0), (x0, column), (x0-n, y0), (x0-n, column)
            // sq2: (x0, y0), (x0, column), (x0+n, y0), (x0+n, column)

            int c1 = rows.getOrDefault(point[0], Map.of()).getOrDefault(column, 0);

            res += c1 * rows.getOrDefault(point[0] - n, Map.of()).getOrDefault(point[1], 0)
                    * rows.getOrDefault(point[0] - n, Map.of()).getOrDefault(column, 0);
            res += c1 * rows.getOrDefault(point[0] + n, Map.of()).getOrDefault(point[1], 0)
                    * rows.getOrDefault(point[0] + n, Map.of()).getOrDefault(column, 0);
        }

        return res;
    }

    public static void main(String[] args) {
        DetectSquares detectSquares = new DetectSquares();
        detectSquares.add(new int[] { 3, 10 });
        detectSquares.add(new int[] { 11, 2 });
        detectSquares.add(new int[] { 3, 2 });
        detectSquares.count(new int[] { 11, 10 }); // return 1. You can choose:
        // - The first, second, and third points
        detectSquares.count(new int[] { 14, 8 }); // return 0. The query point cannot form a square with any points in
                                                  // the data structure.
        detectSquares.add(new int[] { 11, 2 }); // Adding duplicate points is allowed.
        detectSquares.count(new int[] { 11, 10 }); // return 2. You can choose:
        // - The first, second, and third points
        // - The first, third, and fourth points
    }
}
