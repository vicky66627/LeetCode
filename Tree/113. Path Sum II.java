/*
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.  
*/

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
		TreeNode lleft = new TreeNode(3);
		TreeNode lright = new TreeNode(5);
		TreeNode rleft = new TreeNode(6);
		TreeNode rright = new TreeNode(7);
		TreeNode llleft = new TreeNode(4);

		root.left = left;
		root.right = right;
		left.left = lleft;
		left.right = lright;
		right.left = rleft;
		right.right = rright;
		lleft.left = llleft;

		System.out.println(pathSum(root, 10));
		// output: [[1,2,3,4],[1,3,6]]
		System.out.println(pathSum(root, 8));
		// output: [[1,2,5]]
		System.out.println(pathSum(root, 11));
		// output: [[1,3,7]]
		System.out.println(pathSum(root, 7));
		// output: [[]]
	}

	public static List<List<Integer>> pathSum(TreeNode root, int sum) {
		List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
        	return res;
        }

        dfs(root, new ArrayList<>(), res, sum);
        return res;
    }

    private static void dfs(TreeNode root, List<Integer> inner, List<List<Integer>> res, int sum) {
    	if (root == null) {
            return;
        }

        inner.add(root.val);
        if (root.val == sum && root.left == null && root.right == null) {
            res.add(new ArrayList<>(inner));
            inner.remove(inner.size() - 1);
            return;
        }

        dfs(root.left, inner, res, sum - root.val);
        dfs(root.right, inner, res, sum - root.val);

        inner.remove(inner.size() - 1);
    }
}