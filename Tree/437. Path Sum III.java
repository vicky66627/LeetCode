/*
You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11
*/

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	public TreeNode(int x) { val = x; }
}

public class Solution {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode left = new TreeNode(2);
		TreeNode right = new TreeNode(3);
		TreeNode lleft = new TreeNode(3);
		TreeNode lright = new TreeNode(5);
		TreeNode rleft = new TreeNode(6);
		TreeNode rright = new TreeNode(7);
		TreeNode llleft = new TreeNode(4);

		root.left = left;
		root.right = right;
		left.left = lleft;
		left.right = lright;
		right.left = rleft;
		right.right = rright;
		lleft.left = llleft;

		System.out.println(pathSum(root, 7));
        // output: 3 (2->5, 3->4, 7)
        System.out.println(pathSum(root, 10));
        // output: 3 (1->2->3->4, 1->3->6, 3->7)
        System.out.println(pathSum(root, 9));
        // output: 2 (2->3->4, 3->6)
        System.out.println(pathSum(root, 6));
        // output: 2 (1->2->3, 6)
        System.out.println(pathSum(root, 12));
        // output: 0
    }

    // Solution1: O(nlogn) time in best case (balanced tree), O(n^2) time in worst case (no branching), O(n) space due to recursion
    public static int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        return pathSum(root.left, sum) + pathSum(root.right, sum) + dfs(root, sum);
    }

    private static int dfs(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        return (root.val == sum ? 1 : 0) + dfs(root.left, sum - root.val) + dfs(root.right, sum - root.val);
    }

    // Solution2: use map (prefix sum) - O(n) time, O(n) space
    public static int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        return dfs(root, sum, 0, map);
    }

    private static int dfs(TreeNode root, int target, int curtSum, Map<Integer, Integer> map) {
    	if (root == null) {
    		return 0;
    	}

    	curtSum += root.val;
    	int res = map.getOrDefault(curtSum - target, 0);
    	map.put(curtSum, map.getOrDefault(curtSum, 0) + 1);

    	int left = dfs(root.left, target, curtSum, map);
    	int right = dfs(root.right, target, curtSum, map);
    	res += left + right;
    	map.put(curtSum, map.get(curtSum) - 1);

    	return res;
    }
}
