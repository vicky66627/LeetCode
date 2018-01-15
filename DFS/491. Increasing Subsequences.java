public class Solution {
	public static void main(String[] args) {
		int[] nums1 = new int[]{4,6,7,7};
		int[] nums2 = new int[]{7,4,6,7};
		int[] nums3 = new int[]{6,7,4,7};

		System.out.println(findSubsequences(nums1));
		// [[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
		System.out.println(findSubsequences(nums2));
		// [[7,7],[4,6],[4,6,7],[4,7],[6,7]]
		System.out.println(findSubsequences(nums3));
		// [[6,7],[6,7,7],[7,7],[4,7]]
	}

	// backtracking
    public static List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
    	if (nums == null || nums.length == 0) {
    		return res;
    	}

        backtrack(nums, new LinkedList<>(), res, 0);
        return res;
    }

    private static void backtrack(int[] nums, LinkedList<Integer> inner, List<List<Integer>> res, int start) {
    	if (inner.size() >= 2) {
    		res.add(new ArrayList<>(inner));
    	}
        
        Set<Integer> used = new HashSet<>();
    	for (int i = start; i < nums.length; i++) {
            if (used.contains(nums[i])) {
                continue;
            }
    		if (inner.isEmpty() || inner.peekLast() <= nums[i]) {
                used.add(nums[i]);
    			inner.add(nums[i]);
    			backtrack(nums, inner, res, i + 1);
    			inner.remove(inner.size() - 1);
    		}
    	}
    }
}