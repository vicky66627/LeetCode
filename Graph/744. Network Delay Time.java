public class Solution {
	public static void main(String[] args) {
		int[][] times1 = new int[][]{{2,1,1}, {2,3,1}, {3,4,1}};
		int[][] times2 = new int[][]{{1,2,2}, {1,3,4}, {2,3,3}};
		int[][] times3 = new int[][]{{1,2,2}, {1,3,7}, {2,3,3}};

		System.out.println(networkDelayTime(times1, 4, 2));
		// output: 2
		System.out.println(networkDelayTime(times2, 3, 1));
		// output: 4
		System.out.println(networkDelayTime(times2, 3, 2));
		// output: -1
		System.out.println(networkDelayTime(times3, 3, 1));
		// output: 5
	}

	// shortest path - Dijkstra: O(ElogE) time, O(E) space
    public static int networkDelayTime(int[][] times, int N, int K) {
        if (times == null || times.length == 0) {
        	return 0;
        }

        Map<Integer, List<Node>> graph = new HashMap<>();
        for (int[] edge : times) {
        	graph.putIfAbsent(edge[0], new ArrayList<>());
        	graph.get(edge[0]).add(new Node(edge[1], edge[2]));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(K, 0));

        Map<Integer, Integer> dist = new HashMap<>();
        while (!pq.isEmpty()) {
            Node curt = pq.poll();
            if (dist.containsKey(curt.node)) {
                continue;
            }
            dist.put(curt.node, curt.weight);
            if (graph.containsKey(curt.node)) {
                for (Node next : graph.get(curt.node)) {
                    if (!dist.containsKey(next.node)) {
                        pq.offer(new Node(next.node, next.weight + curt.weight));
                    }
                }
            }
        }

        if (dist.size() != N) {
        	return -1;
        }

        int res = 0;
        for (int val : dist.values()) {
        	res = Math.max(val, res);
        }

        return res;
    }
}

class Node implements Comparable<Node> {
	int node;
	int weight;
	public Node(int node, int weight) {
		this.node = node;
		this.weight = weight;
	}

	public int compareTo(Node that) {
		return this.weight - that.weight;
	}
}