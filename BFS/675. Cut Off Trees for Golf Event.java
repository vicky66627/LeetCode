public class Solution {
	public static void main(String[] args) {
		List<Integer> l1 = Arrays.asList(new Integer[]{1,2,3});
		List<Integer> l2 = Arrays.asList(new Integer[]{0,0,4});
		List<Integer> l3 = Arrays.asList(new Integer[]{7,6,5});
		List<Integer> l4 = Arrays.asList(new Integer[]{0,0,0});

		List<Integer> l5 = Arrays.asList(new Integer[]{2,3,4});
		List<Integer> l6 = Arrays.asList(new Integer[]{0,0,5});
		List<Integer> l7 = Arrays.asList(new Integer[]{8,7,6});

		List<Integer> l8 = Arrays.asList(new Integer[]{0,0,5});
		List<Integer> l9 = Arrays.asList(new Integer[]{7,6,4});

		List<Integer> l10 = Arrays.asList(new Integer[]{4,5,6});
		List<Integer> l11 = Arrays.asList(new Integer[]{7,8,9});

		List<List<Integer>> f1 = new ArrayList<>();
		f1.add(l1);
		f1.add(l2);
		f1.add(l3);

		List<List<Integer>> f2 = new ArrayList<>();
		f2.add(l1);
		f2.add(l4);
		f2.add(l3);

		List<List<Integer>> f3 = new ArrayList<>();
		f3.add(l5);
		f3.add(l6);
		f3.add(l7);

		List<List<Integer>> f4 = new ArrayList<>();
		f4.add(l1);
		f4.add(l8);
		f4.add(l9);

		List<List<Integer>> f5 = new ArrayList<>();
		f5.add(l1);
		f5.add(l10);
		f5.add(l11);

		System.out.println(cutOffTree(f1));
		// output: 6
		System.out.println(cutOffTree(f2));
		// output: -1
		System.out.println(cutOffTree(f3));
		// output: 6
		System.out.println(cutOffTree(f4));
		// output: 8
		System.out.println(cutOffTree(f5));
		// output: 12
	}

    public static int cutOffTree(List<List<Integer>> forest) {
        if (forest == null || forest.size() == 0 || forest.get(0) == null || forest.get(0).size() == 0) {
            return 0;
        }

        int row = forest.size();
        int col = forest.get(0).size();
        List<Tree> trees = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int height = forest.get(i).get(j);
                if (height > 1) {
                    trees.add(new Tree(i, j, height));
                }
            }
        }

        Collections.sort(trees);

        int res = 0, sr = 0, sc = 0;

        for (Tree tree : trees) {
            int dis = dist(forest, sr, sc, tree.row, tree.col);
            if (dis < 0) {
                return -1;
            }
            res += dis;
            sr = tree.row;
            sc = tree.col;
        }

        return res;
    }

    private static int dist(List<List<Integer>> forest, int sr, int sc, int er, int ec) {
        int row = forest.size();
        int col = forest.get(0).size();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc, 0});
        int[][] dirs = new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}};
        boolean[][] visited = new boolean[row][col];
        visited[sr][sc] = true;

        while (!queue.isEmpty()) {
            int[] curt = queue.poll();
            if (curt[0] == er && curt[1] == ec) {
                return curt[2];    // curt[2] means distance from last point to current point
            }
            for (int[] dir : dirs) {
                int r = curt[0] + dir[0];
                int c = curt[1] + dir[1];
                if (r < 0 || c < 0 || r >= row || c >= col || visited[r][c] || forest.get(r).get(c) == 0) {
                    continue;
                }
                visited[r][c] = true;
                queue.offer(new int[]{r, c, curt[2] + 1});
            }
        }
        return -1;
    }
}

class Tree implements Comparable<Tree> {
    int row;
    int col;
    int height;
    public Tree(int row, int col, int height) {
        this.row = row;
        this.col = col;
        this.height = height;
    }

    public int compareTo(Tree that) {
        return this.height - that.height;
    }
}