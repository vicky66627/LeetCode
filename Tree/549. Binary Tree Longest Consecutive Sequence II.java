/*
Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.

Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] are both considered valid, 
but the path [1,2,4,3] is not valid. On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.

Example 1:

Input:
        1
       / \
      2   3
Output: 2
Explanation: The longest consecutive path is [1, 2] or [2, 1].

Example 2:

Input:
        2
       / \
      1   3
Output: 3
Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].

Note: All the values of tree nodes are in the range of [-1e7, 1e7].
*/

class TreeNode {
	int val;
	TreeNode left, right;
	public TreeNode(int x) { val = x };
}

public class Solution {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(4);
        TreeNode lleft = new TreeNode(1);
        TreeNode lright = new TreeNode(6);
        TreeNode rleft = new TreeNode(7);
        TreeNode rright = new TreeNode(5);
        TreeNode llleft = new TreeNode(0);

        root.left = left;
        root.right = right;
        left.left = lleft;
        left.right = lright;
        right.left = rleft;
        right.right = rright;
        lleft.left = llleft;

    	System.out.println(longestConsecutive(root));
    	// output: 6
    	System.out.println(longestConsecutive(left));
    	// output: 3
	}

	public static int longestConsecutive(TreeNode root) {
		int[] res = new int[1];
		longestPath(root, res);
		return res[0];
	}

	private static int[] longestPath(TreeNode root, int[] res) {
		if (root == null) {
			return new int[]{0, 0};
		}

		int inr = 1, dcr = 1;
		if (root.left != null) {
			int[] left = longestPath(root.left, res);   // left[0] stores inr, left[1] stores dcr
			if (root.val + 1 == root.left.val) {
				inr = left[0] + 1;
			} else if (root.val - 1 == root.left.val) {
				dcr = left[1] + 1;
			}
		}
		if (root.right != null) {
			int [] right = longestPath(root.right, res);
			if (root.val + 1 == root.right.val) {
				inr = Math.max(inr, right[0] + 1);
			} else if (root.val - 1 == root.right.val) {
				dcr = Math.max(dcr, right[1] + 1);
			}
		}

		res[0] = Math.max(res[0], inr + dcr - 1);
		return new int[]{inr, dcr};
	}
}