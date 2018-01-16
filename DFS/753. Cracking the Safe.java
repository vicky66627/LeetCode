public class Solution {
	public static void main(String[] args) {
		System.out.println(crackSafe(1, 2));
		// output: "01" or "10"
		System.out.println(crackSafe(2, 2));
		// output: "00110" or "01100", "10011", "11001"...
		System.out.println(crackSafe(3, 2));
		// output: "0011101000"
		System.out.println(crackSafe(2, 3));
		// output: "0221120100"
	}

	// Solution1: Hierholzer's Algorithm
    public static String crackSafe(int n, int k) {
        if (n == 1 && k == 1) {
        	return "0";
        }
        Set<String> visited = new HashSet<>();
        StringBuilder res = new StringBuilder();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n - 1; i++) {
        	sb.append("0");
        }
        String start = sb.toString();
        dfs(start, k, visited, res);
        return res.toString();
    }

    private static void dfs(String node, int k, Set<String> visited, StringBuilder res) {
    	for (int i = 0; i < k; i++) {
    		String neigh = node + i;
    		if (visited.add(neigh)) {
    			dfs(neigh.substring(1), k, visited, res);
    			res.append(i);
    		}
    	}
    }
}