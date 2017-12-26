/*
Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

For example:
Given binary tree,

              5
             / \
            1   5
           / \   \
          5   5   5

return 4. 
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
        TreeNode left = new TreeNode(1);
        // TreeNode left = new TreeNode(3);
        TreeNode right = new TreeNode(1);
        TreeNode lleft = new TreeNode(1);
        TreeNode lright = new TreeNode(1);
        TreeNode rleft = new TreeNode(1);
        TreeNode rright = new TreeNode(1);
        TreeNode llleft = new TreeNode(1);

        root.left = left;
        root.right = right;
        left.left = lleft;
        left.right = lright;
        right.left = rleft;
        right.right = rright;
        lleft.left = llleft;

    	System.out.println(countUnivalSubtrees(root));
    	// output: 8
        // output: 6
    }
    
    public static int countUnivalSubtrees(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int[] count = new int[1];
        isUnivalSubtree(root, count);
        return count[0];
    }

    private static boolean isUnivalSubtree(TreeNode root, int[] count) {
        if (root == null) {
            return true;
        }

        boolean left = isUnivalSubtree(root.left, count);
        boolean right = isUnivalSubtree(root.right, count);

        if (left && right) {
            if (root.left != null && root.left.val != root.val) {
                return false;
            }
            if (root.right != null && root.right.val != root.val) {
                return false;
            }

            count[0]++;
            return true;
        }

        return false;
    }
}