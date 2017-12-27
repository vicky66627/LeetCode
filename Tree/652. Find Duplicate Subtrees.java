class TreeNode {
	int val;
	TreeNode left, right;
	public TreeNode(int x) { val = x };
}

public class Solution {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(4);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(1);
        TreeNode lleft = new TreeNode(1);
        TreeNode lright = new TreeNode(3);
        TreeNode rleft = new TreeNode(0);
        TreeNode rright = new TreeNode(0);
        TreeNode llleft = new TreeNode(0);

        root.left = left;
        root.right = right;
        left.left = lleft;
        left.right = lright;
        right.left = rleft;
        right.right = rright;
        lleft.left = llleft;

        List<TreeNode> res = findDuplicateSubtrees(root);

        for (TreeNode tree : res) {
    		System.out.println(traverseTree(tree));
    	}
    	// output: [[1,0,null,...], [0]]
	}

	public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Map<String, Integer> map = new HashMap<>();
        serialize(root, map, res);

        return res;
    }

    // preorder
    private static String serialize(TreeNode root, Map<String, Integer> map, List<TreeNode> res) {
        if (root == null) {
            return "#";
        }

        String left = serialize(root.left, map, res);
        String right = serialize(root.right, map, res);

        String s = root.val + "," + left + "," + right;

        if (map.containsKey(s) && map.get(s) == 1) {
            res.add(root);
        }
        map.put(s, map.getOrDefault(s, 0) + 1);

        return s;
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