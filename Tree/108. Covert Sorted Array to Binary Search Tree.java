class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	public TreeNode(int x) { val = x; }
}

public class Solution {
	public static void main(String[] args) {
		int[] nums1 = new int[]{1,2,3,4,5,6,7};
		int[] nums2 = new int[]{1,2,3,4};
		int[] nums3 = new int[]{-10,-3,0,5,9};

		TreeNode t1 = sortedArrayToBST(nums1);
		// output: 4,2,6,1,3,5,7
		TreeNode t2 = sortedArrayToBST(nums2);
		// output: 2,1,3,null,null,null,4
		TreeNode t3 = sortedArrayToBST(nums3);
		// output: 0,-10,5,null,-3,null,9

		System.out.println(traverseTree(t1));
		System.out.println(traverseTree(t2));
		System.out.println(traverseTree(t3));
	}

	public static TreeNode sortedArrayToBST(int[] nums) {
		if (nums == null || nums.length == 0) {
			return null;
		}

		return toBST(nums, 0, nums.length - 1);
	}

	private static TreeNode toBST(int[] nums, int start, int end) {
		if (start > end) {
            return null;
        }

		int mid = start + (end - start) / 2;
		TreeNode root = new TreeNode(nums[mid]);
		root.left = toBST(nums, start, mid - 1);
		root.right = toBST(nums, mid + 1, end);

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