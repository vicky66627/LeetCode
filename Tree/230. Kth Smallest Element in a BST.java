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

    	System.out.println(kthSmallest(root, 1));
    	// output: 0
        System.out.println(kthSmallest(root, 5));
        // output: 4
        System.out.println(kthSmallest(root, 7));
        // output: 6
    }

    public static int kthSmallest(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }

        int leftNum = getCount(root.left);

        if (k == leftNum + 1) {
            return root.val;
        } else if (k < leftNum + 1) {
            return kthSmallest(root.left, k);
        } else {
            return kthSmallest(root.right, k - leftNum - 1);
        }
    }

    private static int getCount(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + getCount(root.left) + getCount(root.right);
    }
}