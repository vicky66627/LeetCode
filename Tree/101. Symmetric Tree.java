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
		TreeNode right = new TreeNode(2);
		TreeNode lleft = new TreeNode(4);
		TreeNode lright = new TreeNode(5);
		TreeNode rleft = new TreeNode(5);
		TreeNode rright = new TreeNode(4);

		root.left = left;
		root.right = right;
		left.left = lleft;
		left.right = lright;
		right.left = rleft;
		right.right = rright;

		System.out.println(isSymmetric(root));
		// output: true
	}

	public static boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		}
		
		return helper(root.left, root.right);
	}

	private static boolean helper(TreeNode left, TreeNode right) {
		if (left == null && right == null) {
			return true;
		}
		if (left == null || right == null) {
			return false;
		}

		return left.val == right.val && helper(left.left, right.right) && helper(left.right, right.left);
	}
}

