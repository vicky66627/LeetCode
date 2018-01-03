/*
 Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

For example:

Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.

Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.

Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges. 
*/

public class Solution {
	// Tree: 1: the number of nodes = the number of edges + 1.   2: is a connected graph
	public static void main(String[] args) {
		int[][] edges1 = new int[][]{{0,1}, {0,2}, {0,3}, {1,4}};
		int[][] edges2 = new int[][]{{0,1}, {1,2}, {2,3}, {1,3}, {1,4}};
		int[][] edges3 = new int[][]{};
		int[][] edges4 = new int[][]{{0,1}, {1,2}, {0,2}, {3,4}};   // unconnected graph and has cycle
		int[][] edges5 = new int[][]{{0,0}, {0,1}, {0,2}, {3,4}};
		System.out.println(validTree(5, edges1));
		// output: true
		System.out.println(validTree(5, edges2));
		// output: false
		System.out.println(validTree(1, edges3));
		// output: true
		System.out.println(validTree(5, edges4));
		// output: false
		System.out.println(validTree(5, edges5));
		// output: false
	}

	// Solution1: Union-Find
    public static boolean validTree(int n, int[][] edges) {
        if (n < 1) {
        	return false;
        }

        if (edges == null || edges.length == 0) {
        	return n == 1;
        }

        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
        	parent[i] = i;
        }

        for (int[] edge : edges) {
        	if (union(parent, edge[0], edge[1])) {  // has cycle
        		return false;
        	}
        }

        return n == edges.length + 1;
    }

    private static int find(int[] parent, int i) {
    	while (parent[i] != i) {
    		parent[i] = parent[parent[i]];
    		i = parent[i];
    	}
    	return i;
    }

    private static boolean union(int[] parent, int i, int j) {
    	int x = find(parent, i);
        int y = find(parent, j);

        if (x == y) {
            return true;
        }
        parent[x] = y;
        return false;
    }

    // Solution2: DFS
    public static boolean validTree(int n, int[][] edges) {
        if (n < 1) {
        	return false;
        }

        if (edges == null || edges.length == 0) {
        	return n == 1;
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
        if (hasCycle(graph, 0, 0, visited)) {
        	return false;
        }

        for (int i = 0; i < n; i++) {
        	if (!visited[i]) {
        		return false;
        	}
        }

        return true;
    }

    private static boolean hasCycle(List<Integer>[] graph, int curt, int parent, boolean[] visited) {
    	visited[curt] = true;

        for (int next : graph[curt]) {
            if ((next != parent && visited[next]) || (!visited[next] && hasCycle(graph, next, curt, visited))) {
                return true;
            }
        }

        return false;
    }

    // Solution3: BFS
    public static boolean validTree(int n, int[][] edges) {
        if (n < 1) {
        	return false;
        }

        if (edges == null || edges.length == 0) {
        	return n == 1;
        }

        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
        	graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
        	graph[edge[0]].add(edge[1]);
        	graph[edge[1]].add(edge[0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        boolean[] visited = new boolean[n];
        
        while(!queue.isEmpty()) {
            int curt = queue.poll();
            if (visited[curt]) {
                return false;
            }
            visited[curt] = true;
            for (int next : graph[curt]) {
                if (next == curt) {
                    return false;
                }
                queue.offer(next);
                graph[next].remove(Integer.valueOf(curt));
            }
        }

        for (boolean visit : visited) {
        	if (!visit) {
        		return false;
        	}
        }

        return true;
    }
}