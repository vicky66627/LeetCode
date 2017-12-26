/*
Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

For example:
Given the following binary tree,

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---

You should return [1, 3, 4]. 
*/

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

    	System.out.println(rightSideView(root));
    	// output: [1,3,7,8]
    }

    // Solution1: DFS
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        dfs(root, res, 0);
        return res;
    }

    private static void dfs(TreeNode root, List<Integer> res, int curtLvl) {
        if (root == null) {
            return;
        }
        if (curtLvl == res.size()) {
            res.add(root.val);
        }

        dfs(root.right, res, curtLvl + 1);
        dfs(root.left, res, curtLvl + 1);
    }

    // Solution2: BFS - level order
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curt = queue.poll();
                if (curt.left != null) {
                    queue.offer(curt.left);
                }
                if (curt.right != null) {
                    queue.offer(curt.right);
                }
                if (i == size - 1) {
                    res.add(curt.val);
                }
            }
        }

        return res;
    }
}