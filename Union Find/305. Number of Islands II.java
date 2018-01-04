/*
A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example:

Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0

Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0

Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0

Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0

Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0

We return the result as an array: [1, 1, 2, 3]

Challenge:

Can you do it in time complexity O(k log mn), where k is the length of the positions?
*/

public class Solution {
	public static void main(String[] args) {
		int[][] pos1 = new int[][]{{0,0}, {0,1}, {1,2}, {2,1}};
		int[][] pos2 = new int[][]{{0,0}, {1,1}, {2,2}, {1,2}, {0,1}};
		int[][] pos3 = new int[][]{{0,0}, {1,1}, {2,2}, {2,3}, {0,1}, {2,1}};

		System.out.println(numIslands2(3, 3, pos1));
		// output: [1, 1, 2, 3]
		System.out.println(numIslands2(3, 3, pos2));
		// output: [1, 2, 3, 2, 1]
		System.out.println(numIslands2(4, 4, pos3));
		// output: [1, 2, 3, 3, 2, 1]
	}

    public static List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        if (positions == null || positions.length == 0 || positions[0] == null || positions[0].length == 0) {
        	return res;
        }

        UnionFind uf = new UnionFind(m * n);
        boolean[] isIsland = new boolean[m * n];
        uf.count = 0;
        for (int[] pos : positions) {
        	int r = pos[0];
        	int c = pos[1];
        	isIsland[r * n + c] = true;
        	uf.count++;
        	if (r - 1 >= 0 && isIsland[(r - 1) * n + c]) {
        		uf.union(r * n + c, (r - 1) * n + c);
        	}
        	if (r + 1 < m && isIsland[(r + 1) * n + c]) {
        		uf.union(r * n + c, (r + 1) * n + c);
        	}
        	if (c - 1 >= 0 && isIsland[r * n + c - 1]) {
        		uf.union(r * n + c, r * n + c - 1);
        	}
        	if (c + 1 < n && isIsland[r * n + c + 1]) {
        		uf.union(r * n + c, r * n + c + 1);
        	}
        	res.add(uf.count);
        }

        return res;
    }
}

class UnionFind {
	int count;
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
			count--;
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
}