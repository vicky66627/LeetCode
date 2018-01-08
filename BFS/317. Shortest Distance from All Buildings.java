/*
You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

    Each 0 marks an empty land which you can pass by freely.
    Each 1 marks a building which you cannot pass through.
    Each 2 marks an obstacle which you cannot pass through.

For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):

1 - 0 - 2 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0

The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.

Note:
There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
*/

public class Solution {
	public static void main(String[] args) {
		int[][] grid1 = new int[][]{{1,0,2,0,1}, {0,0,0,0,0}, {0,0,1,0,0}};
		int[][] grid2 = new int[][]{{1,0,2,0,0}, {0,0,0,0,0}, {0,0,1,0,0}};
		int[][] grid3 = new int[][]{{1,0,2,0,0}, {0,0,0,1,0}, {0,0,1,0,0}};
		int[][] grid4 = new int[][]{{1,0,2,0,1}, {0,0,2,0,0}, {0,0,1,0,0}};

		System.out.println(shortestDistance(grid1));
		// output: 7 (3 + 3 + 1)
		System.out.println(shortestDistance(grid2));
		// output: 4 (2 + 2)
		System.out.println(shortestDistance(grid3));
		// output: 5 (1 + 1 + 3)
		System.out.println(shortestDistance(grid4));
		// output: -1
	}

    public static int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return -1;
        }

        int row = grid.length;
        int col = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int[][] dist = new int[row][col];
        int[][] reachedBd = new int[row][col];
        int totalBuilding = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    int curtPathLen = 1;
                    totalBuilding++;
                    boolean[][] visited = new boolean[row][col];
                    visited[i][j] = true;
                    queue.offer(new int[]{i, j});
                    while (!queue.isEmpty()) {
                        int size = queue.size();
                        for (int k = 0; k < size; k++) {
                            int[] curt = queue.poll();
                            int r = curt[0];
                            int c = curt[1];
                            
                            for (int[] dir : dirs) {
                                int new_r = r + dir[0];
                                int new_c = c + dir[1];
                                if (new_r < 0 || new_c < 0 || new_r >= row || new_c >= col || visited[new_r][new_c] || grid[new_r][new_c] != 0) {
                                    continue;
                                }
                                dist[new_r][new_c] += curtPathLen;
                                reachedBd[new_r][new_c]++;
                                visited[new_r][new_c] = true;
                                queue.offer(new int[]{new_r, new_c});
                            }
                        }
                        curtPathLen++;
                    }
                }
            }
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0 && reachedBd[i][j] == totalBuilding && dist[i][j] < res) {
                    res = dist[i][j];
                }
            }
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }
}