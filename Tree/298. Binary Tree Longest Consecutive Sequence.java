/*
Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

For example,

   1
    \
     3
    / \
   2   4
        \
         5

Longest consecutive sequence path is 3-4-5, so return 3.

   2
    \
     3
    / 
   2    
  / 
 1

Longest consecutive sequence path is 2-3,not3-2-1, so return 2. 
*/

class TreeNode {
	int val;
	TreeNode left, right;
	public TreeNode(int x) { val = x };
}

public class Solution {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(8);
        TreeNode left = new TreeNode(3);
        TreeNode right = new TreeNode(7);
        TreeNode lleft = new TreeNode(4);
        TreeNode lright = new TreeNode(4);
        TreeNode rleft = new TreeNode(2);
        TreeNode rright = new TreeNode(6);
        TreeNode llleft = new TreeNode(5);

        root.left = left;
        root.right = right;
        left.left = lleft;
        left.right = lright;
        right.left = rleft;
        right.right = rright;
        lleft.left = llleft;

    	System.out.println(longestConsecutive(root));
        // output: 3
        System.out.println(longestConsecutive(left));
        // output: 3
        System.out.println(longestConsecutive(right));
        // output: 1
	}

	static int res = 0;
    public static int longestConsecutive(TreeNode root) {
		if (root == null) {
			return 0;
		}
		dfs(root, null, 0);
		return res;
	}

	private static void dfs(TreeNode root, Integer parenVal, int curtLen) {
		if (root == null) {
			return;
		}

		if (parenVal != null && root.val - parenVal == 1) {
			curtLen++;
		} else {
			curtLen = 1;
		}

		res = Math.max(res, curtLen);
		dfs(root.left, root.val, curtLen);
		dfs(root.right, root.val, curtLen);
	}
}