/*
Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

Example:
Given binary tree

          1
         / \
        2   3
       / \     
      4   5    

Returns [4, 5, 3], [2], [1].

Explanation:

1. Removing the leaves [4, 5, 3] would result in this tree:

          1
         / 
        2          

2. Now removing the leaf [2] would result in this tree:

          1          

3. Now removing the leaf [1] would result in the empty tree:

          []         

Returns [4, 5, 3], [2], [1]. 
*/

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

    	System.out.println(findLeaves(root));
    	// output: [[8,5,6,7], [4,3],[2], [1]]
	}

	// not remove leaves, not destroy tree structure
	public static List<List<Integer>> findLeaves(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if (root == null) {
			return res;
		}

		getDepth(root, res);
		return res;
	}

	private static int getDepth(TreeNode root, List<List<Integer>> res) {
		if (root == null) {
			return -1;
		}

		int depth = Math.max(getDepth(root.left, res), getDepth(root.right, res)) + 1;
		if (res.size() == depth) {
			res.add(new ArrayList<>());
		}
		res.get(depth).add(root.val);

		return depth;
	}
}