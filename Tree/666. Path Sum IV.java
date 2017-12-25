public class Solution {
	public static void main(String[] args) {
		int[] nums1 = new int[]{113, 215, 221};
		int[] nums2 = new int[]{113, 221};
		int[] nums3 = new int[]{113, 218, 224, 317, 321, 342};

		System.out.println(pathSum(nums1));
		// output: 12 - (3 + 5) + (3 + 1)
		System.out.println(pathSum(nums2));
		// output: 4 - (3 + 1)
		System.out.println(pathSum(nums3));
		// output: 39 - (3 + 8 + 7) + (3 + 8 + 1) + (3 + 4 + 2)
	}

    public static int pathSum(int[] nums) {
        if (nums == null || nums.length == 0) {
        	return 0;
        }
        Map<Integer, Integer> values = new HashMap<>();

        for (int num : nums) {
        	values.put(num / 10, num % 10);
        }

        int[] res = new int[1];
        dfs(nums[0] / 10, 0, values, res);
        return res[0];
    }

    private static void dfs(int pos, int sum, Map<Integer, Integer> values, int[] res) {
    	if (!values.containsKey(pos)) {
    		return;
    	}

    	sum += values.get(pos);

    	int lvl = pos / 10;
    	int offset = pos % 10;
    	int lChild = (lvl + 1) * 10 + 2 * offset - 1;
    	int rChild = (lvl + 1) * 10 + 2 * offset;

    	if (!values.containsKey(lChild) && !values.containsKey(rChild)) {
    		res[0] += sum;
    		return;
    	}

    	dfs(lChild, sum, values, res);
    	dfs(rChild, sum, values, res);
    }
}