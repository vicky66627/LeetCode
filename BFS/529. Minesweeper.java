public class Solution {
	public static void main(String[] args) {
		char[][] board1 = new char[][]{{'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'M', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E'}};
		int[] click1 = new int[]{3, 0};
		char[][] board2 = new char[][]{{'B', '1', 'E', '1', 'B'}, {'B', '1', 'M', '1', 'B'}, {'B', '1', '1', '1', 'B'}, {'B', 'B', 'B', 'B', 'B'}};
		int[] click2 = new int[]{1, 2};

		char[][] res1 = updateBoard(board1, click1);
		for (char[] c : res1) {
			printArr(c);
		}
		System.out.println();
		// output: [['B', '1', 'E', '1', 'B'],
 		// 			['B', '1', 'M', '1', 'B'],
 		//			['B', '1', '1', '1', 'B'],
 		// 			['B', 'B', 'B', 'B', 'B']]

		char[][] res2 = updateBoard(board2, click2);
		for (char[] c : res2) {
			printArr(c);
		}
		// output: [['B', '1', 'E', '1', 'B'],
 		//			['B', '1', 'X', '1', 'B'],
 		//			['B', '1', '1', '1', 'B'],
		//			['B', 'B', 'B', 'B', 'B']]
	}

	// Solution1: BFS
    public static char[][] updateBoard(char[][] board, int[] click) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0 || click == null || click.length != 2) {
        	return board;
        }

        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
        	return board;
        }
        int row = board.length;
        int col = board[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(click);
        int[][] dirs = new int[][]{{0,1}, {1,0}, {0,-1}, {-1,0}, {1,1}, {1,-1}, {-1,1}, {-1,-1}};

        while (!queue.isEmpty()) {
        	int[] curt = queue.poll();
        	int mineCnt = adjMine(board, curt[0], curt[1], dirs);

        	if (mineCnt == 0) {
        		board[curt[0]][curt[1]] = 'B';
        		for (int[] dir : dirs) {
	        		int r = curt[0] + dir[0];
	        		int c = curt[1] + dir[1];
	        		if (r < 0 || c < 0 || r >= row || c >= col) {
	        			continue;
	        		}
	        		if (board[r][c] == 'E') {
	        			queue.offer(new int[]{r, c});
	        			board[r][c] = 'B';
	        		}
        		}
        	} else {
				board[curt[0]][curt[1]] = (char)(mineCnt + '0');
			}
        }

        return board;
    }

    // Solution2: DFS
    public static char[][] updateBoard(char[][] board, int[] click) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0 || click == null || click.length != 2) {
        	return board;
        }
        int row = board.length;
        int col = board[0].length;

        if (board[click[0]][click[1]] == 'M') {
        	board[click[0]][click[1]] = 'X';
        	return board;
        }

        dfs(board, click[0], click[1]);
        return board;
    }

    private static void dfs(char[][] board, int r, int c) {
    	int row = board.length;
        int col = board[0].length;
    	if (r < 0 || c < 0 || r >= row || c >= col || board[r][c] != 'E') {
    		return;
    	}
 	
    	int[][] dirs = new int[][]{{0,1}, {1,0}, {0,-1}, {-1,0}, {1,1}, {1,-1}, {-1,1}, {-1,-1}};
    	int mineCnt = adjMine(board, r, c, dirs);
    	if (mineCnt == 0) {
    		board[r][c] = 'B';
    		for (int [] dir : dirs) {
	    		int new_r = r + dir[0];
	    		int new_c = c + dir[1];
	    		if (new_r < 0 || new_c < 0 || new_r >= row || new_c >= col) {
	    			continue;
	    		}
	    		if (board[new_r][new_c] == 'E') {
	    			dfs(board, new_r, new_c);
	    		}
    		}
    	} else {
    		board[r][c] = (char)(mineCnt + '0');
    	}
    }

    private static int adjMine(char[][] board, int r, int c, int[][] dirs) {
    	int row = board.length;
        int col = board[0].length;
        int cnt = 0;
        for (int[] dir : dirs) {
            int new_r = r + dir[0];
            int new_c = c + dir[1];
            if (new_r < 0 || new_c < 0 || new_r >= row || new_c >= col) {
                continue;
            }
            if (board[new_r][new_c] == 'M') {
                cnt++;
            }
        }
        return cnt;
    }
}