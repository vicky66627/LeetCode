public class Solution {
	public static void main(String[] args) {
		int[][] edges1 = new int[][]{{1,2}, {1,3}, {2,3}};
		int[][] edges2 = new int[][]{{1,2}, {2,3}, {3,4}, {1,4}, {1,5}};

		printArr(findRedundantConnection(edges1));
		// output: [2,3]
		printArr(findRedundantConnection(edges2));
		// output: [1,4]
	}

    public static int[] findRedundantConnection(int[][] edges) {
        int[] res = new int[2];
        if (edges == null || edges.length == 0 || edges[0] == null || edges[0].length == 0) {
        	return res;
        }

        int n = edges.size();
        int[] parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
        	parent[i] = i;
        }

        for (int edge : edges) {
        	int x = find(parent, edge[0]);
        	int y = find(parent, edge[1]);
        	if (x == y) {
        		res[0] = edge[0];
        		res[1] = edge[1];
        		return res;
        	}
        }
        return res;
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