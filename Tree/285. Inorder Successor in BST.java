/*
Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

Note: If the given node has no in-order successor in the tree, return null. 
*/

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
        TreeNode rright = new TreeNode(7);
        TreeNode llleft = new TreeNode(0);

        root.left = left;
        root.right = right;
        left.left = lleft;
        left.right = lright;
        right.left = rleft;
        right.right = rright;
        lleft.left = llleft;

        TreeNode successor = inorderSuccessor(root, lleft);
        System.out.println(successor == null ? "null" : successor.val);
        // output: 2
        successor = inorderSuccessor(root, lright);
        System.out.println(successor == null ? "null" : successor.val);
        // output: 4
        successor = inorderSuccessor(root, rright);
        System.out.println(successor == null ? "null" : successor.val);
        // output: null

        TreeNode predecessor = inorderPredecessor(root, lleft);
        System.out.println(predecessor == null ? "null" : predecessor.val);
        // output: 0
        predecessor = inorderPredecessor(root, lright);
        System.out.println(predecessor == null ? "null" : predecessor.val);
        // output: 2
        predecessor = inorderPredecessor(root, llleft);
        System.out.println(predecessor == null ? "null" : predecessor.val);
        // output: null
    }

    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }

        if (root.val <= p.val) {
            return inorderSuccessor(root.right, p);
        }

        TreeNode left = inorderSuccessor(root.left, p);
        return left == null ? root : left;
    }

    public static TreeNode inorderPredecessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }

        if (p.val <= root.val) {
            return inorderPredecessor(root.left, p);
        }

        TreeNode right = inorderPredecessor(root.right, p);
        return right == null ? root : right;
    }
}