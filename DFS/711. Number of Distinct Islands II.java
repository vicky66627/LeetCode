/*
Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Count the number of distinct islands. An island is considered to be the same as another if they have the same shape, or have the same shape after rotation (90, 180, or 270 degrees only) or reflection (left/right direction or up/down direction).

Example 1:

11000
10000
00001
00011

Given the above grid map, return 1.

Notice that:

11
1

and

 1
11

are considered same island shapes. Because if we make a 180 degrees clockwise rotation on the first island, then two islands will have the same shapes.

Example 2:

11100
10001
01001
01110

Given the above grid map, return 2.

Here are the two distinct islands:

111
1

and

1
1


Notice that:

111
1

and

1
111

are considered same island shapes. Because if we flip the first array in the up/down direction, then they have the same shapes.

Note: The length of each dimension in the given grid does not exceed 50. 
*/

public class Solution {
	public static void main(String[] args) {
		int[][] grid1 = new int[][]{{1,1,0,0,0}, {1,0,0,0,0}, {0,0,0,0,1}, {0,0,0,1,1}};
		int[][] grid2 = new int[][]{{1,1,0,1,1}, {1,0,0,0,0}, {0,0,0,0,1}, {1,1,0,1,1}};
		int[][] grid3 = new int[][]{{1,1,0,0}, {0,0,1,1}, {1,1,0,0}, {0,0,1,1}};
		int[][] grid4 = new int[][]{{1,1,1,1}, {1,0,1,0}, {0,0,0,0}, {1,1,1,1}, {0,1,0,1}};
		int[][] grid5 = new int[][]{{0,0,1}, {1,0,1}, {1,0,1}};
		int[][] grid6 = new int[][]{{1,1,0}, {0,0,1}, {0,1,0}, {1,0,0}, {0,1,1}};
        int[][] grid7 = new int[][]{{1,1,1,0,0}, {1,0,0,0,1}, {0,1,0,0,1}, {0,1,1,1,0}};

		System.out.println(numDistinctIslands2(grid1));
		// output: 1
		System.out.println(numDistinctIslands2(grid2));
		// output: 2
		System.out.println(numDistinctIslands2(grid3));
		// output: 1
		System.out.println(numDistinctIslands2(grid4));
		// output: 1
		System.out.println(numDistinctIslands2(grid5));
		// output: 2
		System.out.println(numDistinctIslands2(grid6));
		// output: 2
        System.out.println(numDistinctIslands2(grid7));
        // output: 2
	}

    // The 8 rotations and reflections of each point are (x, y), (-x, y), (x, -y), (-x, -y), (y, x), (-y, x), (y, -x), (-y, -x)
    public static int numDistinctIslands2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
        	return 0;
        }

        int row = grid.length;
        int col = grid[0].length;
        Set<String> shapes = new HashSet<>();

        for (int i = 0; i < row; i++) {
        	for (int j = 0; j < col; j++) {
        		if (grid[i][j] == 1) {
        			List<int[]> shape = new ArrayList<>();
        			explore(grid, i, j, shape);
        			shapes.add(norm(shape));
        		}
        	}
        }
        return shapes.size();
    }

    private static void explore(int[][] grid, int r, int c, List<int[]> shape) {
    	if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] != 1) {
    		return;
    	}

    	grid[r][c] = 0;
    	shape.add(new int[]{r, c});
    	explore(grid, r + 1, c, shape);
    	explore(grid, r - 1, c, shape);
    	explore(grid, r, c + 1, shape);
    	explore(grid, r, c - 1, shape);
    }

    private static String norm(List<int[]> shape) {
        List<String> forms = new ArrayList<>();
        // generate the 8 different transformations
        // (x, y), (x, -y), (-x, y), (-x, -y)
        // (y, x), (-y, x), (y, -x), (-y, -x)
        int[][] trans = new int[][]{{1,1}, {1,-1}, {-1,1}, {-1,-1}};
        for (int[] ts : trans) {
            List<int[]> list1 = new ArrayList<>();
            List<int[]> list2 = new ArrayList<>();
            for (int[] cell : shape) {
                list1.add(new int[]{cell[0] * ts[0], cell[1] * ts[1]});
                list2.add(new int[]{cell[1] * ts[0], cell[0] * ts[1]});
            }
            forms.add(getKey(list1));
            forms.add(getKey(list2));
        }
        Collections.sort(forms);
        return forms.get(0);
    }

    private static String getKey(List<int[]> shape) {
        Collections.sort(shape, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int x = shape.get(0)[0], y = shape.get(0)[1];
        StringBuilder res = new StringBuilder();
        for (int[] cell : shape) {
            res.append((cell[0] - x) + ":" + (cell[1] - y) + ":");
        }
        return res.toString();
    }
}