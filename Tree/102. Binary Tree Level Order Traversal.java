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

		System.out.println(levelOrder(root));
		// output: [[1],[2,3],[4,5,6,7],[8]]
	}

	// DFS
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
		if (root == null) {
			return res;
		}
        
        dfs(root, res, 0);
        return res;
    }
    
    private void dfs(TreeNode root, List<List<Integer>> res, int level) {
        if (root == null) {
            return;
        }
        
        if (res.size() == level) {
            res.add(new ArrayList<>());
        }
        
        res.get(level).add(root.val);
        
        dfs(root.left, res, level + 1);
        dfs(root.right, res, level + 1);
    }
    
    // BFS
	public static List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if (root == null) {
			return res;
		}

		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);

		while (!queue.isEmpty()) {
			int size = queue.size();
			List<Integer> inner = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				TreeNode curt = queue.poll();
				inner.add(curt.val);
				if (curt.left != null) {
					queue.offer(curt.left);
				}
				if (curt.right != null) {
					queue.offer(curt.right);
				}
			}
			res.add(inner);
		}

		return res;
	}
}