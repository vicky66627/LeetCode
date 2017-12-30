public class Solution {
	public static void main(String[] args) {
		int[][] pre1 = new int[][]{{1,0}};
		int[][] pre2 = new int[][]{{1,0}, {0,1}};
		int[][] pre3 = new int[][]{{1,0}, {2,1}, {3,2}, {4,3}};
		int[][] pre4 = new int[][]{{1,0}, {2,1}, {3,2}, {1,4}};
		int[][] pre5 = new int[][]{{0,1}, {2,1}, {3,0}, {1,3}};
		int[][] pre6 = new int[][]{{0,1}, {3,1}, {1,3}, {3,2}};
		int[][] pre7 = new int[][]{{1,0}, {2,0}, {3,1}, {3,2}};

		printArr(findOrder(2, pre1));
        // output: [0,1]
        printArr(findOrder(2, pre2));
        // output: []
        printArr(findOrder(5, pre3));
        // output: [0,1,2,3,4]
        printArr(findOrder(5, pre4));
        // output: [0,4,1,2,3] or [4,0,1,2,3]
        printArr(findOrder(4, pre5));
        // output: []
        printArr(findOrder(4, pre6));
        // output: []
        printArr(findOrder(4, pre7));
        // output: [0,1,2,3] or [0,2,1,3]
	}

	// Solution1: BFS
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
    	List<Integer>[] edges = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            edges[i] = new ArrayList<>();
        }

        int[] indegrees = new int[numCourses];
        for (int[] pre : prerequisites) {
            indegrees[pre[0]]++;
            edges[pre[1]].add(pre[0]);
        }


        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) {
                queue.offer(i);
            }
        }

        int[] res = new int[numCourses];
        int idx = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            res[idx++] = course;
            for (int next : edges[course]) {
                if (--indegrees[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        for (int degree : indegrees) {
        	if (degree != 0) {
        		return new int[0];
        	}
        }

        return res;
    }

    // Solution2: DFS
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] pre : prerequisites) {
            graph[pre[1]].add(pre[0]);
        }

        boolean[] visited = new boolean[numCourses];
        int[] res = new int[numCourses];
        int[] idx = new int[1];
        idx[0] = numCourses - 1;

        for (int i = 0; i < numCourses; i++) {
            if (hasCycle(graph, i, visited, new boolean[numCourses], res, idx)) {
                return new int[0];
            }
        }

        return res;
    }

    private static boolean hasCycle(List<Integer>[] graph, int course, boolean[] visited, boolean[] onpath, int[] res, int[] idx) {
        if (visited[course]) {
            return false;
        }

        onpath[course] = visited[course] = true;
        for (int next : graph[course]) {
            if (onpath[next] || hasCycle(graph, next, visited, onpath, res, idx)) {
                return true;
            }
        }
        onpath[course] = false;
        res[idx[0]--] = course;

        return false;
    }
}