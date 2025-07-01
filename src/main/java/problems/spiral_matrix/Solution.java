package problems.spiral_matrix;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<Integer> spiralOrder(int[][] matrix) {
        int l = 0, u = 0, r = matrix[0].length - 1, d = matrix.length - 1;
        List<Integer> res = new ArrayList<>();
        int c = 0;
        int t = matrix[0].length * matrix.length;

        while (l <= r) {
            for (int i = l; i <= r && c < t; i++) {
                res.add(matrix[u][i]);
                c++;
            }

            for (int i = u + 1; i <= d && c < t; i++) {
                res.add(matrix[i][r]);
                c++;
            }

            for (int i = r - 1; i >= l && c < t; i--) {
                res.add(matrix[d][i]);
                c++;
            }

            for (int i = d - 1; i >= u + 1 && c < t; i--) {
                res.add(matrix[i][l]);
                c++;
            }

            l++;
            u++;
            r--;
            d--;
        }

        return res;
    }
}
