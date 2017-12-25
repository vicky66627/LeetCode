/*
Given the following binary tree,

         1
       /  \
      2    3
     / \    \
    4   5    7

After calling your function, the tree should look like:

         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL
*/

class TreeLinkNode {
	int val;
	TreeLinkNode left, right, next;
	public TreeLinkNode(int x) { val = x; }
}

public class Solution {
	public static void main(String[] args) {
		TreeLinkNode root = new TreeLinkNode(1);
		TreeLinkNode left = new TreeLinkNode(2);
		TreeLinkNode right = new TreeLinkNode(3);
		TreeLinkNode lleft = new TreeLinkNode(4);
		TreeLinkNode lright = new TreeLinkNode(5);
		// TreeLinkNode rleft = new TreeLinkNode(6);
		TreeLinkNode rright = new TreeLinkNode(7);

		root.left = left;
		root.right = right;
		left.left = lleft;
		left.right = lright;
		// right.left = rleft;
		right.right = rright;

		connect(root);
		// output: [[1],[2,3],[4,5,7]]

		while (root != null) {
			TreeLinkNode curt = root;
			while (curt != null) {
				System.out.print(curt.val + " ");
				curt = curt.next;
			}
			System.out.println();
			root = root.left;
		}
	}

	public static void connect(TreeLinkNode root) {
        if (root == null) {
        	return;
        }

        TreeLinkNode lvlHead = null; // head of the next level
        TreeLinkNode prev = null;  // the leading node on the next level
        TreeLinkNode curt = root;  // current node of current level

        while (curt != null) {
        	while (curt != null) {
	        	if (curt.left != null) {
	        		if (prev == null) {
	        			lvlHead = curt.left;
	        		} else {
	        			prev.next = curt.left;
	        		}
	        		prev = curt.left;
	        	}

	        	if (curt.right != null) {
	        		if (prev == null) {
	        			lvlHead = curt.right;
	        		} else {
	        			prev.next = curt.right;
	        		}
	        		prev = curt.right;
	        	}
	        	curt = curt.next;
			}
			curt = lvlHead;
	        lvlHead = null;
	       	prev = null;
        }
    }
}
