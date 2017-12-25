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

		System.out.println(maxPathSum(root));
		// output: 25
	}

	static int max = Integer.MIN_VALUE;
    public static int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        maxPathDown(root);
        return max;
    }

    private static int maxPathDown(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = Math.max(0, maxPathDown(root.left));
        int right = Math.max(0, maxPathDown(root.right));

        max = Math.max(max, left + right + root.val);
        return Math.max(left, right) + root.val;
    }
}
