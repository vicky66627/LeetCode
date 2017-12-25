
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	public TreeNode(int x) { val = x; }
}

public class Solution {
	public static void main(String[] args) {
		int[] inorder = new int[]{8,4,2,5,1,6,3,7};
		int[] postorder = new int[]{8,4,5,2,6,7,3,1};

		TreeNode root = buildTree(inorder, postorder);
		System.out.println(traverseTree(root));
		// output: 1,2,3,4,5,6,7,8
	}

    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        if (postorder == null || inorder == null || postorder.length == 0 || inorder.length == 0 || postorder.length != inorder.length) {
        	return null;
        }

        Map<Integer, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
        	idxMap.put(inorder[i], i);
        }
        return construct(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1, idxMap);
    }

    private static TreeNode construct(int[] postorder, int poststart, int postend, int[] inorder, int instart, int inend, Map<Integer, Integer> idxMap) {
    	if (instart > inend) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[postend]);
        int pos = idxMap.get(postorder[postend]);
        int leftNum = pos - instart;

        root.left = construct(postorder, poststart, poststart + leftNum - 1, inorder, instart, pos - 1, idxMap);
        root.right = construct(postorder, poststart + leftNum, postend - 1, inorder, pos + 1, inend, idxMap);
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