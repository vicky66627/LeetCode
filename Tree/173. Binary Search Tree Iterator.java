class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	public TreeNode(int x) { val = x; }
}

public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(6);
        TreeNode lleft = new TreeNode(0);
        TreeNode lright = new TreeNode(3);
        TreeNode rleft = new TreeNode(5);
        TreeNode rright = new TreeNode(7);
        TreeNode llright = new TreeNode(1);

        root.left = left;
        root.right = right;
        left.left = lleft;
        left.right = lright;
        right.left = rleft;
        right.right = rright;
        lleft.right = llright;

        BSTIterator iter = new BSTIterator(root);
        while (iter.hasNext()) {
        	System.out.print(iter.next() + " ");
        }
        // output: 0,1,2,3,4,5,6,7
    }
}

class BSTIterator {
	Stack<TreeNode> stack;
	TreeNode root;
    public BSTIterator(TreeNode root) {
        this.root = root;
        stack = new Stack<>();
        while (root != null) {
        	stack.push(root);
        	root = root.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.empty();
    }

    /** @return the next smallest number */
    public int next() {
        if (!hasNext()) {
        	return -1;
        }

        TreeNode curt = stack.pop();
        TreeNode node = curt.right;
        while (node != null) {
       		stack.push(node);
       		node = node.left;
        }
        return curt.val;
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */