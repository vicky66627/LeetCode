public class Solution {
	public static void main(String[] args) {
		System.out.println(numSquares(1));
		// output: 1
		System.out.println(numSquares(2));
		// output: 2
		System.out.println(numSquares(3));
		// output: 3
		System.out.println(numSquares(4));
		// output: 1
		System.out.println(numSquares(5));
		// output: 2
		System.out.println(numSquares(6));
		// output: 3
		System.out.println(numSquares(7));
		// output: 4
		System.out.println(numSquares(8));
		// output: 2
		System.out.println(numSquares(9));
		// output: 1
		System.out.println(numSquares(10));
		// output: 2
		System.out.println(numSquares(11));
		// output: 3
		System.out.println(numSquares(12));
		// output: 3
		System.out.println(numSquares(13));
		// output: 2
		System.out.println(numSquares(14));
		// output: 3
		System.out.println(numSquares(15));
		// output: 4
		System.out.println(numSquares(16));
		// output: 1
		System.out.println(numSquares(17));
		// output: 2
		System.out.println(numSquares(18));
		// output: 2
		System.out.println(numSquares(19));
		// output: 3
		System.out.println(numSquares(20));
		// output: 2
		System.out.println(numSquares(77));
		// output: 3
		System.out.println(numSquares(100));
		// output: 1
	}

	// Solution1: BFS
    public static int numSquares(int n) {
        if (n <= 0) {
        	return 0;
        }

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(0);
        int depth = 0;

        while (!queue.isEmpty()) {
        	int size = queue.size();
        	depth++;
        	while (size-- > 0) {
        	int curt = queue.poll();
	        	for (int i = 1; i * i <= n; i++) {
	        		int next = curt + i * i;
	        		if (next == n) {
	        			return depth;
	        		}
	        		if (next > n) {
	        			break;
	        		}
	        		if (visited.add(next)) {
	        			queue.offer(next);
	        		}
	        	}
	        }
        }
        return depth;
    }

    // Solution2: DP
    public static int numSquares(int n) {
        if (n <= 0) {
            return 0;
        }

        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        return dp[n];
    }
}