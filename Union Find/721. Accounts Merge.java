public class Solution {
	public static void main(String[] args) {
		List<List<String>> acc1 = new ArrayList<>();
        List<String> a1 = Arrays.asList(new String[]{"John", "johnsmith@mail.com", "john00@mail.com"});
        List<String> a2 = Arrays.asList(new String[]{"John", "johnnybravo@mail.com"});
        List<String> a3 = Arrays.asList(new String[]{"John", "johnsmith@mail.com", "john_newyork@mail.com"});
        List<String> a4 = Arrays.asList(new String[]{"Mary", "mary@mail.com"});
        acc1.add(a1);
        acc1.add(a2);
        acc1.add(a3);
        acc1.add(a4);

        List<List<String>> acc2 = new ArrayList<>();
        List<String> a5 = Arrays.asList(new String[]{"John", "johnsmith@mail.com", "john00@mail.com"});
        List<String> a6 = Arrays.asList(new String[]{"John", "johnnybravo@mail.com", "john_newyork@mail.com"});
        List<String> a7 = Arrays.asList(new String[]{"John", "john00@mail.com", "john_newyork@mail.com"});
        List<String> a8 = Arrays.asList(new String[]{"Mary", "mary@mail.com"});
        acc2.add(a5);
        acc2.add(a6);
        acc2.add(a7);
        acc2.add(a8);

		System.out.println(accountsMerge(acc1));
		// output: [["John", "john00@mail.com", "john_newyork@mail.com", "johnsmith@mail.com"],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
		System.out.println(accountsMerge(acc2));
		// output: [["John", "john00@mail.com", "john_newyork@mail.com", "johnnybravo@mail.com", "johnsmith@mail.com"], ["Mary", "mary@mail.com"]]
	}

	// Solution1: Union Find
    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
    	if (accounts == null || accounts.size() == 0) {
    		return new ArrayList<>();
    	}

    	Map<String, String> nameMap = new HashMap<>();
    	Map<String, Integer> idMap = new HashMap<>();

    	int id = 0;
    	for (List<String> account : accounts) {
    		for (int i = 0; i < account.size(); i++) {
    			String name = account.get(0);
    			if (i > 0) {
    				nameMap.put(account.get(i), name);
    				idMap.putIfAbsent(account.get(i), id++);
    			}
    		}
    	}

    	UnionFind uf = new UnionFind(id);
    	for (List<String> account : accounts) {
    		for (int i = 2; i < account.size(); i++) {
    			uf.union(idMap.get(account.get(i)), idMap.get(account.get(1)));
    		}
    	}

    	Map<Integer, List<String>> res = new HashMap<>();
    	for (String email : nameMap.keySet()) {
    		int idx = uf.find(idMap.get(email));
    		res.putIfAbsent(idx, new ArrayList<>());
    		res.get(idx).add(email);
    	}

    	for (List<String> email : res.values()) {
    		Collections.sort(email);
    		email.add(0, nameMap.get(email.get(0)));
    	}

    	return new ArrayList(res.values());
    }

    // Solution2: DFS
    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
    	if (accounts == null || accounts.size() == 0) {
    		return new ArrayList<>();
    	}

    	Map<String, String> emailToName = new HashMap<>();
    	Map<String, List<String>> graph = new HashMap<>();

    	int id = 0;
    	for (List<String> account : accounts) {
    		for (int i = 0; i < account.size(); i++) {
    			String name = account.get(0);
    			if (i > 0) {
    				emailToName.put(account.get(i), name);
    				graph.putIfAbsent(account.get(1), new ArrayList<>());
    				graph.get(account.get(1)).add(account.get(i));
    				graph.putIfAbsent(account.get(i), new ArrayList<>());
    				graph.get(account.get(i)).add(account.get(1));
    			}
    		}
    	}

    	Set<String> visited = new HashSet<>();
    	List<List<String>> res = new ArrayList<>();
    	for (String email : graph.keySet()) {
    		if (visited.add(email)) {
    			List<String> inner = new ArrayList<>();
    			dfs(graph, email, inner, visited);
    			Collections.sort(inner);
    			inner.add(0, emailToName.get(email));
    			res.add(inner);
    		}
    	}
    	return res;
    }

    private static void dfs(Map<String, List<String>> graph, String curt, List<String> inner, Set<String> visited) {
    	inner.add(curt);
    	for (String next : graph.get(curt)) {
    		if (visited.add(next)) {
    			dfs(graph, next, inner, visited);
    		}
    	}
    }
}

class UnionFind {
	int[] parent;
	int[] rank;
	public UnionFind(int n) {
		parent = new int[n];
		rank = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}
	}

	public int find(int i) {
		while (parent[i] != i) {
			parent[i] = parent[parent[i]];
			i = parent[i];
		}
		return i;
	}

	public void union(int x, int y) {
		int xroot = find(x);
		int yroot = find(y);
		if (xroot != yroot) {
			if (rank[xroot] < rank[yroot]) {
				parent[xroot] = yroot;
			} else if (rank[yroot] < rank[xroot]) {
				parent[yroot] = xroot;
			} else {
				parent[xroot] = yroot;
				rank[yroot]++;
			}
		}
	}
}