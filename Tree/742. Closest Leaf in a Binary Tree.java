/*
Given a binary tree where every node has a unique value, and a target key k, find the value of the nearest leaf node to target k in the tree.

Here, nearest to a leaf means the least number of edges travelled on the binary tree to reach any leaf of the tree. Also, a node is called a leaf if it has no children.

In the following examples, the input tree is represented in flattened form row by row. The actual root tree given will be a TreeNode object.

Example 1:

Input:
root = [1, 3, 2], k = 1
Diagram of binary tree:
          1
         / \
        3   2

Output: 2 (or 3)

Explanation: Either 2 or 3 is the nearest leaf node to the target of 1.

Example 2:

Input:
root = [1], k = 1
Output: 1

Explanation: The nearest leaf node is the root node itself.

Example 3:

Input:
root = [1,2,3,4,null,null,null,5,null,6], k = 2
Diagram of binary tree:
             1
            / \
           2   3
          /
         4
        /
       5
      /
     6

Output: 3
Explanation: The leaf node with value 3 (and not the leaf node with value 6) is nearest to the node with value 2.
*/

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

        System.out.println(findClosestLeaf(root, 4));
        // output: 8
        System.out.println(findClosestLeaf(root, 2));
        // output: 5
        System.out.println(findClosestLeaf(root, 3));
        // output: 6 (or 7)
        System.out.println(findClosestLeaf(root, 1));
        // output: 5 (or 6 or 7)

        TreeNode t1 = new TreeNode(1);
        t1.left = new TreeNode(2);
        t1.right = new TreeNode(3);
        t1.left.left = new TreeNode(4);
        t1.left.left.left = new TreeNode(5);
        t1.left.left.left.left = new TreeNode(6);
        System.out.println(findClosestLeaf(t1, 2));
        // output: 3
	}

    // O(n) time, O(n) space
    public static int findClosestLeaf(TreeNode root, int k) {
    	if (root == null) {
            return -1;
        }

        Map<TreeNode, TreeNode> map = new HashMap<>();  // stores all edges that trace node back to its parent
        // search for node whose value equals k
        TreeNode node = findNode(root, k, map);
        if (node == null) {
            return -1;
        }
        Set<TreeNode> visited = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);

        // BFS: find the shorest path
        while(!queue.isEmpty()) {
            TreeNode curt = queue.poll();
            if (curt.left == null && curt.right == null) {
                return curt.val;
            }
            if (curt.left != null && visited.add(curt.left)) {
                queue.offer(curt.left);
            }
            if (curt.right != null && visited.add(curt.right)) {
                queue.offer(curt.right);
            }
            if (map.containsKey(curt) && visited.add(map.get(curt))) {
                queue.offer(map.get(curt));
            }
        }
        return -1;
    }

    private static TreeNode findNode(TreeNode root, int target, Map<TreeNode, TreeNode> map) {
        if (root == null) {
            return null;
        }

        if (root.val == target) {
            return root;
        }

        if (root.left != null) {
            map.put(root.left, root);
            TreeNode left = findNode(root.left, target, map);
            if (left != null) {
                return left;
            }
        }
        if (root.right != null) {
            map.put(root.right, root);
            TreeNode right = findNode(root.right, target, map);
            if (right != null) {
                return right;
            }
        }
        return null;
    }

}