public class Solution {
	public static void main(String[] args) {
		int[][] pre1 = new int[][]{{1,0}};
		int[][] pre2 = new int[][]{{1,0}, {0,1}};
		int[][] pre3 = new int[][]{{1,0}, {2,1}, {3,2}, {4,3}};
		int[][] pre4 = new int[][]{{1,0}, {2,1}, {3,2}, {1,4}};
		int[][] pre5 = new int[][]{{0,1}, {2,1}, {3,0}, {1,3}};
		int[][] pre6 = new int[][]{{0,1}, {3,1}, {1,3}, {3,2}};
		int[][] pre7 = new int[][]{{1,0}, {2,0}, {3,1}, {3,2}};

		System.out.println(canFinish(2, pre1));
		// output: true
		System.out.println(canFinish(2, pre2));
		// output: false
		System.out.println(canFinish(5, pre3));
		// output: true
		System.out.println(canFinish(5, pre4));
		// output: true
		System.out.println(canFinish(4, pre5));
		// output: false
		System.out.println(canFinish(4, pre6));
		// output: false
		System.out.println(canFinish(4, pre7));
		// output: true
	}

	// Solution1: BFS
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
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

        while (!queue.isEmpty()) {
            int course = queue.poll();
            for (int next : edges[course]) {
                if (--indegrees[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        for (int degree : indegrees) {
        	if (degree != 0) {
        		return false;
        	}
        }

        return true;
    }

    // Solution2: DFS
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] pre : prerequisites) {
            graph[pre[1]].add(pre[0]);
        }

        boolean[] visited = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (hasCycle(graph, i, visited, new boolean[numCourses])) {
                return false;
            }
        }

        return true;
    }

    private static boolean hasCycle(List<Integer>[] graph, int course, boolean[] visited, boolean[] onpath) {
        if (visited[course]) {
            return false;
        }

        onpath[course] = visited[course] = true;
        for (int next : graph[course]) {
            if (onpath[next] || hasCycle(graph, next, visited, onpath)) {
                return true;
            }
        }
        onpath[course] = false;

        return false;
    }
}