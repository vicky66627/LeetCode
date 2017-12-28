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

    	System.out.println(traverseTree(addOneRow(root,10,2)));
    	// output: 1,10,10,2,null,null,3,4,5,6,7,8
    	// System.out.println(traverseTree(addOneRow(root,10,4)));
    	// output: 1,2,3,4,5,6,7,10,10,10,10,10,10,10,10,8
	}

	public static TreeNode addOneRow(TreeNode root, int v, int d) {
		if (root == null) {
			return null;
		}
		if (d == 1) {
			TreeNode newRoot = new TreeNode(v);
			newRoot.left = root;
			return newRoot;
		}

		dfs(root, v, d, 1);

		return root;
	}

	private static void dfs(TreeNode root, int val, int depth, int curtDepth) {
		if (root == null) {
			return;
		}

		if (depth == curtDepth + 1) {
			TreeNode insertLeft = new TreeNode(val);
			TreeNode insertRight = new TreeNode(val);
			insertLeft.left = root.left;
			insertRight.right = root.right;
			root.left = insertLeft;
			root.right = insertRight;
		}
		dfs(root.left, val, depth, curtDepth + 1);
        dfs(root.right, val, depth, curtDepth + 1);
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