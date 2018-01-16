public class Solution {
	public static void main(String[] args) {
		int[] nums1 = new int[]{1,1,2,2,2};
		int[] nums2 = new int[]{3,3,3,3,4};
		int[] nums3 = new int[]{3,2,4,1,4,2};
		int[] nums4 = new int[]{3,6,4,1,2};

		System.out.println(makesquare(nums1));
		// output: true
		System.out.println(makesquare(nums2));
		// output: false
		System.out.println(makesquare(nums3));
		// output: true
		System.out.println(makesquare(nums4));
		// output: false
	}

	// Solution1: naive DFS - slow
    public static boolean makesquare(int[] nums) {
        if (nums == null || nums.length == 0) {
        	return false;
        }

        int sum = 0;
        for (int num : nums) {
        	sum += num;
        }

        if (sum % 4 != 0) {
        	return false;
        }

        return dfs(nums, new int[4], 0, sum / 4);
    }

    private static boolean dfs(int[] nums, int[] sum, int idx, int side) {
    	if (idx == nums.length) {
    		if (sum[0] == side && sum[1] == side && sum[2] == side) {
    			return true;
    		}
    		return false;
    	}

    	for (int i = 0; i < 4; i++) {
    		if (sum[i] + nums[idx] > side) {
    			continue;
    		}
    		sum[i] += nums[idx];
    		if (dfs(nums, sum, idx + 1, side)) {
    			return true;
    		}
    		sum[i] -= nums[idx];
    	}
    	return false;
    }

    // Solution2: DFS with sorting
    public static boolean makesquare(int[] nums) {
        if (nums == null || nums.length == 0) {
        	return false;
        }

        int sum = 0;
        for (int num : nums) {
        	sum += num;
        }

        if (sum % 4 != 0) {
        	return false;
        }

        Arrays.sort(nums);
        return dfs(nums, new int[4], nums.length - 1, sum / 4);
    }

    private static boolean dfs(int[] nums, int[] sum, int idx, int side) {
    	if (idx == -1) {
            return true;
        }

    	for (int i = 0; i < 4; i++) {
    		if (sum[i] + nums[idx] > side) {
    			continue;
    		}
    		sum[i] += nums[idx];
    		if (dfs(nums, sum, idx - 1, side)) {
    			return true;
    		}
    		sum[i] -= nums[idx];
    	}
    	return false;
    }
}