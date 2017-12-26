/*
Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

    The root is the maximum number in the array.
    The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
    The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.

Construct the maximum tree by the given array and output the root node of this tree.

Example 1:

Input: [3,2,1,6,0,5]
Output: return the tree root node representing the following tree:

      6
    /   \
   3     5
    \    / 
     2  0   
       \
        1

*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int x) { val = x; }
}

public class Solution {
    public static void main(String[] args) {
    	int[] nums1 = new int[]{3,2,1,6,0,5};
    	int[] nums2 = new int[]{0,4,2,5,9,7,6,3};
    	int[] nums3 = new int[]{1,2};
    	int[] nums4 = new int[]{2,1};

    	System.out.println(traverseTree(constructMaximumBinaryTree(nums1)));
    	// output: 6,3,5,null,2,0,null,null,1,null,...
    	System.out.println(traverseTree(constructMaximumBinaryTree(nums2)));
    	// output: 9,5,7,4,null,null,6,0,2,null,3,null,...
    	System.out.println(traverseTree(constructMaximumBinaryTree(nums3)));
    	// output: 2,1,null,...
    	System.out.println(traverseTree(constructMaximumBinaryTree(nums4)));
    	// output: 2,null,1,null,...
    }

    public static TreeNode constructMaximumBinaryTree(int[] nums) {
    	if (nums == null || nums.length == 0) {
    		return null;
    	}

    	return buildTree(nums, 0, nums.length - 1);
    }

    private static TreeNode buildTree(int[] nums, int start, int end) {
    	if (start > end) {
    		return null;
    	}

    	int maxVal = Integer.MIN_VALUE;
    	int pos = 0;
    	for (int i = start; i <= end; i++) {
    		if (maxVal < nums[i]) {
    			maxVal = nums[i];
    			pos = i;
    		}
    	}

    	TreeNode root = new TreeNode(maxVal);
    	root.left = buildTree(nums, start, pos - 1);
    	root.right = buildTree(nums, pos + 1, end);

    	return root;
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