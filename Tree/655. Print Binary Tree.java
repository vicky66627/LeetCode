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

    	System.out.println(printTree(root));

        System.out.println(printTree(left));

        System.out.println(printTree(lleft));
        // output: [["", 4, ""], ["8", "", ""]]
	}

    // O(h * 2^h) time, O(h * 2^h) space
    public static List<List<String>> printTree(TreeNode root) {
        List<List<String>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        
        int height = getHeight(root);
        int col = (1 << height) - 1;
        
        for (int i = 0; i < height; i++) {
            res.add(new ArrayList<>());
            for (int j = 0; j < col; j++) {
                res.get(i).add("");
            }
        }
        
        fillArr(root, res, 0, 0, col);
        return res;
    }

    private static void fillArr(TreeNode root, List<List<String>> res, int idx, int start, int end) {
        if (root == null) {
            return;
        }

        int mid = start + (end - start) / 2;
        res.get(idx).set(mid, String.valueOf(root.val));

        fillArr(root.left, res, idx + 1, start, mid - 1);
        fillArr(root.right, res, idx + 1, mid + 1, end);
    }

	private static int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }
}