/*
 You are given a m x n 2D grid initialized with these three possible values.

    -1 - A wall or an obstacle.
    0 - A gate.
    INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.

Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

For example, given the 2D grid:

INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF

After running your function, the 2D grid should be:

  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
*/

public class Solution {
	public static void main(String[] args) {
		final int INF = Integer.MAX_VALUE;
		int[][] rooms1 = new int[][]{{INF,-1,0,INF}, {INF,INF,INF,-1}, {INF,-1,INF,-1}, {0,-1,INF,INF}};
		int[][] rooms2 = new int[][]{{INF,-1,0,INF}, {INF,INF,INF,-1}, {INF,INF,INF,INF}, {0,-1,INF,INF}};

		wallsAndGates(rooms1);
		wallsAndGates(rooms2);

		for (int[] r : rooms1) {
			printArr(r);
		}
		// output: [[3, -1, 0, 1], [2, 2, 1, -1], [1, -1, 2, -1], [0, -1, 3, 4]]
		System.out.println();

		for (int[] r : rooms2) {
			printArr(r);
		}
		// output: [[3, -1, 0, 1], [2, 2, 1, -1],[1, 2, 2, 3], [0, -1, 3, 4]]
	}

	// Solution1: BFS
    public static void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0] == null || rooms[0].length == 0) {
            return;
        }

        int row = rooms.length;
        int col = rooms[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        int[][] dirs = new int[][]{{0,1}, {0,-1}, {1,0}, {-1,0}};
        while (!queue.isEmpty()) {
            int[] curt = queue.poll();
            int r = curt[0];
            int c = curt[1];
            for (int[] dir : dirs) {
                int new_r = r + dir[0];
                int new_c = c + dir[1];
                if (new_r < 0 || new_c < 0 || new_r >= row || new_c >= col || rooms[new_r][new_c] < rooms[r][c] + 1) {
                    continue;
                }
                rooms[new_r][new_c] = rooms[r][c] + 1;
                queue.offer(new int[]{new_r, new_c});
            }
        }
    }

    // Solution2: DFS
    public static void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0] == null || rooms[0].length == 0) {
            return;
        }

        int row = rooms.length;
        int col = rooms[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (rooms[i][j] == 0) {
                    dfs(rooms, i, j, 0);
                }
            }
        }
    }

    private static void dfs(int[][] rooms, int r, int c, int dist) {
        if (r < 0 || c < 0 || r >= rooms.length || c >= rooms[0].length || rooms[r][c] < dist) {
            return;
        }

        rooms[r][c] = dist;
        dfs(rooms, r + 1, c, dist + 1);
        dfs(rooms, r - 1, c, dist + 1);
        dfs(rooms, r, c + 1, dist + 1);
        dfs(rooms, r, c - 1, dist + 1);
    }

    private static void printArr(int[] arr) {
        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}