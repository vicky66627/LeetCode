public class Solution {
	public static void main(String[] args) {
		int[][] map1 = new int[][]{{1,4,3,1,3,2}, {3,2,1,3,2,4}, {2,3,3,2,3,1}};
		int[][] map2 = new int[][]{{1,4,3,4,4,2}, {3,2,1,3,2,4}, {2,3,3,4,4,1}};
		int[][] map3 = new int[][]{{1,4,3,1,3,2}, {3,3,3,3,3,4}, {2,3,3,2,3,1}};
		int[][] map4 = new int[][]{{12,13,1,12}, {13,4,13,12}, {13,8,10,12}, {12,13,12,12}, {13,13,13,13}};

		System.out.println(trapRainWater(map1));
		// output: 4
		System.out.println(trapRainWater(map2));
		// output: 4
		System.out.println(trapRainWater(map3));
		// output: 0
		System.out.println(trapRainWater(map4));
		// output: 14
	}

    public static int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0] == null || heightMap[0].length == 0) {
        	return 0;
        }

        int row = heightMap.length;
        int col = heightMap[0].length;

        PriorityQueue<Cell> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[row][col];

        // add border cells
        for (int i = 0; i < row; i++) {
            visited[i][0] = true;
            visited[i][col - 1] = true;
            pq.offer(new Cell(i, 0, heightMap[i][0]));
            pq.offer(new Cell(i, col - 1, heightMap[i][col - 1]));
        }
        for (int j = 1; j < col - 1; j++) {
            visited[0][j] = true;
            visited[row - 1][j] = true;
            pq.offer(new Cell(0, j, heightMap[0][j]));
            pq.offer(new Cell(row - 1, j, heightMap[row - 1][j]));
        }

        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int res = 0;
        while (!pq.isEmpty()) {
        	Cell curt = pq.poll();
        	for (int[] dir : dirs) {
        		int r = curt.row + dir[0];
        		int c = curt.col + dir[1];
        		if (r < 0 || c < 0 || r >= row || c >= col || visited[r][c]) {
        			continue;
        		}
        		visited[r][c] = true;
        		res += Math.max(0, curt.height - heightMap[r][c]);
        		pq.offer(new Cell(r, c, Math.max(curt.height, heightMap[r][c])));
        	}
        }

        return res;
    }
}

class Cell implements Comparable<Cell> {
	int row;
	int col;
	int height;
	public Cell(int row, int col, int height) {
		this.row = row;
		this.col = col;
		this.height = height;
	}

	public int compareTo(Cell that) {
		return this.height - that.height;
	}
}