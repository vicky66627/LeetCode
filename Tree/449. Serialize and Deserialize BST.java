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

        Codec codec = new Codec();
        String data = codec.serialize(root);
        System.out.println(data);
        // dfs output: 4,2,1,0,3,6,5,7  (preorder)
        TreeNode node = codec.deserialize(data);
        System.out.println(traverseTree(node));
        // output: 4,2,6,1,3,5,7,0,null,null,null...
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

class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
        	return "";
        }
        
        StringBuilder res = new StringBuilder();
        preTraversal(root, res);
        return res.substring(0, res.length() - 1);
    }

    private void preTraversal(TreeNode root, StringBuilder res) {
        if (root == null) {
            return;
        }

        res.append(root.val + ",");
        preTraversal(root.left, res);
        preTraversal(root.right, res);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // System.out.println(data);
        if (data == null || data.length() == 0) {
        	return null;
        }

        String[] dataArr = data.split(",");
        int[] idx = new int[1];

        return buildTree(dataArr, Long.MIN_VALUE, Long.MAX_VALUE, idx);
    }

    private TreeNode buildTree(String[] dataArr, long lower, long upper, int[] idx) {
        if (idx[0] >= dataArr.length || Integer.parseInt(dataArr[idx[0]]) < lower || Integer.parseInt(dataArr[idx[0]]) > upper) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(dataArr[idx[0]++]));
        root.left = buildTree(dataArr, lower, root.val, idx);
        root.right = buildTree(dataArr, root.val, upper, idx);

        return root;
    }
}


// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
