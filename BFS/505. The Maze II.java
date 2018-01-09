/*
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.

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

Output: 12
Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
             The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.

Example 2

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: -1
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

		System.out.println(shortestDistance(maze, start1, end1));
		// output: 12
		System.out.println(shortestDistance(maze, start1, end2));
		// output: -1
	}

	// Solution1: BFS
    public static int shortestDistance(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0 || maze[0] == null || maze[0].length == 0) {
            return -1;
        }

        int row = maze.length;
        int col = maze[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start[0], start[1]});
        int[][] dist = new int[row][col];
        for (int[] d : dist) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        dist[start[0]][start[1]] = 0;
        int[][] dirs = new int[][]{{0,1}, {0,-1}, {1,0}, {-1,0}};

        while (!queue.isEmpty()) {
            int[] curt = queue.poll();

            for (int[] dir : dirs) {
                int[] next = roll(maze, curt[0], curt[1], dir[0], dir[1]);
                if (next[0] == curt[0] && next[1] == curt[1] || dist[next[0]][next[1]] <= dist[curt[0]][curt[1]] + next[2]) {
                    continue;
                }
                dist[next[0]][next[1]] = dist[curt[0]][curt[1]] + next[2];
                queue.offer(new int[]{next[0], next[1]});
            }
        }
        return dist[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : dist[destination[0]][destination[1]];
    }

    // Solution2: DFS
    public static int shortestDistance(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0 || maze[0] == null || maze[0].length == 0) {
            return -1;
        }

        int row = maze.length;
        int col = maze[0].length;

        int[][] dist = new int[row][col];
        for (int[] d : dist) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        dist[start[0]][start[1]] = 0;

        dfs(maze, start[0], start[1], destination, dist);
        return dist[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : dist[destination[0]][destination[1]];
    }

    private static void dfs(int[][] maze, int r, int c, int[] dest, int[][] dist) {
        int[][] dirs = new int[][]{{0,1}, {0,-1}, {1,0}, {-1,0}};
        for (int[] dir : dirs) {
            int[] next = roll(maze, r, c, dir[0], dir[1]);
            if (next[0] == r && next[1] == c || dist[next[0]][next[1]] <= dist[r][c] + next[2]) {
                continue;
            }
            dist[next[0]][next[1]] = dist[r][c] + next[2];
            dfs(maze, next[0], next[1], dest, dist);
        }
    }

    private static boolean canRoll(int[][] maze, int r, int c) {
        if (r < 0 || c < 0 || r >= maze.length || c >= maze[0].length || maze[r][c] == 1) {
            return false;
        }
        return true;
    }

    private static int[] roll(int[][] maze, int r, int c, int dr, int dc) {
        int len = 0;
        while (canRoll(maze, r + dr, c + dc)) {
            r += dr;
            c += dc;
            len++;
        }
        return new int[]{r, c, len};
    }

    // Solution3: use PriorityQueue
    public static int shortestDistance(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0 || maze[0] == null || maze[0].length == 0) {
            return -1;
        }

        int row = maze.length;
        int col = maze[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.offer(new int[]{start[0], start[1], 0});
        int[][] dist = new int[row][col];
        for (int[] d : dist) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        dist[start[0]][start[1]] = 0;
        int[][] dirs = new int[][]{{0,1}, {0,-1}, {1,0}, {-1,0}};

        while (!pq.isEmpty()) {
            int[] curt = pq.poll();
            if (curt[0] == destination[0] && curt[1] == destination[1]) {
                return dist[curt[0]][curt[1]];
            }

            for (int[] dir : dirs) {
                int[] next = roll(maze, curt[0], curt[1], dir[0], dir[1], curt[2]);
                if (next[0] == curt[0] && next[1] == curt[1] || dist[next[0]][next[1]] <= next[2]) {
                    continue;
                }
                dist[next[0]][next[1]] = next[2];
                pq.offer(next);
            }
        }
        return -1;
    }

    private static int[] roll(int[][] maze, int r, int c, int dr, int dc, int dist) {
        int len = 0;
        while (canRoll(maze, r, c, dr, dc)) {
            r += dr;
            c += dc;
            len++;
        }
        return new int[]{r, c, dist + len};
    }
}