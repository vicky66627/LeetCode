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

        Codec codec = new Codec();
        String data = codec.serialize(root);
        System.out.println(data);
        // dfs output: 1,2,4,8,#,#,#,5,#,#,3,6,#,#,7,#,#  (preorder)
        // bfs output: 1,2,3,4,5,6,7,8,#,#,#,#,#,#,#,#,#  (level order)
        TreeNode node = codec.deserialize(data);
        System.out.println(traverseTree(node));
        // output: 1,2,3,4,5,6,7,8,null,null,null...
	}

	private String traverseTree(TreeNode root) {
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

// Solution1: DFS
class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
        	return "#";
        }
        StringBuilder res = new StringBuilder();
        dfsSerialize(root, res);
        return res.substring(0, res.length() - 1);
    }

    private void dfsSerialize(TreeNode root, StringBuilder res) {
    	if (root == null) {
    		res.append("#,");
    		return;
    	}

    	res.append(root.val + ",");
    	dfsSerialize(root.left, res);
    	dfsSerialize(root.right, res);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
        	return null;
        }

        String[] dataArr = data.split(",");
        int[] idx = new int[1];
        return dfsDeserialize(dataArr, idx);
    }

    private TreeNode dfsDeserialize(String[] dataArr, int[] idx) {
    	if (dataArr[idx[0]].equals("#")) {
    		idx[0]++;
    		return null;
    	}

    	TreeNode root = new TreeNode(Integer.parseInt(dataArr[idx[0]++]));
    	root.left = dfsDeserialize(dataArr, idx);
    	root.right = dfsDeserialize(dataArr, idx);

    	return root;
    }
}

// Solution2: BFS
class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
        	return "";
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        StringBuilder res = new StringBuilder();

        while (!queue.isEmpty()) {
        	TreeNode curt = queue.poll();
        	if (curt == null) {
        		res.append("#,");
        		continue;
        	}
        	res.append(curt.val + ",");
        	queue.offer(curt.left);
        	queue.offer(curt.right);
        }

        return res.substring(0, res.length() - 1);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
    	// System.out.println(data);
        if (data == null || data.length() == 0) {
        	return null;
        }

        String[] dataArr = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(dataArr[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        for (int i = 1; i < dataArr.length; i++) {
        	TreeNode curt = queue.poll();
        	if (!dataArr[i].equals("#")) {
        		curt.left = new TreeNode(Integer.parseInt(dataArr[i]));
        		queue.offer(curt.left);
        	}
        	i++;
        	if (!dataArr[i].equals("#")) {
        		curt.right = new TreeNode(Integer.parseInt(dataArr[i]));
        		queue.offer(curt.right);
        	}
        }

        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));