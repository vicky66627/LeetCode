/*
Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.

You may assume each number in the sequence is unique.

Follow up:
Could you do it using only constant space complexity?
*/

class TreeNode {
	int val;
	TreeNode left, right;
	public TreeNode(int x) { val = x };
}

public class Solution {
	public static void main(String[] args) {
		int[] preorder1 = new int[]{4,2,1,0,3,6,5,7};
        int[] preorder2 = new int[]{1,2,3,4,5,6,7,8};
        int[] preorder3 = new int[]{4,5,9,8,7,3,6,2,1};

    	System.out.println(verifyPreorder(preorder1));
        // output: true
        System.out.println(verifyPreorder(preorder2));
        // output: true
        System.out.println(verifyPreorder(preorder3));
        // output: false
	}

    // O(n) space
    public static boolean verifyPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return true;
        }

        int lower = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int val : preorder) {
            if (val < lower) {
                return false;
            }

            while (!stack.empty() && val > stack.peek()) {
                lower = stack.pop();
            }
            stack.push(val);
        }

        return true;
    }

    // O(1) space
    public static boolean verifyPreorder(int[] preorder) {
		if (preorder == null || preorder.length == 0) {
			return true;
		}
		
        int lower = Integer.MIN_VALUE, idx = -1;
        for (int val : preorder) {
            if (val < lower) {
                return false;
            }

            while (idx >= 0 && val > preorder[idx]) {
                lower = preorder[idx--];
            }
            preorder[++idx] = val;
        }

        return true;
	}
}