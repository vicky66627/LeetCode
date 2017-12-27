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

    	System.out.println(findTilt(root));
        // output: 19
        System.out.println(findTilt(left));
        // output: 15
        System.out.println(findTilt(right));
        // output: 1
    }

    public static int findTilt(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int[] res = new int[1];
        sumOfTree(root, res);
        return res[0];
    }

    private static int sumOfTree(TreeNode root, int[] res) {
        if (root == null) {
            return 0;
        }

        int left = sumOfTree(root.left, res);
        int right = sumOfTree(root.right, res);

        res[0] += Math.abs(left - right);
        return root.val + left + right;
    }
}