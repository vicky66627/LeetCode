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
        TreeNode lleft = new TreeNode(7);
        TreeNode lright = new TreeNode(3);
        TreeNode rleft = new TreeNode(5);
        TreeNode rright = new TreeNode(1);
        TreeNode llleft = new TreeNode(0);

        root.left = left;
        root.right = right;
        left.left = lleft;
        left.right = lright;
        right.left = rleft;
        right.right = rright;
        lleft.left = llleft;

        System.out.println(traverseTree(root));
        // output: 4,2,6,7,3,5,1,0,null,null...
        recoverTree(root);
        System.out.println(traverseTree(root));
        // output: 4,2,6,1,3,5,7,0,null,null...

        TreeNode t1 = new TreeNode(0);
        t1.left = new TreeNode(1);
        first = null;
        sec = null;
        prev = null;
        System.out.println(traverseTree(t1));
        // output: 0,1,null,...
        recoverTree(t1);
        System.out.println(traverseTree(t1));
        // output: 1,0,null,...

	}

	// Following in-order traversal, we should have following order: prev.val < curr.val
	// So the basic idea is to visit the tree with in-order traversal and search for two swapped nodes. Then swap them back.
    static TreeNode first = null;
    static TreeNode sec = null;
    static TreeNode prev = null;
    public static void recoverTree(TreeNode root) {
        if (root == null) {
            return;
        }

        findNodes(root);

        int tmp = first.val;
        first.val = sec.val;
        sec.val = tmp;
    }

    private static void findNodes(TreeNode root) {
        if (root == null) {
            return;
        }

        findNodes(root.left);

        if (prev != null && root.val <= prev.val) {
            if (first == null) {
                first = prev;
            }
            sec = root;
        }
        prev = root;

        findNodes(root.right);
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

