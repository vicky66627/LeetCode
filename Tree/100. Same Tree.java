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

		TreeNode root2 = new TreeNode(1);
		TreeNode left2 = new TreeNode(2);
		TreeNode right2 = new TreeNode(3);
		TreeNode lleft2 = new TreeNode(4);
		TreeNode lright2 = new TreeNode(5);
		TreeNode rleft2 = new TreeNode(6);
		TreeNode rright2 = new TreeNode(7);
		TreeNode llleft2 = new TreeNode(8);

		root2.left = left2;
		root2.right = right2;
		left2.left = lleft2;
		left2.right = lright2;
		right2.left = rleft2;
		right2.right = rright2;
		lleft2.left = llleft2;

		System.out.println(isSameTree(root, root2));
		// output: true
	}

	public static boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null) {
			return true;
		}
		if (p == null || q == null) {
			return false;
		}

		return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
	}
}

