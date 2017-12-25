
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	public TreeNode(int x) { val = x; }
}

public class Solution {
	public static void main(String[] args) {
		int[] inorder = new int[]{8,4,2,5,1,6,3,7};
		int[] preorder = new int[]{1,2,4,8,5,3,6,7};

		TreeNode root = buildTree(preorder, inorder);
		System.out.println(traverseTree(root));
		// output: 1,2,3,4,5,6,7,8
	}

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0 || preorder.length != inorder.length) {
        	return null;
        }

        Map<Integer, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
        	idxMap.put(inorder[i], i);
        }
        return construct(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, idxMap);
    }

    private static TreeNode construct(int[] preorder, int prestart, int preend, int[] inorder, int instart, int inend, Map<Integer, Integer> idxMap) {
    	if (instart > inend) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[prestart]);
        int pos = idxMap.get(preorder[prestart]);
        int leftNum = pos - instart;

        root.left = construct(preorder, prestart + 1, prestart + leftNum, inorder, instart, pos - 1, idxMap);
        root.right = construct(preorder, prestart + leftNum + 1, preend, inorder, pos + 1, inend, idxMap);

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