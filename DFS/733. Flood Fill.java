public class Solution {
	public static void main(String[] args) {
        int[][] image1 = new int[][]{{1,1,1},{1,1,0},{1,0,1}};
        int[][] image2 = new int[][]{{0,0,0},{0,0,0}};
        int[][] image3 = new int[][]{{0,0,0},{0,1,1}};
    
        int[][] res1 = floodFill(image1, 1, 1, 2);
        for (int[] r : res1) {
            printArr(r);
        }
        // output: [[2,2,2],[2,2,0],[2,0,1]]
    
        System.out.println();
        image1 = new int[][]{{1,1,1},{1,1,0},{1,0,1}};
        int[][] res2 = floodFill(image1, 2, 2, 2);
        for (int[] r : res2) {
            printArr(r);
        }
        // output: [[1,1,1],[1,1,0],[1,0,2]]

        System.out.println();
        int[][] res3 = floodFill(image2, 0, 0, 2);
        for (int[] r : res3) {
            printArr(r);
        }
        // output: [[2,2,2],[2,2,2]]

        System.out.println();
        int[][] res4 = floodFill(image3, 1, 1, 1);
        for (int[] r : res4) {
            printArr(r);
        }
        // output: [[0,0,0],[0,1,1]]
    }

    // Solution1: BFS
    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image.length == 0) {
            return image;
        }
        int row = image.length;
        int col = image[0].length;
        if (sr < 0 || sc < 0 || sr >= row || sc >= col || image[sr][sc] == newColor) {
        	return image;
        }
        
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc});
        int origColor = image[sr][sc];
        image[sr][sc] = newColor;
        int[][] dirs = new int[][]{{0,1}, {0,-1}, {1,0}, {-1,0}};

        while (!queue.isEmpty()) {
            int[] curt = queue.poll();
            for (int[] dir : dirs) {
                int r = curt[0] + dir[0];
                int c = curt[1] + dir[1];
                if (r < 0 || c < 0 || r >= row || c >= col || image[r][c] != origColor) {
                    continue;
                }
                image[r][c] = newColor;
                queue.offer(new int[]{r, c});
            }
        }
        return image;
    }

    // Solution2: DFS
    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image.length == 0) {
            return image;
        }

        int row = image.length;
        int col = image[0].length;
        if (sr < 0 || sc < 0 || sr >= row || sc >= col || image[sr][sc] == newColor) {
        	return image;
        }

        int origColor = image[sr][sc];

        dfs(image, sr, sc, newColor, origColor);
        return image;
    }

    private static void dfs(int[][] image, int i, int j, int newColor, int origColor) {
    	if (i < 0 || j < 0 || i >= image.length || j >= image[0].length || image[i][j] != origColor) {
    		return;
    	}

    	image[i][j] = newColor;
    	dfs(image, i + 1, j, newColor, origColor);
    	dfs(image, i - 1, j, newColor, origColor);
    	dfs(image, i, j + 1, newColor, origColor);
    	dfs(image, i, j - 1, newColor, origColor);
    }

    private static void printArr(int[] arr) {
        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}