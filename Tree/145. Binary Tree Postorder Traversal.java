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

		System.out.println(preorderTraversal(root));
		// output: [8,4,5,2,6,7,3,1]
	}

	public static List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if (root == null) {
			return res;
		}

		Stack<TreeNode> stack = new Stack<>();
		Stack<Integer> values = new Stack<>();
		while (root != null || !stack.empty()) {
			if (root != null) {
				values.push(root.val);
				stack.push(root);
				root = root.right;
			} else {
				root = stack.pop();
				root = root.left;
			}
		}

		while (!values.empty()) {
			res.add(values.pop());
		}

		return res;
	}
}
