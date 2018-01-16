public class Solution {
	public static void main(String[] args) {
		System.out.println(findPaths(2, 2, 2, 0, 2));
		// output: 6
		System.out.println(findPaths(1, 3, 3, 0, 1));
		// output: 12
		System.out.println(findPaths(3, 3, 3, 1, 0));
        // output: 13
        System.out.println(findPaths(3, 3, 1, 1, 1));
        // output: 0
		System.out.println(findPaths(1, 3, 50, 0, 1));
        // output: 268435448
        System.out.println(findPaths(50, 50, 50, 0, 1));
        // output: 263523405
	}

	// Solution1: DFS + memoization - fast
	static final int M = 1000000007;
    public static int findPaths(int m, int n, int N, int i, int j) {
        if ((m == 0 && n == 0) || N == 0 || (i < 0 || i >= m || j < 0 || j >= n)) {
        	return 0;
        }
        int[][][] memo = new int[m][n][N + 1];
        for (int[][] l : memo) {
            for (int[] x : l) {
                Arrays.fill(x, -1);
            }
        }

        return dfs(m, n, N, i, j, memo);
    }

    private static int dfs(int row, int col, int step, int i, int j, int[][][] memo) {
    	if (step < 0) {
    		return 0;
    	}

    	if (i < 0 || i >= row || j < 0 || j >= col) {
    		return 1;
    	}
    	if (memo[i][j][step] >= 0) {
    		return memo[i][j][step];
    	}

    	long res = 0;
    	res += dfs(row, col, step - 1, i + 1, j, memo) % M;
    	res += dfs(row, col, step - 1, i - 1, j, memo) % M;
    	res += dfs(row, col, step - 1, i, j + 1, memo) % M;
    	res += dfs(row, col, step - 1, i, j - 1, memo) % M;

    	res %= M;
    	memo[i][j][step] = (int)res;
    	return memo[i][j][step];
    }

    // Solution2: DP
    public static int findPaths(int m, int n, int N, int i, int j) {
        if ((m == 0 && n == 0) || N == 0 || (i < 0 || i >= m || j < 0 || j >= n)) {
        	return 0;
        }

        final int M = 1000000007;
        int[][] cnt = new int[m][n];
        cnt[i][j] = 1;

        int res = 0;
        int[][] dirs = new int[][]{{0,1}, {0,-1}, {1,0}, {-1,0}};

        for (int step = 0; step < N; step++) {
        	int[][] tmp = new int[m][n];
        	for (int r = 0; r < m; r++) {
        		for (int c = 0; c < n; c++) {
        			for (int[] dir : dirs) {
        				int nr = r + dir[0];
        				int nc = c + dir[1];
        				if (nr < 0 || nc < 0 || nr >= m || nc >= n) {
        					res = (res + cnt[r][c]) % M;
        				} else {
        					tmp[nr][nc] = (tmp[nr][nc] + cnt[r][c]) % M;
        				}
        			}
        		}
        	}
        	cnt = tmp;
        }

        return res;
    }
}