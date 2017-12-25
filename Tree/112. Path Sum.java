/*
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum. 
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

		System.out.println(hasPathSum(root, 8));
		// output: true
		System.out.println(hasPathSum(root, 10));
		// output: true
		System.out.println(hasPathSum(root, 15));
		// output: true
		System.out.println(hasPathSum(root, 11));
		// output: true
		System.out.println(hasPathSum(root, 12));
		// output: false
	}

	public static boolean hasPathSum(TreeNode root, int sum) {
		if (root == null) {
			return false;
		}

		if (root.left == null && root.right == null && root.val == sum) {
            return true;
        }

		return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
	}
}