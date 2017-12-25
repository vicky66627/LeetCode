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
		TreeNode lleft = new TreeNode(4);
		TreeNode lright = new TreeNode(5);
		TreeNode rleft = new TreeNode(6);
		TreeNode rright = new TreeNode(7);
		TreeNode llleft = new TreeNode(8);

		root.left = left;
		root.right = right;
		left.left = lleft;
		left.right = lright;
		right.left = rleft;
		right.right = rright;
		lleft.left = llleft;

		System.out.println(isBalanced(root));
		// output: true
	}

	// Solution1: top-down, O(n^2) time
	public static boolean isBalanced(TreeNode root) {
		if (root == null) {
			return true;
		}

		int left = maxDepth(root.left);
		int right = maxDepth(root.right);

		return Math.abs(left - right) <= 1 && isBalanced(root.left) && isBalanced(root.right);
	}

	private static int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int left = maxDepth(root.left);
		int right = maxDepth(root.right);

		return Math.max(left, right) + 1;
	}

	// Solution2: bottom-up, O(n) time
	public static boolean isBalanced(TreeNode root) {
		if (root == null) {
			return true;
		}

		return dfsHeight(root) != -1;
	}

	private static int dfsHeight(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int left = dfsHeight(root.left);
		if (left == -1) {
			return -1;
		}
		int right = dfsHeight(root.right);
		if (right == -1) {
			return -1;
		}

		if (Math.abs(left - right) > 1) {
			return -1;
		}

		return Math.max(left, right) + 1;
	}
}