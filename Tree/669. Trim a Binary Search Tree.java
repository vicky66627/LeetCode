class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int x) { val = x; }
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

        System.out.println(traverseTree(trimBST(root, 0, 5)));
        // output: 4,2,5,1,3,null,null,0,null,null...
        // System.out.println(traverseTree(trimBST(root, 2, 6)));
        // output: 4,2,6,null,3,5,null,null...
        // System.out.println(traverseTree(trimBST(root, 0, 3)));
        // output: 2,1,3,0,null,null...
    }

    public static TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) {
            return null;
        }

        TreeNode left = trimBST(root.left, L, R);
        TreeNode right = trimBST(root.right, L, R);

        if (root.val > R) {
            return left;
        }
        if (root.val < L) {
            return right;
        }

        root.left = left;
        root.right = right;

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