class TreeNode {
	int val;
	TreeNode left, right;
	public TreeNode(int x) { val = x };
}

public class Solution {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        TreeNode lleft = new TreeNode(4);
        TreeNode lright = new TreeNode(1);
        TreeNode rleft = new TreeNode(-6);
        TreeNode rright = new TreeNode(7);
        TreeNode llleft = new TreeNode(0);

        root.left = left;
        root.right = right;
        left.left = lleft;
        left.right = lright;
        right.left = rleft;
        right.right = rright;
        lleft.left = llleft;

    	printArr(findFrequentTreeSum(root));
    	// output: [4,7]
	}

	public static int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) {
            return new int[0];
        }

        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> countMap = new HashMap<>();
        treeSum(root, countMap);
        int freq = 0;
        for (int val : countMap.keySet()) {
            if (freq < countMap.get(val)) {
                freq = countMap.get(val);
                list.clear();
                list.add(val);
            } else if (freq == countMap.get(val)) {
                list.add(val);
            }
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }

        return res;
    }

    private static int treeSum(TreeNode root, Map<Integer, Integer> countMap) {
        if (root == null) {
            return 0;
        }

        int val = root.val + treeSum(root.left, countMap) + treeSum(root.right, countMap);
        countMap.put(val, countMap.getOrDefault(val, 0) + 1);

        return val;
    }

	private static void printArr(int[] arr) {
        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}