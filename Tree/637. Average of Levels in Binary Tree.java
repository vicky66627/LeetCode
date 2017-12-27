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

    	System.out.println(averageOfLevels(root));
        // output: [1.0, 2.5, 5.5, 8.0]
        System.out.println(averageOfLevels(left));
        // output: [2.0, 4.5, 8.0]
	}

	public static List<Double> averageOfLevels(TreeNode root) {
		List<Double> res = new ArrayList<>();
		if (root == null) {
			return res;
		}

		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);

		while (!queue.isEmpty()) {
			int size = queue.size();
			double aver = 0;
			for (int i = 0; i < size; i++) {
				TreeNode curt = queue.poll();
				aver += curt.val;
				if (curt.left != null) {
					queue.offer(curt.left);
				}
				if (curt.right != null) {
					queue.offer(curt.right);
				}
			}

			res.add(aver / size);
		}

		return res;
	}
}