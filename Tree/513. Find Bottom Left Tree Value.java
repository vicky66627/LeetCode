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
		TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        TreeNode lleft = new TreeNode(4);
        TreeNode lright = new TreeNode(5);
        TreeNode rleft = new TreeNode(6);
        TreeNode rright = new TreeNode(7);
        TreeNode llleft = new TreeNode(8);
        TreeNode rrright = new TreeNode(9);

        root.left = left;
        root.right = right;
        left.left = lleft;
        left.right = lright;
        right.left = rleft;
        right.right = rright;
        lleft.left = llleft;
        rright.right = rrright;

    	System.out.println(findBottomLeftValue(root));
        // output: 8
        System.out.println(findBottomLeftValue(left));
        // output: 8
        System.out.println(findBottomLeftValue(right));
        // output: 9
	}

    // Solution1: DFS
    public static int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return -1;
        }
        int[] res = new int[2];  // res[0] stores current level, res[1] stores value
        traverse(root, 1, res);
        return res[0];
    }

    private static void traverse(TreeNode root, int depth, int[] res) {
        if (root == null) {
            return;
        }

        if (res[1] < depth) {
            res[1] = depth;
            res[0] = root.val;
        }
        traverse(root.left, depth + 1, res);
        traverse(root.right, depth + 1, res);
    }


    // Solution2: BFS - level order
    public static int findBottomLeftValue(TreeNode root) {
		if (root == null) {
            return -1;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root.right != null) {
                queue.offer(root.right);
            }
            if (root.left != null) {
                queue.offer(root.left);
            }
        }

        return root.val;
    }
}