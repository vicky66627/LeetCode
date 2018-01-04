public class Solution {
	public static void main(String[] args) {
		char[][] board1 = new char[][]{{'X', 'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X', 'X'}, {'X', 'O', 'X', 'O', 'X'}, {'X', 'O', 'O', 'X', 'X'}};
        char[][] board2 = new char[][]{{'X', 'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X', 'X'}, {'X', 'O', 'X', 'O', 'X'}, {'X', 'X', 'O', 'X', 'X'}};
		char[][] board3 = new char[][]{{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};

        solve(board1);
        solve(board2);
        solve(board3);

        for (char[] b : board1) {
		  printArr(b);
        }
		// output: XXXXX, XOOXX, XOXXX, XOOXX
        for (char[] b : board2) {
          printArr(b);
        }
        // output: XXXXX, XXXXX, XXXXX, XXOXX
		for (char[] b : board3) {
          printArr(b);
        }
		// output: XXXX, XXXX, XXXX, XOXX
	}

	// Solution1: Union-Find
    public static void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return;
        }
        int row = board.length;
        int col = board[0].length;
        UnionFind uf = new UnionFind(row * col + 1);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if ((i == 0 || i == row - 1 || j == 0 || j == col - 1) && board[i][j] == 'O') {
                    uf.union(i * col + j, row * col);
                } else if (board[i][j] == 'O') {
                    if (board[i - 1][j] == 'O') {
                        uf.union(i * col + j, (i - 1) * col + j);
                    }
                    if (board[i + 1][j] == 'O') {
                        uf.union(i * col + j, (i + 1) * col + j);
                    }
                    if (board[i][j - 1] == 'O') {
                        uf.union(i * col + j, i * col + j - 1);
                    }
                    if (board[i][j + 1] == 'O') {
                        uf.union(i * col + j, i * col + j + 1);
                    }
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!uf.connected(i * col + j, row * col)) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    // Solution2: DFS
    public static void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return;
        }
        int row = board.length;
        int col = board[0].length;

        // check left & right boundary
        for (int i = 0; i < row; i++) {
            check(board, i, 0);
            if (col > 1) {
                check(board, i, col - 1);
            }
        }
        // check top & bottom boundary
        for (int j = 1; j < col - 1; j++) {
            check(board, 0, j);
            if (row > 1) {
                check(board, row - 1, j);
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = board[i][j] == '1' ? 'O' : 'X';
            }
        }
    }

    private static void check(char[][] board, int i, int j) {
        int row = board.length;
        int col = board[0].length;

        if (board[i][j] == 'O') {
            board[i][j] = '1';

            if (i > 1) {
                check(board, i - 1, j);
            }
            if (j > 1) {
                check(board, i, j - 1);
            }
            if (i + 1 < row - 1) {
                check(board, i + 1, j);
            }
            if (j + 1 < col - 1) {
                check(board, i, j + 1);
            }
        }
    }

    // Solution3: BFS
    public static void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return;
        }
        int row = board.length;
        int col = board[0].length;

        Queue<int[]> queue = new LinkedList<>();
        // check left & right boundary
        for (int i = 0; i < row; i++) {
            if (board[i][0] == 'O') {
                queue.offer(new int[]{i, 0});
            }
            if (col > 1 && board[i][col - 1] == 'O') {
                queue.offer(new int[]{i, col - 1});
            }
        }

        // check top & bottom boundary
        for (int j = 1; j < col - 1; j++) {
            if (board[0][j] == 'O') {
                queue.offer(new int[]{0, j});
            }
            if (row > 1 && board[row - 1][j] == 'O') {
                queue.offer(new int[]{row - 1, j});
            }
        }

        while (!queue.isEmpty()) {
            int[] curt = queue.poll();
            int r = curt[0];
            int c = curt[1];
            board[r][c] = '1';
            if (r > 1 && board[r - 1][c] == 'O') {
                queue.offer(new int[]{r - 1, c});
            }
            if (r + 1 < row - 1 && board[r + 1][c] == 'O') {
                queue.offer(new int[]{r + 1, c});
            }
            if (c > 1 && board[r][c - 1] == 'O') {
                queue.offer(new int[]{r, c - 1});
            }
            if (c + 1 < col - 1 && board[r][c + 1] == 'O') {
                queue.offer(new int[]{r, c + 1});
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = board[i][j] == '1' ? 'O' : 'X';
            }
        }
    }

    private static void printArr(char[] arr) {
        for (char val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}

class UnionFind {
    int[] parent;
    int[] rank;
    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int i) {
        while (parent[i] != i) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }

        return i;
    }

    public void union(int x, int y) {
        int xroot = find(x);
        int yroot = find(y);
        if (xroot != yroot) {
            if (rank[xroot] < rank[yroot]) {
                parent[xroot] = yroot;
            } else if (rank[yroot] < rank[xroot]) {
                parent[yroot] = xroot;
            } else {
                parent[xroot] = yroot;
                rank[yroot]++;
            }
        }
    }

    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}