/*
You need to construct a binary tree from a string consisting of parenthesis and integers.

The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. 
The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.

You always start to construct the left child node of the parent first if it exists.

Example:

Input: "4(2(3)(1))(6(5))"
Output: return the tree root node representing the following tree:

       4
     /   \
    2     6
   / \   / 
  3   1 5   

Note:

    There will only be '(', ')', '-' and '0' ~ '9' in the input string.
    An empty tree is represented by "" instead of "()".
*/

class TreeNode {
	int val;
	TreeNode left, right;
	public TreeNode(int x) { val = x };
}

public class Solution {
	public static void main(String[] args) {
        String s1 = "4(2(3)(1))(6(5))";
        String s2 = "1(2)";
        String s3 = "1(2(4))(3(5)(6))"
        String s4 = "-4(2(3)(1))(6(5)(7))";

    	System.out.println(traverseTree(str2tree(s1)));
    	// output: 4,2,6,3,1,5
        System.out.println(traverseTree(str2tree(s2)));
        // output: 1,2
        System.out.println(traverseTree(str2tree(s3)));
        // output: 1,2,3,4,null,5,6
        System.out.println(traverseTree(str2tree(s4)));
        // output: -4,2,6,3,1,5,7
	}

    // Solution1: O(n) time
    public static TreeNode str2tree(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }

        char[] arr = ("(" + s + ")").toCharArray();
        int[] pos = new int[1];
        return constructTree(arr, pos);
    }

    private static TreeNode constructTree(char[] arr, int[] pos) {
        if (pos[0] == arr.length || arr[pos[0]] == ')') {
            return null;
        }

        pos[0]++;
        int val = 0;
        int flag = 1;
        if (arr[pos[0]] == '-') {
            pos[0]++;
            flag = -1;
        }

        while (arr[pos[0]] != '(' && arr[pos[0]] != ')') {
            val = 10 * val + arr[pos[0]] - '0';
            pos[0]++;
        }

        TreeNode root = new TreeNode(val * flag);
        root.left = constructTree(arr, pos);
        root.right = constructTree(arr, pos);
        pos[0]++;

        return root;
    }

    // Solution2: O(n^2) time
	public static TreeNode str2tree(String s) {
		if (s == null || s.length() == 0) {
			return null;
		}

        int firstParen = s.indexOf("(");
        if (firstParen == -1) {
            return new TreeNode(Integer.parseInt(s));
        }

        int val = Integer.parseInt(s.substring(0, firstParen));
        TreeNode root = new TreeNode(val);

        int start = firstParen, parenCnt = 0;
        for (int i = start; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                parenCnt++;
            } else if (s.charAt(i) == ')') {
                parenCnt--;
            }

            if (parenCnt == 0) {
                if (start == firstParen) {
                    root.left = str2tree(s.substring(start + 1, i));
                    start = i + 1;
                } else {
                    root.right = str2tree(s.substring(start + 1, i));
                }
            }
        }
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