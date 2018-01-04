public class Solution {
	public static void main(String[] args) {
		int[] nums1 = new int[]{100, 4, 200, 1, 3, 2};
		int[] nums2 = new int[]{100, 105, 1, 4, 5, 3, 103, 101, 102};
		int[] nums3 = new int[]{-1, 5, 6, 7, 1, 3, 0, 2};
		int[] nums4 = new int[]{400, 401, -5, -3, -2, 403, 402, -4, -1};
		int[] nums5 = new int[]{1, 2, 0, 1};

		System.out.println(longestConsecutive(nums1));
		// output: 4
		System.out.println(longestConsecutive(nums2));
		// output: 4
		System.out.println(longestConsecutive(nums3));
		// output: 5
		System.out.println(longestConsecutive(nums4));
		// output: 5
		System.out.println(longestConsecutive(nums5));
		// output: 3
	}

	// Solution1: use HashMap - O(n) time, O(n) space
    public static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
        	return 0;
        }

        Map<Integer, Integer> map = new HashMap<>();
        int left, right, sum, res = 0;
        for (int num : nums) {
        	if (map.containsKey(num)) {
                continue;
            }
        	left = map.getOrDefault(num - 1, 0);
        	right = map.getOrDefault(num + 1, 0);

        	sum = left + right + 1;

        	res = Math.max(res, sum);

        	map.put(num, sum);
        	map.put(num - left, sum);
        	map.put(num + right, sum);
        }

        return res;
    }

    // Solution2: Union-Find
    public static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
        	return 0;
        }

        Map<Integer, Integer> map = new HashMap<>();
        UnionFind uf = new UnionFind(nums.length);

        for (int i = 0; i < nums.length; i++) {
        	if (map.containsKey(nums[i])) {
        		continue;
        	}	
        	map.put(nums[i], i);

        	if (map.containsKey(nums[i] - 1)) {
        		uf.union(i, map.get(nums[i] - 1));
        	}
        	if (map.containsKey(nums[i] + 1)) {
        		uf.union(i, map.get(nums[i] + 1));
        	}
        }

        return uf.maxUnion();
    }

    // Solution3: Sort - O(nlogn) time, O(1) space
    public static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
        	return 0;
        }

        Arrays.sort(nums);
        int curtIdx = 0, max = 0, count = 1;

        for (int i = 1; i < nums.length; i++) {
        	if (nums[curtIdx] + 1 == nums[i]) {
        		curtIdx = i;
        		count++;
        	} else if (nums[curtIdx] != nums[i]) {
        		max = Math.max(max, count);
        		count = 1;
        		curtIdx = i;
        	}
        }

        max = Math.max(max, count);
        return max;
    }
}

class UnionFind {
	private int[] parent;
	private int[] rank;
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

	// return the maximum size of union
	public int maxUnion() {
		int[] cnt = new int[parent.length];
		int max = 0;
		for (int i = 0; i < cnt.length; i++) {
            int root = find(i);
			cnt[root]++;
			max = Math.max(max, cnt[root]);
		}
		return max;
	}
}