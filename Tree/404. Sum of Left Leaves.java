class TreeNode {
	int val;
	TreeNode left, right;
	public TreeNode(int x) { val = x };
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

    	System.out.println(sumOfLeftLeaves(root));
    	// output: 14
	}

	static int sum = 0;
	public static int sumOfLeftLeaves(TreeNode root) {
		if (root == null) {
			return 0;
		}

		if (root.left != null && root.left.left == null && root.left.right == null) {
			sum += root.left.val;
		}

		sumOfLeftLeaves(root.left);
		sumOfLeftLeaves(root.right);

		return sum;
	}
}