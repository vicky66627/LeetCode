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

    	System.out.println(widthOfBinaryTree(root));
    	// output: 4
	}

	// O(n) time, O(n) space
	public static int widthOfBinaryTree(TreeNode root) {
		if (root == null) {
			return 0;
		}

		return dfs(root, 0, 1, new ArrayList<>(), new ArrayList<>());
	}

	private static int dfs(TreeNode root, int level, int idx, List<Integer> start, List<Integer> end) {
		if (root == null) {
			return 0;
		}
		if (start.size() == level) {
			start.add(idx);
			end.add(idx);
		} else {
			end.set(level, idx);
		}

		int curt = end.get(level) - start.get(level) + 1;
		int left = dfs(root.left, level + 1, idx * 2, start, end);
		int right = dfs(root.right, level + 1, idx * 2 + 1, start, end);

		return Math.max(curt, Math.max(left, right));
	}
}