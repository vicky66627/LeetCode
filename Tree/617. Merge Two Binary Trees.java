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

		TreeNode root2 = new TreeNode(5);
		TreeNode left2 = new TreeNode(10);
		TreeNode right2 = new TreeNode(6);
		TreeNode lleft2 = new TreeNode(7);
		TreeNode lright2 = new TreeNode(2);
		TreeNode rleft2 = new TreeNode(4);
		TreeNode rright2 = new TreeNode(9);
		TreeNode llright2 = new TreeNode(1);

		root2.left = left2;
		root2.right = right2;
		left2.left = lleft2;
		left2.right = lright2;
		right2.left = rleft2;
		right2.right = rright2;
		lleft2.right = llright2;

		System.out.println(traverseTree(mergeTrees(root, root2)));
		// output: [6,12,9,11,7,10,16,8,1]
	}

	public static TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
		if (t1 == null) {
			return t2;
		}
		if (t2 == null) {
			return t1;
		}

		TreeNode root = new TreeNode(t1.val + t2.val);
		root.left = mergeTrees(t1.left, t2.left);
		root.right = mergeTrees(t1.right, t2.right);

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
