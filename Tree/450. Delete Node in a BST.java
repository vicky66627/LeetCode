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

        // System.out.println(traverseTree(deleteNode(root, 4)));
		// output: 5,2,6,1,3,null,7,0,null,...
        System.out.println(traverseTree(deleteNode(root, 2)));
        // output: 4,3,6,1,null,5,7,0,null,... 
        System.out.println(traverseTree(deleteNode(root, 6)));
        // output: 4,3,7,1,null,5,null,0,null,...
	}

	public static TreeNode deleteNode(TreeNode root, int key) {
		if (root == null) {
			return null;
		}

		if (key < root.val) {
			root.left = deleteNode(root.left, key);
		} else if (key > root.val) {
			root.right = deleteNode(root.right, key);
		} else {
			if (root.left == null) {
				return root.right;
			} else if (root.right == null) {
				return root.left;
			}

			TreeNode minNode = findMin(root.right);
			root.val = minNode.val;
			root.right = deleteNode(root.right, root.val);
		}
		return root;
	}

	private static TreeNode findMin(TreeNode root) {
		while (root.left != null) {
			root = root.left;
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