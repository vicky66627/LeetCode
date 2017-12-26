/*
Example 1:

     3
    / \
   2   3
    \   \ 
     3   1

Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.

Example 2:

     3
    / \
   4   5
  / \   \ 
 1   3   1

Maximum amount of money the thief can rob = 4 + 5 = 9. 

Example 3:

         3
        / \
       4   5
      / \   \ 
     1   5   1
    /
   2

Maximum amount of money the thief can rob = 5 + 5 + 2= 11. 
*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int x) { val = x; }
}

public class Solution {
    public static void main(String[] args) {
    	TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(4);
        TreeNode right = new TreeNode(5);
        TreeNode lleft = new TreeNode(1);
        TreeNode lright = new TreeNode(5);
        // TreeNode rleft = new TreeNode(6);
        TreeNode rright = new TreeNode(1);
        TreeNode llleft = new TreeNode(2);

        root.left = left;
        root.right = right;
        left.left = lleft;
        left.right = lright;
        // right.left = rleft;
        right.right = rright;
        lleft.left = llleft;

    	System.out.println(rob(root));
    	// output: 12
    }

    // Solution1: Dynamic Programming - O(n) time, O(1) space, (stack cost for recursion is not counted).
    public static int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int[] res = robSub(root);
        return Math.max(res[0], res[1]);
    }

    private static int[] robSub(TreeNode root) {
        if (root == null) {
            return new int[2];
        }

        int[] left = robSub(root.left);
        int[] right = robSub(root.right);
        int[] res = new int[2];

        // res[0] -> root is not robbed, res[1] -> root is robbed
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = root.val + left[0] + right[0];

        return res;
    }

    // Solution2: memoization - O(n) time, O(n) space, n is the total number of nodes, (stack cost for recursion is not counted).
    public static int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Map<TreeNode, Integer> map = new HashMap<>();
        return robSub(root, map);
    }

    private static int robSub(TreeNode root, Map<TreeNode, Integer> map) {
        if (root == null) {
            return 0;
        }

        if (map.containsKey(root)) {
            return map.get(root);
        }

        int val = 0;
        if (root.left != null) {
            val += robSub(root.left.left, map) + robSub(root.left.right, map);
        }
        if (root.right != null) {
            val += robSub(root.right.left, map) + robSub(root.right.right, map);
        }

        val = Math.max(root.val + val, robSub(root.left, map) + robSub(root.right, map));
        map.put(root, val);

        return val;
    }

    // the time complexity turns out to be exponential - NOT ACCEPTED
    public static int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int val = 0;
        if (root.left != null) {
            val += rob(root.left.left) + rob(root.left.right);
        }
        if (root.right != null) {
            val += rob(root.right.left) + rob(root.right.left);
        }

        return Math.max(root.val + val, rob(root.left) + rob(root.right));
    }
}