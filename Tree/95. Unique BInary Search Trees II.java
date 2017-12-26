class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int x) { val = x; }
}

public class Solution {
    public static void main(String[] args) {
        List<TreeNode> list = generateTrees(3);

        for (TreeNode tree : list) {
            System.out.println(traverseTree(tree));
        }
    	
    	// output: [1,null,2,null,3,null,...], [1,null,3,2,null,...], [2,1,3,null,...], [3,1,null,null,2,null,...], [3,2,null,1,null,...]
    }

    public static List<TreeNode> generateTrees(int n) {
        if (n <= 0) {
            return new ArrayList<TreeNode>();
        }

        return generateTree(1, n);
    }

    private static List<TreeNode> generateTree(int start, int end) {
        List<TreeNode> res = new ArrayList<>();
        if (start > end) {
            res.add(null);
            return res;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> left = generateTree(start, i - 1);
            List<TreeNode> right = generateTree(i + 1, end);

            for (TreeNode lnode : left) {
                for (TreeNode rnode : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = lnode;
                    root.right = rnode;
                    res.add(root);
                }
            }
        }

        return res;
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