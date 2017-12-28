/*
Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.

Note:
A subtree must include all of its descendants.
Here's an example:

    10
    / \
   5  15
  / \   \ 
 1   8   7

The Largest BST Subtree in this case is the highlighted one.
The return value is the subtree's size, which is 3.

Follow up:
Can you figure out ways to solve it with O(n) time complexity? 
*/

class TreeNode {
	int val;
	TreeNode left, right;
	public TreeNode(int x) { val = x };
}

class Info {
	int size;
	long lower;
	long upper;

	public Info(int size, long lower, long upper) {
		this.size = size;
		this.lower = lower;
		this.upper = upper;
	}
}

public class Solution {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(6);
        TreeNode right = new TreeNode(3);
        TreeNode lleft = new TreeNode(8);
        TreeNode lright = new TreeNode(5);
        TreeNode rleft = new TreeNode(2);
        TreeNode rright = new TreeNode(7);
        TreeNode llleft = new TreeNode(4);

        root.left = left;
        root.right = right;
        left.left = lleft;
        left.right = lright;
        right.left = rleft;
        right.right = rright;
        lleft.left = llleft;

    	System.out.println(largestBSTSubtree(root));
    	// output: 3
	}

	// Solution1: O(n) time
	public static int largestBSTSubtree(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int[] count = new int[1];
		traverse(root, count);
		return count[0];
	}

	private Info traverse(TreeNode root, int[] count) {
		if (root == null) {
			return new Info(0, Long.MAX_VALUE, Long.MIN_VALUE);
		}

		Info left = traverse(root.left, count);
		Info right = traverse(root.right, count);

		if (left.size == -1 || right.size == -1 || root.val <= left.upper || root.val >= right.lower) {
			return new Info(-1, 0, 0);
		}

		int size = left.size + right.size + 1;
        count[0] = Math.max(count[0], size);
		
		return new Info(size, Math.min(left.lower, root.val), Math.max(right.upper, root.val));
	}

	// Solution2: O(n^2) time
	public static int largestBSTSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE)) {
            return getNodeNums(root);
        }

        return Math.max(largestBSTSubtree(root.left), largestBSTSubtree(root.right));
    }

    private static int getNodeNums(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getNodeNums(root.left) + getNodeNums(root.right) + 1;
    }

    private static boolean isBST(TreeNode root, long lower, long upper) {
        if (root == null) {
            return true;
        }

        return (root.val > lower && root.val < upper) && isBST(root.left, lower, root.val) && isBST(root.right, root.val, upper);
    }
}