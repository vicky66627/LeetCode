/*
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.

Example 1

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: true
Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.

Example 2

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: false
Explanation: There is no way for the ball to stop at the destination.

Note:

    There is only one ball and one destination in the maze.
    Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
    The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
    The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
*/

public class Solution {
	public static void main(String[] args) {
		int[][] maze = new int[][]{{0,0,1,0,0}, {0,0,0,0,0}, {0,0,0,1,0}, {1,1,0,1,1}, {0,0,0,0,0}};
		int[] start1 = new int[]{0,4};
		int[] end1 = new int[]{4,4};
		int[] end2 = new int[]{3,2};

		System.out.println(hasPath(maze, start1, end1));
		// output: true
		System.out.println(hasPath(maze, start1, end2));
		// output: false
	}

	// Solution1: BFS
    public static int shortestDistance(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0 || maze[0] == null || maze[0].length == 0) {
            return false;
        }

        int row = maze.length;
        int col = maze[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        boolean[][] visited = new boolean[row][col];
        visited[start[0]][start[1]] = true;
        int[][] dirs = new int[][]{{0,1}, {0,-1}, {1,0}, {-1,0}};

        while (!queue.isEmpty()) {
            int[] curt = queue.poll();
            if (curt[0] == destination[0] && curt[1] == destination[1]) {
                return true;
            }

            for (int[] dir : dirs) {
                int[] next = roll(maze, curt[0], curt[1], dir[0], dir[1]);
                if (next[0] == curt[0] && next[1] == curt[1] || visited[next[0]][next[1]]) {
                    continue;
                }
                visited[next[0]][next[1]] = true;
                queue.offer(new int[]{next[0], next[1]});
            }
        }
        return false;
    }

    // Solution2: DFS
    public static boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0 || maze[0] == null || maze[0].length == 0) {
            return false;
        }

        int row = maze.length;
        int col = maze[0].length;

        boolean[][] visited = new boolean[row][col];
        visited[start[0]][start[1]] = true;

        return dfs(maze, start[0], start[1], destination, visited);
    }

    private static boolean dfs(int[][] maze, int r, int c, int[] dest, boolean[][] visited) {
        if (r == dest[0] && c == dest[1]) {
            return true;
        }

        int[][] dirs = new int[][]{{0,1}, {0,-1}, {1,0}, {-1,0}};
        for (int[] dir : dirs) {
            int[] next = roll(maze, r, c, dir[0], dir[1]);
            if (next[0] == r && next[1] == c || visited[next[0]][next[1]]) {
                continue;
            }
            visited[next[0]][next[1]] = true;
            if (dfs(maze, next[0], next[1], dest, visited)) {
                return true;
            }
        }
        return false;
    }

    private static boolean canRoll(int[][] maze, int r, int c, int dr, int dc) {
        if (r + dr < 0 || c + dc < 0 || r + dr >= maze.length || c + dc >= maze[0].length || maze[r + dr][c + dc] == 1) {
            return false;
        }
        return true;
    }

    private static int[] roll(int[][] maze, int r, int c, int dr, int dc) {
        while (canRoll(maze, r, c, dr, dc)) {
            r += dr;
            c += dc;
        }
        return new int[]{r, c};
    }
}