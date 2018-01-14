/*
Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Count the number of distinct islands. An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.

Example 1:

11000
11000
00011
00011

Given the above grid map, return 1.

Example 2:

11011
10000
00001
11011

Given the above grid map, return 3.

Notice that:

11
1

and

 1
11

are considered different island shapes, because we do not consider reflection / rotation.

Note: The length of each dimension in the given grid does not exceed 50. 
*/

public class Solution {
	public static void main(String[] args) {
		int[][] grid1 = new int[][]{{1,1,0,0,0}, {1,1,0,0,0}, {0,0,0,1,1}, {0,0,0,1,1}};
		int[][] grid2 = new int[][]{{1,1,0,1,1}, {1,0,0,0,0}, {0,0,0,0,1}, {1,1,0,1,1}};
		int[][] grid3 = new int[][]{{1,1,0,0}, {0,0,1,1}, {1,1,0,0}, {0,0,1,1}};
		int[][] grid4 = new int[][]{{1,1,1,1}, {1,0,1,0}, {0,0,0,0}, {0,1,1,1}, {1,1,0,1}};
		int[][] grid5 = new int[][]{{0,0,1}, {1,0,1}, {1,0,1}};
		int[][] grid6 = new int[][]{{1,1,0}, {0,1,1}, {0,0,0}, {1,1,1}, {0,1,0}};

		System.out.println(numDistinctIslands(grid1));
		// output: 1
		System.out.println(numDistinctIslands(grid2));
		// output: 3
		System.out.println(numDistinctIslands(grid3));
		// output: 1
		System.out.println(numDistinctIslands(grid4));
		// output: 2
		System.out.println(numDistinctIslands(grid5));
		// output: 2
		System.out.println(numDistinctIslands(grid6));
		// output: 2
	}

	// Solution1: Hash by local cooridnates
    public static int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
        	return 0;
        }

        int row = grid.length;
        int col = grid[0].length;
        boolean[][] visited = new boolean[row][col];
        Set<Set<Integer>> shapes = new HashSet<>();

        for (int i = 0; i < row; i++) {
        	for (int j = 0; j < col; j++) {
        		if (grid[i][j] == 1 && !visited[i][j]) {
        			Set<Integer> shape = new HashSet<>();
        			explore(grid, i, j, i, j, visited, shape);
        			if (!shape.isEmpty()) {
        				shapes.add(shape);
        			}
        		}
        	}
        }
        return shapes.size();
    }

    private static void explore(int[][] grid, int r, int c, int dr, int dc, boolean[][] visited, Set<Integer> shape) {
    	if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || visited[r][c] || grid[r][c] != 1) {
    		return;
    	}

    	visited[r][c] = true;
    	shape.add((r - dr) * 2 * grid[0].length + (c - dc));  // 注意这步, 如果不*2, test case4将出错
    	explore(grid, r + 1, c, dr, dc, visited, shape);
    	explore(grid, r - 1, c, dr, dc, visited, shape);
    	explore(grid, r, c + 1, dr, dc, visited, shape);
    	explore(grid, r, c - 1, dr, dc, visited, shape);
    }

    // Solution2: Hash by path signature
    public static int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
        	return 0;
        }

        int row = grid.length;
        int col = grid[0].length;
        boolean[][] visited = new boolean[row][col];
        Set<List<Integer>> shapes = new HashSet<>();

        for (int i = 0; i < row; i++) {
        	for (int j = 0; j < col; j++) {
        		if (grid[i][j] == 1 && !visited[i][j]) {
        			List<Integer> shape = new ArrayList<>();
        			explore(grid, i, j, 0, visited, shape);
        			if (!shape.isEmpty()) {
        				shapes.add(shape);
        			}
        		}
        	}
        }
        return shapes.size();
    }

    private static void explore(int[][] grid, int r, int c, int dir, boolean[][] visited, List<Integer> shape) {
    	if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || visited[r][c] || grid[r][c] != 1) {
    		return;
    	}

    	visited[r][c] = true;
    	shape.add(dir);
    	explore(grid, r + 1, c, 1, visited, shape);
    	explore(grid, r - 1, c, 2, visited, shape);
    	explore(grid, r, c + 1, 3, visited, shape);
    	explore(grid, r, c - 1, 4, visited, shape);
    	shape.add(0);  // 注意这步, 如果没有它, test case6将出错
    }
}