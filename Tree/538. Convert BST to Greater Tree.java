class TreeNode {
	int val;
	TreeNode left, right;
	public TreeNode(int x) { val = x };
}

public class Solution {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(4);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(6);
        TreeNode lleft = new TreeNode(1);
        TreeNode lright = new TreeNode(3);
        TreeNode rleft = new TreeNode(5);
        TreeNode rright = new TreeNode(7);
        TreeNode llleft = new TreeNode(0);

        root.left = left;
        root.right = right;
        left.left = lleft;
        left.right = lright;
        right.left = rleft;
        right.right = rright;
        lleft.left = llleft;

    	System.out.println(traverseTree(convertBST(root)));
    	// output: 22,27,13,28,25,18,7,28,null,...
	}

	// Solution1: recursive
	static int val = 0;
	public static TreeNode convertBST(TreeNode root) {
		if (root == null) {
			return null;
		}

		convertBST(root.right);
		root.val += val;
		val = root.val;
		convertBST(root.left);

		return root;
	}

	// Solution2: iterative
	public static TreeNode convertBST(TreeNode root) {
		if (root == null) {
			return null;
		}

		Stack<TreeNode> stack = new Stack<>();
		int val = 0;
		TreeNode curt = root;

		while (curt != null || !stack.empty()) {
			if (curt != null) {
				stack.push(curt);
				curt = curt.right;
			} else {
				curt = stack.pop();
				curt.val += val;
				val = curt.val;
				curt = curt.left;
			}
		}

		return root;
	}

	private static String traverseTree(TreeNode root) {
        if (root == null) {
            return "null";
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        StringBuilder res = new StringBuilder();

        while (!queue.isEmpty()) {
            TreeNode curt = queue.poll();
            if (curt == null) {
                res.append("null, ");
            } else {
                res.append(curt.val + ", ");
                queue.offer(curt.left);
                queue.offer(curt.right);
            }
        }
        return res.toString();
    }
}