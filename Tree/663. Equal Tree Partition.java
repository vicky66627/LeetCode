/*
Given a binary tree with n nodes, your task is to check if it's possible to partition the tree to two trees which have the equal sum of values after removing exactly one edge on the original tree.

Example 1:

Input:     
    5
   / \
  10 10
    /  \
   2   3

Output: True
Explanation: 
    5
   / 
  10
      
Sum: 15

   10
  /  \
 2    3

Sum: 15

Example 2:

Input:     
    1
   / \
  2  10
    /  \
   2   20

Output: False
Explanation: You can't split the tree into two trees with equal sum after removing exactly one edge on the tree.
*/

class TreeNode {
	int val;
	TreeNode left, right;
	public TreeNode(int x) { val = x };
}

public class Solution {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(2);
        TreeNode left = new TreeNode(1);
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

    	System.out.println(checkEqualTree(root));
    	// output: true
    	System.out.println(checkEqualTree(left));
    	// output: false
    	System.out.println(checkEqualTree(right));
    	// output: false

    	TreeNode t1 = new TreeNode(0);
    	t1.left = new TreeNode(-1);
    	t1.right = new TreeNode(1);
    	System.out.println(checkEqualTree(t1));
    	// output: false
	}

	// Solution1: use HashMap
	public static boolean checkEqualTree(TreeNode root) {
        if (root == null) {
            return true;
        }

        Map<Integer, Integer> map = new HashMap<>();
        int sum = getSum(root, map);
        if (sum == 0) {
            return map.containsKey(0) && map.get(0) > 1;
        }

        return (sum % 2 == 0) && map.containsKey(sum / 2);
    }

    private static int getSum(TreeNode root, Map<Integer, Integer> map) {
        if (root == null) {
            return 0;
        }

        int sum = root.val + getSum(root.left, map) + getSum(root.right, map);
        map.put(sum, map.getOrDefault(sum, 0) + 1);

        return sum;
    }

	// Solution2: use Stack
	public static boolean checkEqualTree(TreeNode root) {
        if (root == null) {
            return true;
        }

        Stack<Integer> stack = new Stack<>();
        int sum = getSum(root, stack);
        if (sum % 2 == 1) {
        	return false;
        }
        stack.pop();

        while (!stack.empty()) {
        	if (stack.pop() * 2 == sum) {
        		return true;
        	}
        }

        return false;
    }

    private static int getSum(TreeNode root, Stack<Integer> stack) {
        if (root == null) {
            return 0;
        }

        int sum = root.val + getSum(root.left, stack) + getSum(root.right, stack);
        stack.add(sum);

        return sum;
    }
}