package leetcode;

//https://leetcode.com/problems/rotting-oranges/submissions/
//inplace update of visited nodes

public class RottingOranges {
    public int orangesRotting(int[][] grid) {
        boolean done = false;
        boolean changed = false;

        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 || grid[i][j] == 2) count++;
            }
        }

        int iteration = 1;
        while (!done) {
            changed = false;
            int currentCount = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == (1 + iteration)) {
                        currentCount++;
                        grid[i][j]++;
                        if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                            grid[i - 1][j] = 1 + iteration + 1;
                            currentCount++;
                            changed = true;
                        }
                        if (i + 1 < grid.length && grid[i + 1][j] == 1) {
                            grid[i + 1][j] = 1 + iteration + 1;
                            currentCount++;
                            changed = true;
                        }
                        if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                            grid[i][j - 1] = 1 + iteration + 1;
                            currentCount++;
                            changed = true;
                        }
                        if (j + 1 < grid[0].length && grid[i][j + 1] == 1) {
                            grid[i][j + 1] = 1 + iteration + 1;
                            currentCount++;
                            changed = true;
                        }
                    }
                }
            }
            if (currentCount == count) return !changed ? iteration - 1 : iteration;
            if (!changed) return -1;
            iteration++;
        }
        return iteration;
    }
}