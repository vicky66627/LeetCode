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

    	System.out.println(diameterOfBinaryTree(root));
    	// output: 5
    }

    static int diameter = 0;
    public static int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        longestPath(root);
        return diameter;
    }

    private static int longestPath(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = longestPath(root.left);
        int right = longestPath(root.right);

        diameter = Math.max(diameter, left + right);
        return Math.max(left, right) + 1;
    }
}