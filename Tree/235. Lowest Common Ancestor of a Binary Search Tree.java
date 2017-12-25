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
		TreeNode rright = new TreeNode(4);
		TreeNode llleft = new TreeNode(0);

		root.left = left;
		root.right = right;
		left.left = lleft;
		left.right = lright;
		right.left = rleft;
		right.right = rright;
		lleft.left = llleft;

		System.out.println(lowestCommonAncestor(root, llleft, lright).val);
		// output: 2
		System.out.println(lowestCommonAncestor(root, llleft, lleft).val);
		// output: 1
		System.out.println(lowestCommonAncestor(root, lright, rright).val);
		// output: 4
		System.out.println(lowestCommonAncestor(root, lright, null).val);
		// output: 3
		System.out.println(lowestCommonAncestor(null, lright, rleft) == null ? "null" : lowestCommonAncestor(null, lright, rleft).val);
		// output: null
	}

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
        	return root;
        }

        if (p == null) {
        	return q;
        }
        if (q == null) {
        	return p;
        }

        if (p.val < root.val && q.val < root.val) {
        	return lowestCommonAncestor(root.left, p, q);
        }
        if (p.val > root.val && q.val > root.val) {
        	return lowestCommonAncestor(root.right, p, q);
        }

        return root;
    }
}