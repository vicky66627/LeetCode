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

		System.out.println(zigzagLevelOrder(root));
		// output: [[1],[3,2],[4,5,6,7],[8]]
	}

	public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if (root == null) {
			return res;
		}

		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		boolean reversed = false;

		while (!queue.isEmpty()) {
			int size = queue.size();
			List<Integer> inner = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				TreeNode curt = queue.poll();
				if (reversed) {
					inner.add(0, curt.val);
				} else {
					inner.add(curt.val);
				}
				if (curt.left != null) {
					queue.offer(curt.left);
				}
				if (curt.right != null) {
					queue.offer(curt.right);
				}
			}
			reversed = !reversed;
			res.add(inner);
		}

		return res;
	}
}