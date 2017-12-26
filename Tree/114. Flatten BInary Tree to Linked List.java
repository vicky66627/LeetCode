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

    	System.out.println(traverseTree(root));
    	// output: 1,2,3,4,5,6,7,8,null,...

        flatten(root);
        System.out.println(traverseTree(root));
        // output: 1,null,2,null,4,null,8,null,5,null,3,null,6,null,7,null,...
    }

    public static void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        TreeNode left = root.left;
        TreeNode right = root.right;
        
        flatten(left);
        flatten(right);

        root.left = null;
        root.right = left;

        while (root.right != null) {
            root = root.right;
        }

        root.right = right;
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