/*
 Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.

Example 1:

     0          3
     |          |
     1 --- 2    4

Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.

Example 2:

     0           4
     |           |
     1 --- 2 --- 3

Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.

Note:
You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges. 
*/
public class Solution {
	public static void main(String[] args) {
		int[][] edges1 = new int[][]{{0,1}, {0,2}, {0,3}, {1,4}};
		int[][] edges2 = new int[][]{{0,1}, {1,2}, {2,3}, {1,4}};
		int[][] edges3 = new int[][]{};
		int[][] edges4 = new int[][]{{0,1}, {1,2}, {3,4}};
		int[][] edges5 = new int[][]{{0,0}, {0,1}, {0,2}, {3,4}};
		int[][] edges6 = new int[][]{{0,1}, {1,2}, {0,2}, {3,4}};
		int[][] edges7 = new int[][]{{0,1}, {1,2}, {0,2}, {2,3}, {2,4}};
        System.out.println(countComponents(5, edges1));
        // output: 1
        System.out.println(countComponents(5, edges2));
        // output: 1
        System.out.println(countComponents(7, edges3));
        // output: 7
        System.out.println(countComponents(5, edges4));
        // output: 2
        System.out.println(countComponents(5, edges5));
        // output: 2
        System.out.println(countComponents(5, edges6));
        // output: 2
        System.out.println(countComponents(5, edges7));
        // output: 1
	}

	// Solution1: Union-Find
    public static int countComponents(int n, int[][] edges) {
        if (edges == null || edges.length == 0) {
        	return n;
        }

        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
        	parent[i] = i;
        }

        int cnt = n;
        for (int[] edge : edges) {
        	int x = find(parent, edge[0]);
        	int y = find(parent, edge[1]);
        	if (x != y) {
        		cnt--;
        		parent[x] = y;
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

    // Solution2: DFS
    public static int countComponents(int n, int[][] edges) {
        if (edges == null || edges.length == 0) {
        	return n;
        }

        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
        	graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
        	graph[edge[0]].add(edge[1]);
        	graph[edge[1]].add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
        	if (visited[i]) {
        		continue;
        	}
        	cnt++;
        	dfs(graph, i, -1, visited);
        }

        return cnt;
    }

    private static void dfs(List<Integer>[] graph, int curt, int parent, boolean[] visited) {
    	visited[curt] = true;
    	for (int next : graph[curt]) {
    		if (next != parent && visited[next]) {
                continue;
            }
            if (!visited[next]) {
                dfs(graph, next, curt, visited);
            }
    	}
    }

    // Solution3: BFS
    public static int countComponents(int n, int[][] edges) {
        if (edges == null || edges.length == 0) {
        	return n;
        }

        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
        	graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
        	graph[edge[0]].add(edge[1]);
        	graph[edge[1]].add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int cnt = 0;

        for (int i = 0; i < n; i++) {
        	if (visited[i]) {
        		continue;
        	}
        	cnt++;
        	Queue<Integer> queue = new LinkedList<>();
        	queue.offer(i);
        	visited[i] = true;
        	while (!queue.isEmpty()) {
        		int curt = queue.poll();
        		for (int next : graph[curt]) {
        			if (!visited[next]) {
        				queue.offer(next);
        				visited[next] = true;
        			}
        		}
        	}
        }

        return cnt;
    }
}