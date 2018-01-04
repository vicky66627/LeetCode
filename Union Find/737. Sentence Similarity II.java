/*
Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.

For example, words1 = ["great", "acting", "skills"] and words2 = ["fine", "drama", "talent"] are similar, if the similar word pairs are pairs = [["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].

Note that the similarity relation is transitive. For example, if "great" and "good" are similar, and "fine" and "good" are similar, then "great" and "fine" are similar.

Similarity is also symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.

Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.

Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].

Note:
The length of words1 and words2 will not exceed 1000.
The length of pairs will not exceed 2000.
The length of each pairs[i] will be 2.
The length of each words[i] and pairs[i][j] will be in the range [1, 20].
*/

public class Solution {
	public static void main(String[] args) {
		String[] w1 = new String[]{"great", "acting", "skills"};
		String[] w2 = new String[]{"fine", "drama", "talent"};
		String[][] pairs1 = new String[][]{{"great", "good"}, {"fine", "good"}, {"acting", "drama"}, {"skills", "talent"}};

		String[] w3 = new String[]{"great", "acting", "skills"};
		String[] w4 = new String[]{"great", "drama", "talent"};
		String[][] pairs2 = new String[][]{{"acting", "drama"}, {"skills", "talent"}};

		String[] w5 = new String[]{"great", "acting", "skills"};
		String[] w6 = new String[]{"fine", "drama", "talent"};
		String[][] pairs3 = new String[][]{{"great", "good"}, {"fine", "good"}, {"acting", "a"}, {"b", "a"}, {"drama", "b"}, {"skills", "talent"}};

		String[] w7 = new String[]{"great", "acting"};
		String[] w8 = new String[]{"fine", "drama", "talent"};
		String[][] pairs4 = new String[][]{{"great", "fine"}, {"acting", "drama"}, {"skills", "talent"}};

		System.out.println(areSentencesSimilarTwo(w1, w2, pairs1));
		// output: true
		System.out.println(areSentencesSimilarTwo(w3, w4, pairs2));
		// output: true
		System.out.println(areSentencesSimilarTwo(w5, w6, pairs3));
		// output: true
		System.out.println(areSentencesSimilarTwo(w7, w8, pairs4));
		// output: false
	}

	// Solution1: Union-Find - O((n + p)logp) time, O(p) space, n is the size of words1 (words2), p is the size of pairs
    public static boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if ((words1 == null || words1.length == 0) && (words2 == null || words2.length == 0)) {
        	return true;
        }

        if (words1.length != words2.length) {
        	return false;
        }
        Map<String, Integer> map = new HashMap<>();
        int count = 0;
        UnionFind uf = new UnionFind(2 * pairs.length);
        for (String[] pair : pairs) {
        	for (String p : pair) {
        		map.putIfAbsent(p, count++);
        	}
        	uf.union(map.get(pair[0]), map.get(pair[1]));
        }

        int len = words1.length;
        for (int i = 0; i < len; i++) {
        	if (words1[i].equals(words2[i])) {
        		continue;
        	}
        	if (!map.containsKey(words1[i]) || !map.containsKey(words2[i]) 
        		|| uf.find(map.get(words1[i])) != uf.find(map.get(words2[i]))) {
        		return false;
        	}
        }

        return true;
    }

    // Solution2: DFS - O(np) time, O(p) space - n is the size of words1 (words2), p is the size of pairs
    public static boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if ((words1 == null || words1.length == 0) && (words2 == null || words2.length == 0)) {
        	return true;
        }

        if (words1.length != words2.length) {
        	return false;
        }
        
        Map<String, Set<String>> graph = new HashMap<>();
        for (String[] pair : pairs) {
            graph.putIfAbsent(pair[0], new HashSet<>());
            graph.get(pair[0]).add(pair[1]);
            graph.putIfAbsent(pair[1], new HashSet<>());
            graph.get(pair[1]).add(pair[0]);
        }

        int len = words1.length;
        for (int i = 0; i < len; i++) {
        	if (words1[i].equals(words2[i])) {
	    		continue;
	    	}
	    	if (!graph.containsKey(words1[i]) || !graph.containsKey(words2[i])) {
	    		return false;
	    	}
        	if (!match(graph, words1[i], words2[i], new HashSet<>())) {
        		return false;
        	}
        }

        return true;
    }

    private static boolean match(Map<String, Set<String>> graph, String w1, String w2, Set<String> visited) {
    	if (graph.get(w2).contains(w1)) {
    		return true;
    	}

    	visited.add(w2);
    	for (String next : graph.get(w2)) {
    		if (!visited.contains(next) && match(graph, w1, next, visited)) {
    			return true;
    		}
    	}

    	return false;
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