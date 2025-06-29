package problems.search_2d_matrix;

public class Solution {
    public static boolean searchMatrix(int[][] matrix, int target) {
        int l = 0, r = matrix.length * matrix[0].length - 1;
        int rl = matrix[0].length;

        int mid = 0;

        while (l < r) {
            mid = l + (r - l) / 2;

            if (matrix[mid / rl][mid % rl] == target) {
                return true;
            }

            if (matrix[mid / rl][mid % rl] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        mid = l + (r - l) / 2;

        return matrix[mid / rl][mid % rl] == target;
    }

    public static void main(String[] args) {
        var input = new int[][] {
                { 1, 3, 5, 7 },
                { 10, 11, 16, 20 },
                { 23, 30, 34, 60 }
        };

        var input2 = new int[][] {
                { 1, 3 }
        };

        searchMatrix(input, 0);
        searchMatrix(input2, 3);
    }
}
