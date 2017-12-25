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

		System.out.println(binaryTreePaths(root));
		// output: ["1->2->4->8", "1->2->5", "1->3->6", "1->3->7"]
	}

	public static List<String> binaryTreePaths(TreeNode root) {
		List<String> res = new ArrayList<>();
		if (root == null) {
			return res;
		}

		findPath(root, "", res);
		return res;
	}

	private static void findPath(TreeNode root, String path, List<String> res) {
		if (root.left == null && root.right == null) {
			path += root.val;
			res.add(path);
		}

		if (root.left != null) {
            findPath(root.left, path + root.val + "->", res);
        }
        if (root.right != null) {
            findPath(root.right, path + root.val + "->", res);
        }
	}
}
