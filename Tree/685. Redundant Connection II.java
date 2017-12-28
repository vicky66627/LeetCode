public class Solution {
	public static void main(String[] args) {
		int[][] edges1 = new int[][]{{1,2}, {1,3}, {2,3}};
		int[][] edges2 = new int[][]{{1,2}, {2,3}, {3,4}, {4,1}, {1,5}};
		int[][] edges3 = new int[][]{{1,2}, {2,1}};
        int[][] edges4 = new int[][]{{2,1}, {3,1}, {4,2}, {1,4}};

		printArr(findRedundantDirectedConnection(edges1));
		// output: [2,3]
		printArr(findRedundantDirectedConnection(edges2));
		// output: [4,1]
		printArr(findRedundantDirectedConnection(edges3));
		// output: [2,1]
        printArr(findRedundantDirectedConnection(edges4));
        // output: [2,1]   // return cand1 instead of cand2
	}

	//  1: If there is no loop, then either one vertex must have two parents (or no edge is redundant.) 
    //  2: If there is a loop, then either one vertex has two parents, or every vertex has one parent.
    // union-find
    public static int[] findRedundantDirectedConnection(int[][] edges) {
        if (edges == null || edges.length == 0 || edges[0] == null || edges[0].length == 0) {
            return new int[0];
        }

        int n = edges.length;
        int[] parent = new int[n + 1];
        int[] cand1 = new int[]{-1, -1};
        int[] cand2 = new int[]{-1, -1};

        // Check whether there is a node having two parents.
        for (int[] edge : edges) {
            if (parent[edge[1]] == 0) {
                parent[edge[1]] = edge[0];
            } else {
                cand1 = new int[]{parent[edge[1]], edge[1]};
                cand2 = new int[]{edge[0], edge[1]};
                edge[1] = 0;
            }
        }

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int[] edge : edges) {
            if (edge[1] == 0) {
                continue;
            }
            int paren = find(parent, edge[0]);
            int child = find(parent, edge[1]);
            if (paren == child) {
                if (cand1[0] == -1) {
                    return edge;
                } 
                return cand1;
            }
            parent[child] = paren;
        }
        
        // If there are multiple answers, return the answer that occurs last in the given 2D-array. 
        return cand2;
    }

    private static int find(int[] parent, int i) {
        // path compression
        while (parent[i] != i) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }

    private static void printArr(int[] arr) {
        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}