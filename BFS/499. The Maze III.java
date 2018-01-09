/*
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up (u), down (d), left (l) or right (r), but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction. There is also a hole in this maze. The ball will drop into the hole if it rolls on to the hole.

Given the ball position, the hole position and the maze, find out how the ball could drop into the hole by moving the shortest distance. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the hole (included). Output the moving directions by using 'u', 'd', 'l' and 'r'. Since there could be several different shortest ways, you should output the lexicographically smallest way. If the ball cannot reach the hole, output "impossible".

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The ball and the hole coordinates are represented by row and column indexes.

Example 1

Input 1: a maze represented by a 2D array

0 0 0 0 0
1 1 0 0 1
0 0 0 0 0
0 1 0 0 1
0 1 0 0 0

Input 2: ball coordinate (rowBall, colBall) = (4, 3)
Input 3: hole coordinate (rowHole, colHole) = (0, 1)

Output: "lul"
Explanation: There are two shortest ways for the ball to drop into the hole.
The first way is left -> up -> left, represented by "lul".
The second way is up -> left, represented by 'ul'.
Both ways have shortest distance 6, but the first way is lexicographically smaller because 'l' < 'u'. So the output is "lul".

Example 2

Input 1: a maze represented by a 2D array

0 0 0 0 0
1 1 0 0 1
0 0 0 0 0
0 1 0 0 1
0 1 0 0 0

Input 2: ball coordinate (rowBall, colBall) = (4, 3)
Input 3: hole coordinate (rowHole, colHole) = (3, 0)
Output: "impossible"
Explanation: The ball cannot reach the hole.

Note:

    There is only one ball and one hole in the maze.
    Both the ball and hole exist on an empty space, and they will not be at the same position initially.
    The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
    The maze contains at least 2 empty spaces, and the width and the height of the maze won't exceed 30.

*/

public class Solution {
	public static void main(String[] args) {
		int[][] maze = new int[][]{{0,0,0,0,0}, {1,1,0,0,1}, {0,0,0,0,0}, {0,1,0,0,1}, {0,1,0,0,0}};
		int[] ball1 = new int[]{0,4};
        int[] hole1 = new int[]{0,1};
        int[] hole2 = new int[]{3,0};

        System.out.println(findShortestWay(maze, ball1, hole1));
        // output: "lul"
        System.out.println(findShortestWay(maze, ball1, hole2));
        // output: "impossible"
	}

	// Solution1: BFS - use PriorityQueue
    public static String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        if (maze == null || maze.length == 0 || maze[0] == null || maze[0].length == 0) {
            return "";
        }

        int row = maze.length;
        int col = maze[0].length;
        Point[][] points = new Point[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                points[i][j] = new Point(i, j, Integer.MAX_VALUE, "");
            }
        }
        points[ball[0]][ball[1]] = new Point(ball[0], ball[1], 0, "");

        int[][] dirs = new int[][]{{1,0}, {0,-1}, {0,1}, {-1,0}};
        String[] dirStr = new String[]{"d", "l", "r", "u"};
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.offer(new Point(ball[0], ball[1], 0, ""));

        while (!pq.isEmpty()) {
            Point curt = pq.poll();
            if (curt.r == hole[0] && curt.c == hole[1]) {
                return curt.dir;
            }

            for (int i = 0; i < 4; i++) {
                Point p = roll(maze, curt.r, curt.c, dirs[i][0], dirs[i][1], curt.dist, curt.dir + dirStr[i], hole);
                if (p.r == curt.r && p.c == curt.c || points[p.r][p.c].compareTo(p) <= 0) {
                    continue;
                }
                // System.out.println(p.r + " " + p.c + " " + p.dist + " " + p.dir);
                pq.offer(p);
                points[p.r][p.c] = p;
            }
        }

        return "impossible";
    }

    private static boolean canRoll(int[][] maze, int r, int c, int dr, int dc) {
        if (r + dr < 0 || c + dc < 0 || r + dr >= maze.length || c + dc >= maze[0].length || maze[r + dr][c + dc] == 1) {
            return false;
        }
        return true;
    }

    private static Point roll(int[][] maze, int r, int c, int dr, int dc, int dist, String dir, int[] hole) {
        while (canRoll(maze, r, c, dr, dc)) {
            r += dr;
            c += dc;
            dist++;
            if (r == hole[0] && c == hole[1]) {
                break;
            }
        }
        return new Point(r, c, dist, dir);
    }

    // Solution2: BFS - use queue
    public static String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        if (maze == null || maze.length == 0 || maze[0] == null || maze[0].length == 0) {
            return "";
        }

        int row = maze.length;
        int col = maze[0].length;
        Point[][] points = new Point[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                points[i][j] = new Point(i, j, Integer.MAX_VALUE, "");
            }
        }
        points[ball[0]][ball[1]] = new Point(ball[0], ball[1], 0, "");

        int[][] dirs = new int[][]{{1,0}, {0,-1}, {0,1}, {-1,0}};
        String[] dirStr = new String[]{"d", "l", "r", "u"};
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(ball[0], ball[1], 0, ""));

        while (!queue.isEmpty()) {
            Point curt = queue.poll();
            for (int i = 0; i < 4; i++) {
                Point p = roll(maze, curt.r, curt.c, dirs[i][0], dirs[i][1], curt.dist, curt.dir + dirStr[i], hole);
                if (p.r == curt.r && p.c == curt.c || points[p.r][p.c].compareTo(p) <= 0) {
                    continue;
                }
                // System.out.println(p.r + " " + p.c + " " + p.dist + " " + p.dir);
                queue.offer(p);
                points[p.r][p.c] = p;
            }
        }

        return points[hole[0]][hole[1]].dist == Integer.MAX_VALUE ? "impossible" : points[hole[0]][hole[1]].dir;
    }

    // Solution3: DFS
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        if (maze == null || maze.length == 0 || maze[0] == null || maze[0].length == 0) {
            return "";
        }

        int row = maze.length;
        int col = maze[0].length;
        Point[][] points = new Point[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                points[i][j] = new Point(i, j, Integer.MAX_VALUE, "");
            }
        }
        Point start = new Point(ball[0], ball[1], 0, "");
        points[ball[0]][ball[1]] = start;

        dfs(maze, start, points, hole);
        return points[hole[0]][hole[1]].dist == Integer.MAX_VALUE ? "impossible" : points[hole[0]][hole[1]].dir;
    }

    private void dfs(int[][] maze, Point start, Point[][] points, int[] hole) {
        int[][] dirs = new int[][]{{1,0}, {0,-1}, {0,1}, {-1,0}};
        String[] dirStr = new String[]{"d", "l", "r", "u"};

        for (int i = 0; i < 4; i++) {
            Point p = roll(maze, start.r, start.c, dirs[i][0], dirs[i][1], start.dist, start.dir + dirStr[i], hole);
            if (p.r == start.r && p.c == start.c || points[p.r][p.c].compareTo(p) <= 0) {
                continue;
            }
            // System.out.println(p.r + " " + p.c + " " + p.dist + " " + p.dir);
            points[p.r][p.c] = p;
            dfs(maze, p, points, hole);
        }
    }
}

class Point implements Comparable<Point> {
    int r;
    int c;
    int dist;
    String dir;
    public Point(int r, int c, int dist, String dir) {
        this.r = r;
        this.c = c;
        this.dist = dist;
        this.dir = dir;
    }

    public int compareTo(Point that) {
        return this.dist == that.dist ? this.dir.compareTo(that.dir) : this.dist - that.dist;
    }
}