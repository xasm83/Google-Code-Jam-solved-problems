package leetcode;


import java.util.Deque;
import java.util.LinkedList;

public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        int xlength = grid.length;
        int ylength = xlength == 0 ? 0 : grid[0].length;

        if (xlength == 0) return 0;
        if (xlength == 1 && ylength == 1 && grid[0][0] == '1') return 1;

        boolean[][] visited = new boolean[xlength][ylength];
        int res = 0;

        for (int i = 0; i < xlength; i++) {
            for (int j = 0; j < ylength; j++) {
                if ((grid[i][j] == '1') && !visited[i][j]) {
                    res++;
                    Deque<Pairr> stack = new LinkedList<Pairr>();
                    stack.push(new Pairr(i, j));
                    while (!stack.isEmpty()) {
                        Pairr p = stack.pop();
                        int x = p.x;
                        int y = p.y;
                        visited[x][y] = true;

                        if ((x + 1 < xlength) && !visited[x + 1][y] && grid[x + 1][y] == '1') {
                            stack.push(new Pairr(x + 1, y));
                        }

                        if ((y + 1 < ylength) && !visited[x][y + 1] && grid[x][y + 1] == '1') {
                            stack.push(new Pairr(x, y + 1));
                        }

                        if ((x - 1 >= 0) && !visited[x - 1][y] && grid[x - 1][y] == '1') {
                            stack.push(new Pairr(x - 1, y));
                        }

                        if ((y - 1 >= 0) && !visited[x][y - 1] && grid[x][y - 1] == '1') {
                            stack.push(new Pairr(x, y - 1));
                        }
                    }
                }
            }
        }
        return res;
    }


    class Pairr {
        int x;
        int y;

        public Pairr(int i, int j) {
            x = i;
            y = j;
        }
    }
}
