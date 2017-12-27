class TreeNode {
	int val;
	TreeNode left, right;
	public TreeNode(int x) { val = x };
}

public class Solution {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(0);
        TreeNode right = new TreeNode(2);
        TreeNode lleft = new TreeNode(0);
        TreeNode lright = new TreeNode(1);
        TreeNode rleft = new TreeNode(1);
        TreeNode rright = new TreeNode(2);
        TreeNode llleft = new TreeNode(0);

        root.left = left;
        root.right = right;
        left.left = lleft;
        left.right = lright;
        right.left = rleft;
        right.right = rright;
        lleft.left = llleft;

    	printArr(findMode(root));
        // output: [0, 1]
        // printArr(findMode(left));
        // output: [0]
        // printArr(findMode(right));
        // output: [2]
    }

    // Solution1: extra O(1) space
    static Integer preVal = null;
    static int[] count = new int[2]; // count[0]: the most frequently occurred count,  count[1]: current node count
    public static int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[0];
        }

        List<Integer> list = new ArrayList<>();
        inorder(root, list);

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }

        return res;
    }

    private static void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        inorder(root.left, list);

        if (preVal != null && root.val == preVal) {
            count[1]++;
        } else {
            count[1] = 1;
        }

        if (count[0] < count[1]) {
            count[0] = count[1];
            list.clear();
            list.add(root.val);
        } else if (count[0] == count[1]) {
            list.add(root.val);
        }

        preVal = root.val;

        inorder(root.right, list);
    }

    // Solution2: O(n) space
    public static int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[0];
        }

        Map<Integer, Integer> map = new HashMap<>();
        int[] freq = new int[1];
        traverse(root, map, freq);
        List<Integer> res = new ArrayList<>();
        for (int val : map.keySet()) {
            if (map.get(val) == freq[0]) {
                res.add(val);
            }
        }
        
        int[] modes = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            modes[i] = res.get(i);
        }

        return modes;
    }

    private static void traverse(TreeNode root, Map<Integer, Integer> map, int[] freq) {
        if (root == null) {
            return;
        }

        map.put(root.val, map.getOrDefault(root.val, 0) + 1);
        if (freq[0] < map.get(root.val)) {
            freq[0] = map.get(root.val);
        }

        traverse(root.left, map, freq);
        traverse(root.right, map, freq);
    }

    private static void printArr(int[] arr) {
        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}