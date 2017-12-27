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

        TreeNode t1 = new TreeNode(2);
        t1.left = new TreeNode(4);
        t1.right = new TreeNode(5);
        t1.left.left = new TreeNode(8);

        TreeNode t2 = new TreeNode(7);

        TreeNode t3 = new TreeNode(3);
        t3.left = new TreeNode(6);

        TreeNode t4 = new TreeNode(3);
        t4.left = new TreeNode(6);
        t4.right = new TreeNode(9);

        TreeNode t5 = new TreeNode(3);
        t5.left = new TreeNode(6);
        t5.right = new TreeNode(7);
        t5.left.left = new TreeNode(8);

        System.out.println(isSubtree(root, t1));
        // output: true
        System.out.println(isSubtree(root, t2));
        // output: true
        System.out.println(isSubtree(root, t3));
        // output: false
        System.out.println(isSubtree(root, t4));
        // output: false
        System.out.println(isSubtree(root, t5));
        // output: false
    }

    public static boolean isSubtree(TreeNode s, TreeNode t) {
        if (t == null) {
            return true;
        }
        if (s == null) {
            return false;
        }

        return isSubtree(s.left, t) || isSubtree(s.right, t) || isSameTree(s, t);
    }

    private static boolean isSameTree(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }

        return s.val == t.val && isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }
}