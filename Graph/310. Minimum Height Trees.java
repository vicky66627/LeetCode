public class Solution {
	public static void main(String[] args) {
        int[][] edges1 = new int[][]{{1,0}, {1,2}, {1,3}};
        int[][] edges2 = new int[][]{{0,3}, {1,3}, {2,3}, {4,3}, {5,4}};
        int[][] edges3 = new int[][]{{1,0}};

        System.out.println(findMinHeightTrees(4, edges1));
        // output: [1]
        System.out.println(findMinHeightTrees(6, edges2));
        // output: [3,4]
        System.out.println(findMinHeightTrees(2, edges3));
        // output: [0,1]
    }

    public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            return new ArrayList<Integer>(Arrays.asList(new Integer[]{0}));
        }
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (graph[i].size() == 1) {
                leaves.add(i);
            }
        }
        
        while (n > 2) {
            n -= leaves.size();
            List<Integer> newLeaves = new ArrayList<>();
            for (int leaf : leaves) {
                int next = graph[leaf].get(0);
                graph[next].remove(Integer.valueOf(leaf));   // remove by object instead of by index
                if (graph[next].size() == 1) {
                    newLeaves.add(next);
                }
            }
            leaves = newLeaves;
        }
        return leaves;
    }
}