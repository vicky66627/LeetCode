class TreeNode {
	int val;
	TreeNode left, right;
	public TreeNode(int x) { val = x };
}

public class Solution {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(1);
        TreeNode lleft = new TreeNode(1);
        TreeNode lright = new TreeNode(2);
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

    	System.out.println(findSecondMinimumValue(root));
    	// output: 2
	}

	public static int findSecondMinimumValue(TreeNode root) {
		if (root == null) {
			return -1;
		}

		int[] min = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};   // min[0] is the smallest value, min[1] is the second smallest value
		traverse(root, min);
		return min[1] == Integer.MAX_VALUE ? -1 : min[1];
	}

	private static void traverse(TreeNode root, int[] min) {
		if (root == null) {
			return;
		}

		if (min[0] > root.val) {
			min[1] = min[0];
			min[0] = root.val;
		} else if (min[1] > root.val && min[0] != root.val) {
			min[1] = root.val;
		}

		traverse(root.left, min);
		traverse(root.right, min);
	}
}