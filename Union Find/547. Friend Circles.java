public class Solution {
	public static void main(String[] args) {
		int[][] m1 = new int[][]{{1,1,0}, {1,1,0}, {0,0,1}};
        int[][] m2 = new int[][]{{1,1,0}, {1,1,1}, {0,1,1}};
        int[][] m3 = new int[][]{{1,1,0,0}, {1,1,0,0}, {0,0,1,1}, {0,0,1,1}};
        int[][] m4 = new int[][]{{1,1,0,0,0}, {1,1,0,0,0}, {0,0,1,0,0}, {0,0,0,1,1}, {0,0,0,1,1}};
        int[][] m5 = new int[][]{{1,0,0,1}, {0,1,1,0}, {0,1,1,1}, {1,0,1,1}};
        int[][] m6 = new int[][]{{1,1,1}, {1,1,1}, {1,1,1}};
        
        System.out.println(findCircleNum(m1));
        // output: 2
        System.out.println(findCircleNum(m2));
        // output: 1
        System.out.println(findCircleNum(m3));
        // output: 2
        System.out.println(findCircleNum(m4));
        // output: 3
        System.out.println(findCircleNum(m5));
        // output: 1
        System.out.println(findCircleNum(m6));
        // output: 1
	}

	// Solution1: DFS
    public static int findCircleNum(int[][] M) {
        if (M == null || M.length == 0) {
            return 0;
        }
        int n = M.length;
        int cnt = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                cnt++;
                dfs(M, i, visited);
            }
        }
        return cnt;
    }

    private static void dfs(int[][] M, int i, boolean[] visited) {
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(M, j, visited);
            }
        }
    }

    // Solution2: Union-Find
    public static int findCircleNum(int[][] M) {
        if (M == null || M.length == 0) {
            return 0;
        }
        int n = M.length;
        int cnt = n;
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (M[i][j] == 1 && union(parent, i, j)) {
                    cnt--;
                }
            }
        }
        return cnt;
    }

    private static int find(int[] parent, int i) {
        while (parent[i] != i) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }

    private static boolean union(int[] parent, int x, int y) {
        int xroot = find(parent, x);
        int yroot = find(parent, y);
        if (xroot != yroot) {
            parent[xroot] = yroot;
            return true;
        }
        return false;
    }

    // Solution3: BFS
    public static int findCircleNum(int[][] M) {
        if (M == null || M.length == 0) {
            return 0;
        }
        int n = M.length;
        int cnt = 0;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            queue.offer(i);
            while (!queue.isEmpty()) {
                int curt = queue.poll();
                visited[curt] = true;
                for (int j = 0; j < n; j++) {
                    if (M[curt][j] == 1 && !visited[j]) {
                        queue.offer(j);
                    }
                }
            }
            cnt++;
        }
        return cnt;
    }
}