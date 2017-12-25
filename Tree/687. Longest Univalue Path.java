/*
Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.

Note: The length of path between two nodes is represented by the number of edges between them.

Example 1:

Input:

              5
             / \
            4   5
           / \   \
          1   1   5

Output:

2

Example 2:

Input:

              1
             / \
            4   5
           / \   \
          4   4   5

Output:

2
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
		TreeNode left = new TreeNode(4);
		TreeNode right = new TreeNode(3);
		TreeNode lleft = new TreeNode(4);
		TreeNode lright = new TreeNode(4);
		TreeNode rleft = new TreeNode(6);
		TreeNode rright = new TreeNode(3);
		TreeNode llleft = new TreeNode(8);

		root.left = left;
		root.right = right;
		left.left = lleft;
		left.right = lright;
		right.left = rleft;
		right.right = rright;
		lleft.left = llleft;

		System.out.println(longestUnivaluePath(root));
		// output: 2
	}

	// O(n) time, n is the number of nodes in the tree, O(h) space, h is the height of the tree
	static int res = 0;
	public static int longestUnivaluePath(TreeNode root) {
		if (root == null) {
			return 0;
		}

		dfs(root, root.val);
		return res;
	}

	private static int dfs(TreeNode root, int val) {
		if (root == null) {
			return 0;
		}

		int left = dfs(root.left, root.val);
		int right = dfs(root.right, root.val);

		res = Math.max(res, left + right);

		if (root.val != val) {
			return 0;
		}

		return Math.max(left, right) + 1;
	}
}
