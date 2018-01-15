public class Solution {
	public static void main(String[] args) {
		String s1 = "aaabbb";
		String s2 = "aba";
		String s3 = "aaacccdebbb";
		String s4 = "abcabcabccba";
		String s5 = "aaaaaaaaaaaaaaaaaaaaa";

		System.out.println(strangePrinter(s1));
		// output: 2
		System.out.println(strangePrinter(s2));
		// output: 2
		System.out.println(strangePrinter(s3));
		// output: 5
		System.out.println(strangePrinter(s4));
		// output: 7
		System.out.println(strangePrinter(s5));
		// output: 1
	}

    public static int strangePrinter(String s) {
        if (s == null || s.length() == 0) {
        	return 0;
        }

        int n = s.length();
        int[][] dp = new int[n][n];
        return compute(s, 0, n - 1, dp);
    }

    private static int compute(String s, int l, int r, int[][] dp) {
    	if (l > r) {
    		return 0;
    	}
    	if (dp[l][r] != 0) {
    		return dp[l][r];
    	}

    	while (l + 1 <= r && s.charAt(l) == s.charAt(l + 1)) {
    		l++;
    	}

    	int res = 1 + compute(s, l + 1, r, dp);
    	for (int i = l + 1; i <= r; i++) {
    		if (s.charAt(l) == s.charAt(i)) {
    			res = Math.min(res, compute(s, l, i - 1, dp) + compute(s, i + 1, r, dp));
    			// res = Math.min(res, compute(s, l + 1, i - 1, dp) + compute(s, i, r, dp));
    		}
    	}
    	dp[l][r] = res;
    	return res;
    }
}