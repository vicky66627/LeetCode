public class Solution {
	public static void main(String[] args) {
		int[] nums1 = new int[]{1, 1, 1, 1, 1};
		int[] nums2 = new int[]{1, 2, 3, 4, 5};

		System.out.println(findTargetSumWays(nums1, 3));
		// output: 5
		System.out.println(findTargetSumWays(nums1, 2));
		// output: 0
		System.out.println(findTargetSumWays(nums2, 3));
		// output: 3
		System.out.println(findTargetSumWays(nums2, 5));
		// output: 3
		System.out.println(findTargetSumWays(nums2, 10));
		// output: 0
		System.out.println(findTargetSumWays(nums2, 11));
		// output: 1
	}

	// A + B = sum, A - B = target => 2A = sum + target => (sum + target) % 2 == 0	
    public static int findTargetSumWays(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
        	return 0;
        }

        int sum = 0;
        for (int num : nums) {
        	sum += num;
        }
        if (Math.abs(sum) < Math.abs(target) || (sum + target) % 2 == 1) {
        	return 0;
        }

        // return findWays(nums, 0, 0, target);
        // return findWays(nums, 0, 0, target, new HashMap<>());
        return subsetSum(nums, (sum + target) / 2);
    }

    // Solution1: DFS - O(2^n) time, O(n) space (recursion stack)
    private static int findWays(int[] nums, int curtIdx, int curtSum, int target) {
    	int res = 0;
    	if (curtIdx == nums.length) {
    		if (curtSum == target) {
    			res += 1;
    		}
    		return res;
    	}

    	res += findWays(nums, curtIdx + 1, curtSum + nums[curtIdx], target);
    	res += findWays(nums, curtIdx + 1, curtSum - nums[curtIdx], target);

    	return res;
    }

    // Solution2: DFS with memoization
    private static int findWays(int[] nums, int curtIdx, int curtSum, int target, Map<String, Integer> map) {
    	String encodeStr = curtIdx + "->" + curtSum;
    	if (map.containsKey(encodeStr)) {
    		return map.get(encodeStr);
    	}

    	int res = 0;
    	if (curtIdx == nums.length) {
    		if (curtSum == target) {
    			res += 1;
    		}
    		return res;
    	}

    	res += findWays(nums, curtIdx + 1, curtSum + nums[curtIdx], target);
    	res += findWays(nums, curtIdx + 1, curtSum - nums[curtIdx], target);
    	map.put(encodeStr, res);

    	return res;
    }

    // Solution3: DP - 0-1knapsack: O(n * m) time, O(m) space. n is the array size of nums, m is target
    private static int subsetSum(int[] nums, int target) {
    	int[] dp = new int[target + 1];
    	dp[0] = 1;
    	for (int num : nums) {
    		for (int j = target; j >= num; j--) {
    			dp[j] += dp[j - num];
    		}
    	}
    	return dp[target];
    }
}