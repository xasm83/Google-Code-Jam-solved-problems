package leetcode;

//https://leetcode.com/problems/minimum-path-sum/submissions/
//fill the matrix with previous length starting from 00 then grow around
public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int[][] length = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                length[i][j] = grid[i][j] + getLength(i, j, length);
            }
        }
        return length[grid.length - 1][grid[0].length - 1];
    }

    private int getLength(int i, int j, int[][] length) {
        int upi = i;
        int upj = j - 1;
        int up = upj < 0 ? Integer.MAX_VALUE : length[upi][upj];

        int lefti = i - 1;
        int leftj = j;
        int left = lefti < 0 ? Integer.MAX_VALUE : length[lefti][leftj];
        return left == up && up == Integer.MAX_VALUE ? 0 : Math.min(up, left);
    }
}
