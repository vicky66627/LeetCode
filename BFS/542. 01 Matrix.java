public class Solution {
	public static void main(String[] args) {
		int[][] m1 = new int[][]{{0,0,0}, {0,1,0}, {0,0,0}};
		int[][] m2 = new int[][]{{0,0,0}, {0,1,0}, {1,1,1}};

		int[][] res1 = updateMatrix(m1);
		int[][] res2 = updateMatrix(m2);

		for (int[] m : res1) {
			printArr(m);
		}
		// output: [[0,0,0], [0,1,0], [0,0,0]]
		for (int[] m : res2) {
			printArr(m);
		}
		// output: [[0,0,0], [0,1,0], [1,2,1]]
	}

	// Solution1: BFS
    public static int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
        	return matrix;
        }

        int row = matrix.length;
        int col = matrix[0].length;

        int[][] dist = new int[row][col];
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < row; i++) {
        	Arrays.fill(dist[i], Integer.MAX_VALUE);
        	for (int j = 0; j < col; j++) {
        		if (matrix[i][j] == 0) {
        			dist[i][j] = 0;
        			queue.offer(new int[]{i, j});
        		}
        	}
        }

        int[][] dirs = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
        while (!queue.isEmpty()) {
        	int[] curt = queue.poll();
        	int r = curt[0];
        	int c = curt[1];
        	for (int[] dir : dirs) {
        		int new_r = r + dir[0];
        		int new_c = c + dir[1];
        		if (new_r >= 0 && new_c >= 0 && new_r < row && new_c < col && dist[new_r][new_c] > dist[r][c] + 1) {
        			dist[new_r][new_c] = dist[r][c] + 1;
        			queue.offer(new int[]{new_r, new_c});
        		}
        	}
        }

        return dist;
    }

    // Solution2: DP
    public static int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
        	return matrix;
        }

        int row = matrix.length;
        int col = matrix[0].length;

        int[][] dist = new int[row][col];
        
        // from top left
        for (int i = 0; i < row; i++) {
        	Arrays.fill(dist[i], Integer.MAX_VALUE - 100000);
        	for (int j = 0; j < col; j++) {
        		if (matrix[i][j] == 0) {
        			dist[i][j] = 0;
        		} else {
        			if (i > 0) {
        				dist[i][j] = Math.min(dist[i][j], dist[i - 1][j] + 1);
        			}
        			if (j > 0) {
        				dist[i][j] = Math.min(dist[i][j], dist[i][j - 1] + 1);
        			}
        		}
        	}
        }

        // from bottom right
        for (int i = row - 1; i >= 0; i--) {
        	for (int j = col - 1; j >= 0; j--) {
       			if (i < row - 1) {
       				dist[i][j] = Math.min(dist[i][j], dist[i + 1][j] + 1);
       			}
        		if (j < col - 1) {
        			dist[i][j] = Math.min(dist[i][j], dist[i][j + 1] + 1);
        		}
        	}
        }

        return dist;
    }

    private static void printArr(int[] arr) {
        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}