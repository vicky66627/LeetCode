public class Solution {
	public static void main(String[] args) {
		int[][] m1 = new int[][]{{1,2,2,3,5}, {3,2,3,4,4}, {2,4,5,3,1}, {6,7,1,4,5},{5,1,1,2,4}};
		int[][] m2 = new int[][]{{1,2,2,3,0}, {3,2,3,4,4}, {2,4,5,3,1}, {6,7,1,4,5},{1,1,1,2,4}};

		List<int[]> res1 = pacificAtlantic(m1);
		for (int[] r : res1) {
			printArr(r);
		}
		// output: [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]]
		System.out.println();
		List<int[]> res2 = pacificAtlantic(m2);
		for (int[] r : res2) {
			printArr(r);
		}
		// [[0,3],[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[3,2],[3,3],[3,4],[4,0],[4,1],[4,2],[4,3],[4,4]]
	}

	// Solution1: BFS
    public static List<int[]> pacificAtlantic(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
        	return new ArrayList<int[]>();
        }
        int row = matrix.length;
        int col = matrix[0].length;
        boolean[][] paVisited = new boolean[row][col];
        boolean[][] atVisited = new boolean[row][col];
        Queue<int[]> paQueue = new LinkedList<>();
        Queue<int[]> atQueue = new LinkedList<>();

        for (int i = 0; i < row; i++) {
       		paQueue.offer(new int[]{i, 0});
       		paVisited[i][0] = true;
       		atQueue.offer(new int[]{i, col - 1});
       		atVisited[i][col - 1] = true;
        }
        for (int j = 0; j < col; j++) {
       		paQueue.offer(new int[]{0, j});
       		paVisited[0][j] = true;
       		atQueue.offer(new int[]{row - 1, j});
       		atVisited[row - 1][j] = true;
        }

        bfs(matrix, paQueue, paVisited);
        bfs(matrix, atQueue, atVisited);

        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < row; i++) {
        	for (int j = 0; j < col; j++) {
        		if (paVisited[i][j] && atVisited[i][j]) {
        			res.add(new int[]{i, j});
        		}
        	}
        }
        return res;
    }

    private static void bfs(int[][] matrix, Queue<int[]> queue, boolean[][] visited) {
    	int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    	while (!queue.isEmpty()) {
    		int[] curt = queue.poll();
    		int r = curt[0];
    		int c = curt[1];
    		for (int[] dir : dirs) {
    			int new_r = r + dir[0];
    			int new_c = c + dir[1];
    			if (new_r < 0 || new_r >= matrix.length || new_c < 0 || new_c >= matrix[0].length 
    				|| visited[new_r][new_c] || matrix[new_r][new_c] < matrix[r][c]) {
    				continue;
    			}
    			visited[new_r][new_c] = true;
    			queue.offer(new int[]{new_r, new_c});
    		}
    	}
    }

    // Solution2: DFS
    public static List<int[]> pacificAtlantic(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
        	return new ArrayList<int[]>();
        }
        int row = matrix.length;
        int col = matrix[0].length;
        boolean[][] paVisited = new boolean[row][col];
        boolean[][] atVisited = new boolean[row][col];
        
        for (int i = 0; i < row; i++) {
        	dfs(matrix, i, 0, paVisited);
        	dfs(matrix, i, col - 1, atVisited);
        }
        for (int j = 0; j < col; j++) {
        	dfs(matrix, 0, j, paVisited);
        	dfs(matrix, row - 1, j, atVisited);
        }

        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < row; i++) {
        	for (int j = 0; j < col; j++) {
        		if (paVisited[i][j] && atVisited[i][j]) {
        			res.add(new int[]{i, j});
        		}
        	}
        }
        return res;
    }

    private static void dfs(int[][] matrix, int r, int c, boolean[][] visited) {
    	int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    	visited[r][c] = true;
    	for (int[] dir : dirs) {
    		int new_r = r + dir[0];
    		int new_c = c + dir[1];
    		if (new_r < 0 || new_r >= matrix.length || new_c < 0 || new_c >= matrix[0].length 
    			|| visited[new_r][new_c] || matrix[new_r][new_c] < matrix[r][c]) {
    			continue;
    		}
    		dfs(matrix, new_r, new_c, visited);
    	}
    }

    private static void printArr(int[] arr) {
        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}