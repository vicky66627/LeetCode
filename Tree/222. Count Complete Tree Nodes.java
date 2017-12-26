class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int x) { val = x; }
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

    	System.out.println(countNodes(root));
    	// output: 8
    }

    public static int countNodes(TreeNode root) {
    	if (root == null) {
    		return 0;
    	}

    	int h = getHeight(root);
        return getHeight(root.right) == h - 1 ? (1 << h) + countNodes(root.right) : (1 << (h - 1)) + countNodes(root.left);
    }

    private static int getHeight(TreeNode root) {
        return root == null ? -1 : 1 + getHeight(root.left);
    }
}