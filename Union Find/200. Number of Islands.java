public class Solution {
	public static void main(String[] args) {
		char[][] grid1 = new char[][]{{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
		char[][] grid2 = new char[][]{{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};

		System.out.println(numIslands(grid1));
		// output: 1
		System.out.println(numIslands(grid2));
		// output: 3
	}

	// Solution1: DFS
    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
        	return 0;
        }

        int cnt = 0;
        for (int i = 0; i < grid.length; i++) {
        	for (int j = 0; j < grid[i].length; j++) {
        		if (grid[i][j] == '1') {
        			cnt++;
        			dfs(grid, i, j);
        		}
        	}
        }
        return cnt;
    }

    private static void dfs(char[][] grid, int i, int j) {
    	if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
    		return;
    	}

    	grid[i][j] = '0';

    	dfs(grid, i + 1, j);
    	dfs(grid, i - 1, j);
    	dfs(grid, i, j + 1);
    	dfs(grid, i, j - 1);
    }

    // Solution2: DFS - without modifying orignal matrix
    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }

        int cnt = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    cnt++;
                    dfs(grid, i, j, visited);
                }
            }
        }
        return cnt;
    }

    private static void dfs(char[][] grid, int i, int j, boolean[][] visited) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || visited[i][j] || grid[i][j] == '0') {
            return;
        }

        visited[i][j] = true;

        dfs(grid, i + 1, j, visited);
        dfs(grid, i - 1, j, visited);
        dfs(grid, i, j + 1, visited);
        dfs(grid, i, j - 1, visited);
    }

    // Solution3: Union-Find
    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }

        UnionFind uf = new UnionFind(grid);
        int row = grid.length;
        int col = grid[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    grid[i][j] = '0';
                    if (i - 1 >= 0 && grid[i - 1][j] == '1') {
                    	uf.union(i * col + j, (i - 1) * col + j);
                    }
                    if (i + 1 < row && grid[i + 1][j] == '1') {
                    	uf.union(i * col + j, (i + 1) * col + j);
                    }
                    if (j - 1 >= 0 && grid[i][j - 1] == '1') {
                    	uf.union(i * col + j, i * col + j - 1);
                    }
                    if (j + 1 < col && grid[i][j + 1] == '1') {
                    	uf.union(i * col + j, i * col + j + 1);
                    }
                }
            }
        }
        return uf.count;
    }
}

class UnionFind {
	int count = 0;
	int r;
	int c;
	int[] parent;
	int[] rank;
	public UnionFind(char[][] grid) {
		r = grid.length;
		c = grid[0].length;
		parent = new int[r * c];
		rank = new int[r * c];
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (grid[i][j] == '1') {
					parent[i * c + j] = i * c + j;
					count++;
				}
			}
		}
	}

	public int find(int i) {
		while (parent[i] != i) {
			parent[i] = parent[parent[i]];  // path compression
			i = parent[i];
		}
		return i;
	}

	public void union(int x, int y) {   // union by rank
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
			count--;
		}
	}
}