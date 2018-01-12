public class Solution {
	public static void main(String[] args) {
		int[][] m1 = new int[][]{{9,9,4}, {6,6,8}, {2,1,1}};
		int[][] m2 = new int[][]{{3,4,5}, {3,2,6}, {2,2,1}};
		int[][] m3 = new int[][]{{1,8,7}, {2,9,6}, {3,4,5}};

		System.out.println(longestIncreasingPath(m1));
		// output: 4 (1-2-6-9)
		System.out.println(longestIncreasingPath(m2));
		// output: 4 (3-4-5-6)
		System.out.println(longestIncreasingPath(m3));
		// output: 9 (1-2-3-4-5-6-7-8-9)
	}

	// Solution1: DFS with memoization
    public static int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
        	return 0;
        }

        int row = matrix.length;
        int col = matrix[0].length;

        int[][] cache = new int[row][col];
        int res = 0;
        for (int i = 0; i < row; i++) {
        	for (int j = 0; j < col; j++) {
        		res = Math.max(res, dfs(matrix, i, j, cache));
        	}
        }

        return res;
    }

    private static int dfs(int[][] matrix, int i, int j, int[][] cache) {
    	int row = matrix.length;
    	int col = matrix[0].length;
    	if (cache[i][j] != 0) {
    		return cache[i][j];
    	}

    	int[][] dirs = new int[][]{{0,1}, {0,-1}, {1,0}, {-1,0}};
        int max = 1;
    	for (int[] dir : dirs) {
    		int r = i + dir[0];
    		int c = j + dir[1];
    		if (r < 0 || c < 0 || r >= row || c >= col || matrix[r][c] <= matrix[i][j]) {
    			continue;
    		}

    		max = Math.max(max, dfs(matrix, r, c, cache) + 1);
    	}
        cache[i][j] = max;
    	return max;
    }
}