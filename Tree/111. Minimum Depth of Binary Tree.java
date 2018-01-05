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
		TreeNode llleft = new TreeNode(6);

		root.left = left;
		root.right = right;
		left.left = lleft;
		left.right = lright;
		lleft.left = llleft;

		System.out.println(minDepth(root));
		// output: 2
	}

	// DFS
	public static int minDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int left = minDepth(root.left);
		int right = minDepth(root.right);

		return (left == 0 || right == 0) ? (left + right + 1) : (Math.min(left, right) + 1);
	}

	// BFS - level order
    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int depth = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curt = queue.poll();
                if (curt.left == null && curt.right == null) {
                    return depth;
                }
                if (curt.left != null) {
                    queue.offer(curt.left);
                }
                if (curt.right != null) {
                    queue.offer(curt.right);
                }
            }
            depth++;
        }
        
        return depth;
    }
}