public class Solution {
	public static void main(String[] args) {
		String ring1 = "godding";
		String ring2 = "inggodd";
		String ring3 = "badedca";
		String ring4 = "abcac";
		String key1 = "gd";
		String key2 = "dg";
		String key3 = "ad";
		String key4 = "ac";
		String key5 = "aac";
		String key6 = "aca";

		System.out.println(findRotateSteps(ring1, key1));
		// output: 4 (1 + 2 + 1)
		System.out.println(findRotateSteps(ring1, key2));
		// output: 6 (2 + 1 + 2 + 1)
		System.out.println(findRotateSteps(ring2, key1));
		// output: 7 (2 + 1 + 3 + 1) 
		System.out.println(findRotateSteps(ring2, key2));
		// output: 6 (1 + 1 + 3 + 1)
		System.out.println(findRotateSteps(ring3, key3));
		// output: 4 (1 + 1 + 1 + 1)
		System.out.println(findRotateSteps(ring4, key4));
		// output: 3 (1 + 1 + 1)
		System.out.println(findRotateSteps(ring4, key5));
		// output: 4 (1 + 1 + 1 + 1)
		System.out.println(findRotateSteps(ring4, key6));
		// output: 5 (1 + 1 + 1 + 1 + 1)
	}

	// Solution1: DFS + memoization - fast
    public static int findRotateSteps(String ring, String key) {
        if (ring == null || ring.length() == 0 || key == null || key.length() == 0) {
       		return 0;
        }

        int n = ring.length();
        int m = key.length();
        int[][] memo = new int[m][n];
        for (int i = 0; i < m; i++) {
        	Arrays.fill(memo[i], -1);
        }

        List<Integer>[] map = new List[26];
        for (int i = 0; i < 26; i++) {
        	map[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
        	map[ring.charAt(i) - 'a'].add(i);
        }

        return findSteps(ring, key, 0, 0, map, memo) + m;
    }

    private static int findSteps(String ring, String key, int kIdx, int orig, List<Integer>[] map, int[][] memo) {
    	if (kIdx == key.length()) {
    		return 0;
    	}
    	if (memo[kIdx][orig] >= 0) {
    		return memo[kIdx][orig];
    	}

    	int min = Integer.MAX_VALUE;
    	for (int idx : map[key.charAt(kIdx) - 'a']) {
    		int step = Math.abs(idx - orig);
    		step = Math.min(step, ring.length() - step);
    		min = Math.min(min, step + findSteps(ring, key, kIdx + 1, idx, map, memo));
    	}
    	memo[kIdx][orig] = min;
    	return min;
    }

    // Solution2: DP
    public static int findRotateSteps(String ring, String key) {
        if (ring == null || ring.length() == 0 || key == null || key.length() == 0) {
       		return 0;
        }

        int n = ring.length();
        int m = key.length();
        int[][] dp = new int[m + 1][n];
        List<Integer>[] map = new List[26];
        for (int i = 0; i < 26; i++) {
        	map[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
        	map[ring.charAt(i) - 'a'].add(i);
        }
        
        for (int i = m - 1; i >= 0; i--) {
        	for (int j = 0; j < n; j++) {
        		dp[i][j] = Integer.MAX_VALUE;
       			if (!map[key.charAt(i) - 'a'].isEmpty()) {
        			for (int idx : map[key.charAt(i) - 'a']) {
        				int step = Math.abs(idx - j);
        				step = Math.min(step, n - step);
        				dp[i][j] = Math.min(dp[i][j], step + dp[i + 1][idx]);
        			}
        		}
        	}
        }
        return dp[0][0] + m;
    }
}