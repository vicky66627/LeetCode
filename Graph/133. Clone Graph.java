class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
}

 public class Solution {
 	public static void main(String[] args) {
 		UndirectedGraphNode n0 = new UndirectedGraphNode(0);
        UndirectedGraphNode n1 = new UndirectedGraphNode(1);
        UndirectedGraphNode n2 = new UndirectedGraphNode(2);

        n0.neighbors = new ArrayList<>(Arrays.asList(new UndirectedGraphNode[]{n1, n2}));
        n1.neighbors = new ArrayList<>(Arrays.asList(new UndirectedGraphNode[]{n0, n2}));
        n2.neighbors = new ArrayList<>(Arrays.asList(new UndirectedGraphNode[]{n0, n1, n2}));

        System.out.println(serializeGraph(n0));

        UndirectedGraphNode clone = cloneGraph(n0);
        System.out.println(serializeGraph(clone));
 	}

 	// Solution1: DFS
    public static UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        Map<Integer, UndirectedGraphNode> map = new HashMap<>();
        return clone(node, map);
    }

    private static UndirectedGraphNode clone(UndirectedGraphNode node, Map<Integer, UndirectedGraphNode> map) {
        if (node == null) {
            return null;
        }
        if (map.containsKey(node.label)) {
            return map.get(node.label);
        }

        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
        map.put(node.label, newNode);
        for (UndirectedGraphNode neigh : node.neighbors) {
            newNode.neighbors.add(clone(neigh, map));
        }

        return newNode;
    }

    // Solution2: BFS
    public static UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
    	if (node == null) {
            return null;
        }
        Map<Integer, UndirectedGraphNode> map = new HashMap<>();
        Queue<UndirectedGraphNode> queue = new LinkedList<>();

        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
        map.put(newNode.label, newNode);
        queue.offer(node);

        while (!queue.isEmpty()) {
            UndirectedGraphNode curt = queue.poll();
            for (UndirectedGraphNode neigh : curt.neighbors) {
                if (!map.containsKey(neigh.label)) {
                    map.put(neigh.label, new UndirectedGraphNode(neigh.label));
                    queue.offer(neigh);
                }
                map.get(curt.label).neighbors.add(map.get(neigh.label));
            }
        }

        return newNode;
    }

    private static String serializeGraph(UndirectedGraphNode node) {
    	if (node == null) {
            return "";
        }

        Set<UndirectedGraphNode> set = new HashSet<>();
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        queue.offer(node);
        set.add(node);
        StringBuilder res = new StringBuilder();

        while (!queue.isEmpty()) {
            UndirectedGraphNode curt = queue.poll();
            res.append("node" + curt.label + ":");
            for (UndirectedGraphNode neigh : curt.neighbors) {
                res.append(neigh.label + ",");
                if (!set.contains(neigh)) {
                    queue.offer(neigh);
                }
                set.add(neigh);
            }
            res.append("#");
        }
        return res.toString();
    }
}
