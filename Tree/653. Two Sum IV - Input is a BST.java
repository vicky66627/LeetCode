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
        TreeNode llleft = new TreeNode(-1);

        root.left = left;
        root.right = right;
        left.left = lleft;
        left.right = lright;
        right.left = rleft;
        right.right = rright;
        lleft.left = llleft;

        System.out.println(findTarget(root, 10));
        // output: true
        System.out.println(findTarget(root, 13));
        // output: true
        System.out.println(findTarget(root, 14));
        // output: false
        System.out.println(findTarget(root, -1));
        // output: false
    }

    // Solution1: O(nlogn) time, O(h) space
    public static boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }

        return dfs(root, root, k);
    }

    private static boolean dfs(TreeNode root, TreeNode curt, int target) {
        if (curt == null) {
            return false;
        }

        return search(root, curt, target - curt.val) || dfs(root, curt.left, target) || dfs(root, curt.right, target);
    }

    private static boolean search(TreeNode root, TreeNode curt, int value) {
        if (root == null) {
            return false;
        }

        return (root.val == value && root != curt) 
            || (root.val > value && search(root.left, curt, value)) 
            || (root.val < value && search(root.right, curt, value));
    }

    // Solution2: O(n) time, O(n) space
    public static boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }

        Set<Integer> set = new HashSet<>();
        return find(root, k, set);
    }

    private static boolean find(TreeNode root, int target, Set<Integer> set) {
        if (root == null) {
            return false;
        }

        if (set.contains(target - root.val)) {
            return true;
        }

        set.add(root.val);

        return find(root.left, target, set) || find(root.right, target, set);
    }
}