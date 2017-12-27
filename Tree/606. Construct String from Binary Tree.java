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
        // right.left = rleft;
        right.right = rright;
        lleft.left = llleft;

    	System.out.println(tree2str(root));
    	// output: 1(2(4(8))(5))(3()(7))
	}

	public static String tree2str(TreeNode t) {
		if (t == null) {
			return "";
		}

		StringBuilder res = new StringBuilder();
		dfs(t, res);
		return res.toString();
	}

	private static void dfs(TreeNode root, StringBuilder res) {
		if (root == null) {
			return;
		}

		res.append(root.val);

		if (root.left != null || root.right != null) {
			res.append("(");
			dfs(root.left, res);
			res.append(")");

			if (root.right != null) {
				res.append("(");
				dfs(root.right, res);
				res.append(")");
			}
		}
	}
}