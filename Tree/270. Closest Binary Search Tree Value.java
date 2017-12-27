/*
Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:

    Given target value is a floating point.
    You are guaranteed to have only one unique value in the BST that is closest to the target.
*/

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

    	System.out.println(closestValue(root, 4.57));
        // output: 5
        System.out.println(closestValue(root, 7.87));
        // output: 7
        System.out.println(closestValue(root, 0.49));
        // output: 0
        System.out.println(closestValue(root, 5.32));
        // output: 5
    }

    public static int closestValue(TreeNode root, double target) {
        if (root == null) {
            return 0;
        }

        TreeNode child = target < root.val ? root.left : root.right;
        if (child == null) {
            return root.val;
        }

        int val = closestValue(child, target);
        return Math.abs(root.val - target) < Math.abs(val - target) ? root.val : val;
    }
}