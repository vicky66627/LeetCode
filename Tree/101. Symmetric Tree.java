class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	public TreeNode(int x) { val = x; }
}

public class Solution {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode left = new TreeNode(2);
		TreeNode right = new TreeNode(2);
		TreeNode lleft = new TreeNode(4);
		TreeNode lright = new TreeNode(5);
		TreeNode rleft = new TreeNode(5);
		TreeNode rright = new TreeNode(4);

		root.left = left;
		root.right = right;
		left.left = lleft;
		left.right = lright;
		right.left = rleft;
		right.right = rright;

		System.out.println(isSymmetric(root));
		// output: true
	}

	// Solution1: recusive
	public static boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		}
		
		return helper(root.left, root.right);
	}

	private static boolean helper(TreeNode left, TreeNode right) {
		if (left == null && right == null) {
			return true;
		}
		if (left == null || right == null) {
			return false;
		}

		return left.val == right.val && helper(left.left, right.right) && helper(left.right, right.left);
	}

	// Solution2: iterative with queue
    public static boolean isSymmetric(TreeNode root) {
		if (root == null || (root.left == null && root.right == null)) {
			return true;
		}
		
        if (root.left == null || root.right == null) {
            return false;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);
        
        while(!queue.isEmpty()) {
            if (queue.size() % 2 == 1) {
                return false;
            }
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            if (left.val != right.val) {
                return false;
            }
            if (left.left != null) {
                if (right.right == null) {
                    return false;
                }
                queue.offer(left.left);
                queue.offer(right.right);
            } else if (right.right != null) {
                return false;
            }
            
            if (left.right != null) {
                if (right.left == null) {
                    return false;
                }
                queue.offer(left.right);
                queue.offer(right.left);
            } else if (right.left != null) {
                return false;
            }
        }
		return true;
	}
}

