/*
Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.
For example:
Given a binary tree {1,2,3,4,5},

    1
   / \
  2   3
 / \
4   5

return the root of the binary tree [4,5,2,#,#,3,1].

   4
  / \
 5   2
    / \
   3   1  

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

    	System.out.println(traverseTree(upsideDownBinaryTree(root)));
    	// output: [8,null,4,5,2,null,null,3,1,6,7]
	}

	// Solution1: recursive
	public static TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }

        TreeNode newRoot = upsideDownBinaryTree(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;

        return newRoot;
    }

	// Solution2: iterative, O(1) sapce
	public static TreeNode upsideDownBinaryTree(TreeNode root) {
		if (root == null) {
            return null;
        }

        TreeNode prev = root;
        TreeNode curt = root.left;
        TreeNode right = prev.right;
        prev.left = null;
        prev.right = null;

        while (curt != null) {
            TreeNode next = curt.left;
            curt.left = right;
            right = curt.right;
            curt.right = prev;

            prev = curt;
            curt = next;
        }

        return prev;
    }

	// Solution3: iterative, use stack
	public static TreeNode upsideDownBinaryTree(TreeNode root) {
		if (root == null) {
            return null;
        }

        Stack<TreeNode> stack = new Stack<>();
        while (root.left != null) {
            stack.push(root);
            root = root.left;
        }

        TreeNode newRoot = root;
        TreeNode curt = newRoot;
        while (!stack.empty()) {
            TreeNode parent = stack.pop();
            curt.left = parent.right;
            curt.right = parent;
            parent.left = null;
            parent.right = null;
            curt = parent;
        }

        return newRoot;
	}

	private static String traverseTree(TreeNode root) {
        if (root == null) {
            return "null";
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        StringBuilder res = new StringBuilder();

        while (!queue.isEmpty()) {
            TreeNode curt = queue.poll();
            if (curt == null) {
                res.append("null, ");
            } else {
                res.append(curt.val + ", ");
                queue.offer(curt.left);
                queue.offer(curt.right);
            }
        }
        return res.toString();
    }
}