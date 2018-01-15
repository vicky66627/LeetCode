public class Solution {
	public static void main(String[] args) {
		int[] boxes1 = new int[]{1, 3, 2, 2, 2, 3, 4, 3, 1};
		int[] boxes2 = new int[]{1, 3, 2, 5, 2, 3, 4, 3, 1};
		int[] boxes3 = new int[]{1, 3, 2, 2, 3, 3, 4, 3, 1};
		int[] boxes4 = new int[]{1, 3, 2, 2, 2, 1, 3, 4, 3, 1};

		System.out.println(removeBoxes(boxes1));
		// output: 23
		System.out.println(removeBoxes(boxes2));
		// output: 19
		System.out.println(removeBoxes(boxes3));
		// output: 25
		System.out.println(removeBoxes(boxes4));
		// output: 24
	}

	// Solution1: Top-down DP
    public static int removeBoxes(int[] boxes) {
        if (boxes == null || boxes.length == 0) {
        	return 0;
        }

        int n = boxes.length;
        int[][][] dp = new int[n][n][n];
        return remove(boxes, 0, n - 1, 0, dp);
    }

    private static int remove(int[] boxes, int l, int r, int k, int[][][]dp) {
    	if (l > r) {
    		return 0;
    	}
    	if (dp[l][r][k] != 0) {
    		return dp[l][r][k];
    	}

    	// optimization: all boxes of the same color counted continuously from the first box should be grouped together
    	while (l + 1 <= r && boxes[l] == boxes[l + 1]) {
    		l++;
    		k++;
    	}

    	int res = (k + 1) * (k + 1) + remove(boxes, l + 1, r, 0, dp);

    	for (int i = l + 1; i <= r; i++) {
    		if (boxes[l] == boxes[i]) {
    			res = Math.max(res, remove(boxes, l + 1, i - 1, 0, dp) + remove(boxes, i, r, k + 1, dp));
    		}
    	}
    	dp[l][r][k] = res;
    	return res;
    }

    // Solution2: Bottom-up DP
    public static int removeBoxes(int[] boxes) {
        if (boxes == null || boxes.length == 0) {
        	return 0;
        }

        int n = boxes.length;
        int[][][] dp = new int[n][n][n];
        return remove(boxes, 0, boxes.length - 1, 0, dp);
    }
}