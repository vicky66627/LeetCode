public class Solution {
	public static void main(String[] args) {
		int[][] grid1 = new int[][]{{0,0,1,0,0,0,0,1,0,0,0,0,0},
 									{0,0,0,0,0,0,0,1,1,1,0,0,0},
									{0,1,1,0,1,0,0,0,0,0,0,0,0},
									{0,1,0,0,1,1,0,0,1,0,1,0,0},
									{0,1,0,0,1,1,0,0,1,1,1,0,0},
									{0,0,0,0,0,0,0,0,0,0,1,0,0},
									{0,0,0,0,0,0,0,1,1,1,0,0,0},
									{0,0,0,0,0,0,0,1,1,0,0,0,0}};

		int[][] grid2 = new int[][]{{0,0,0,0,0,0,0,0}};
		int[][] grid3 = new int[][]{{1,1,0,0,0}, {1,1,0,0,0}, {0,0,0,1,1}, {0,0,0,1,1}};

		System.out.println(maxAreaOfIsland(grid1));
		// 6
		System.out.println(maxAreaOfIsland(grid2));
		// 0
		System.out.println(maxAreaOfIsland(grid3));
		// 4
	}

	// Solution1: DFS
    public static int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
        	return 0;
        }

        int row = grid.length;
        int col = grid[0].length;
        int res = 0;
        for (int i = 0; i < row; i++) {
        	for (int j = 0; j < col; j++) {
        		if (grid[i][j] == 1) {
        			int[] cnt = new int[1];
        			dfs(grid, i, j, cnt);
        			res = Math.max(res, cnt[0]);
        		}
        	}
        }

        return res;
    }

    private static void dfs(int[][] grid, int i, int j, int[] cnt) {
    	if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0) {
    		return;
    	}
    	cnt[0]++;
    	grid[i][j] = 0;
    	int[][] dirs = new int[][]{{0,-1}, {0,1}, {-1,0}, {1,0}};
    	for (int[] dir : dirs) {
    		dfs(grid, i + dir[0], j + dir[1], cnt);
    	}
    }

    // Solution2: BFS
    public static int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
        	return 0;
        }

        int row = grid.length;
        int col = grid[0].length;
        int res = 0;
        Queue<int[]> queue = new LinkedList<>();
        int[][] dirs = new int[][]{{0,-1}, {0,1}, {-1,0}, {1,0}};

        for (int i = 0; i < row; i++) {
        	for (int j = 0; j < col; j++) {
        		if (grid[i][j] == 1) {
        			queue.offer(new int[]{i, j});
        			grid[i][j] = 0;
        			int cnt = 0;
        			while (!queue.isEmpty()) {
        				int[] curt = queue.poll();
        				for (int[] dir : dirs) {
        					int r = curt[0] + dir[0];
        					int c = curt[1] + dir[1];
        					if (r < 0 || c < 0 || r >= row || c >= col || grid[r][c] == 0) {
        						continue;
        					}
        					queue.offer(new int[]{r, c});
        					grid[r][c] = 0;
        				}
        				cnt++;
        			}
        			res = Math.max(res, cnt);
        		}
        	}
        }

        return res;
    }
}