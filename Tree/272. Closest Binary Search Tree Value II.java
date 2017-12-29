/*
Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Note:

    Given target value is a floating point.
    You may assume k is always valid, that is: k ≤ total nodes.
    You are guaranteed to have only one unique set of k values in the BST that are closest to the target.

Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)? 
*/

class TreeNode {
	int val;
	TreeNode left, right;
	public TreeNode(int x) { val = x };
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

    	System.out.println(closestKValues(root, 4.57, 3));
        // output: [4,5,6]
        System.out.println(closestKValues(root, 7.87, 2));
        // output: [6,7]
        System.out.println(closestKValues(root, 0.49, 3));
        // output: [0,1,2]
        System.out.println(closestKValues(root, 5.32, 4));
        // output: [4,5,6,7]
    }

    // Solution1: O(klogn) time, O(klogn) space
    public static List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Stack<TreeNode> pred = new Stack<>();  // stores nodes whose value less than or equal to target
        Stack<TreeNode> succ = new Stack<>();  // stores nodes whose value greater than target

        while (root != null) {
            if (target < root.val) {
                succ.push(root);
                root = root.left;
            } else {
                pred.push(root);
                root = root.right;
            }
        }

        while (k-- > 0) {
            if (pred.empty() && succ.empty()) {
                break;
            } else if (pred.empty()) {
                res.add(getSucc(succ));
            } else if (succ.empty()) {
                res.add(getPred(pred));
            } else {
                if (Math.abs(pred.peek().val - target) < Math.abs(succ.peek().val - target)) {
                    res.add(getPred(pred));
                } else {
                    res.add(getSucc(succ));
                }
            }
        }

        return res;
    }

    private static int getPred(Stack<TreeNode> stack) {
        TreeNode pop = stack.pop();
        TreeNode node = pop.left;
        while (node != null) {
            stack.push(node);
            node = node.right;
        }
        return pop.val;
    }

    private static int getSucc(Stack<TreeNode> stack) {
        TreeNode pop = stack.pop();
        TreeNode node = pop.right;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
        return pop.val;
    }

    // Solution2: O(n + k) time, O(n) space
    public static List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Stack<Integer> pred = new Stack<>();  // stores values less than or equal to target
        Stack<Integer> succ = new Stack<>();  // stores values greater than target

        inorder(root, false, pred, target);
        inorder(root, true, succ, target);

        while (k-- > 0) {
            if (pred.empty()) {
                res.add(succ.pop());
            } else if (succ.empty()) {
                res.add(pred.pop());
            } else {
                if (Math.abs(pred.peek() - target) < Math.abs(succ.peek() - target)) {
                    res.add(pred.pop());
                } else {
                    res.add(succ.pop());
                }
            }
        }

        return res;
    }

    private static void inorder(TreeNode root, boolean reversed, Stack<Integer> stack, double target) {
        if (root == null) {
            return;
        }

        inorder(reversed ? root.right : root.left, reversed, stack, target);

        if ((reversed && root.val <= target) || (!reversed && root.val > target)) {
            return;
        }

        stack.push(root.val);
        inorder(reversed ? root.left : root.right, reversed, stack, target);
    }
}