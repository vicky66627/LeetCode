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

		System.out.println(lowestCommonAncestor(root, llleft, lright).val);
		// output: 2
		System.out.println(lowestCommonAncestor(root, llleft, lleft).val);
		// output: 4
		System.out.println(lowestCommonAncestor(root, lright, rright).val);
		// output: 1
		System.out.println(lowestCommonAncestor(root, lright, null).val);
		// output: 5
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

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
        	return root;
        }
        
        return left == null ? right : left;
    }
}