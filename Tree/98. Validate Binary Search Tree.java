class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	public TreeNode(int x) { val = x; }
}

public class Solution {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(4);
		TreeNode left = new TreeNode(2);
		TreeNode right = new TreeNode(6);
		TreeNode lleft = new TreeNode(1);
		TreeNode lright = new TreeNode(3);
		TreeNode rleft = new TreeNode(5);
		TreeNode rright = new TreeNode(7);
		TreeNode llleft = new TreeNode(0);

		root.left = left;
		root.right = right;
		left.left = lleft;
		left.right = lright;
		right.left = rleft;
		right.right = rright;
		lleft.left = llleft;

		System.out.println(isValidBST(root));
	}

	public static boolean isValidBST(TreeNode root) {
		if (root == null) {
			return true;
		}

		long upper = Long.MAX_VALUE;
		long lower = Long.MIN_VALUE;

		return isBST(root, lower, upper);
	}

	private static boolean isBST(TreeNode root, long lower, long upper) {
		if (root == null) {
			return true;
		}

		return root.val < upper && root.val > lower && isBST(root.left, lower, root.val) && isBST(root.right, root.val, upper);
	}
}