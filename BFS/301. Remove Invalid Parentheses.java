public class Solution {
	public static void main(String[] args) {
        String s1 = "()())()";
        String s2 = "(a)())()";
        String s3 = ")(";

        System.out.println(removeInvalidParentheses(s1));
        // output: ["()()()", "(())()"]
        System.out.println(removeInvalidParentheses(s2));
        // output: ["(a)()()", "(a())()"]
        System.out.println(removeInvalidParentheses(s3));
        // output: [""]
    }

	// Solution1: BFS
    public static List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null) {
            return res;
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(s);
        visited.add(s);
        boolean found = false;

        while (!queue.isEmpty()) {
            String curt = queue.poll();
            if (isValid(curt)) {
                res.add(curt);
                found = true;
            }
            if (found) {
                continue;
            }

            for (int i = 0; i < curt.length(); i++) {
                if (curt.charAt(i) != '(' && curt.charAt(i) != ')') {
                    continue;
                }

                String sub = curt.substring(0, i) + curt.substring(i + 1);
                if (visited.add(sub)) {
                    queue.offer(sub);
                }
            }
        }

        return res;
    }

    private static boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                cnt++;
            }
            if (s.charAt(i) == ')') {
                cnt--;
            }
            if (cnt < 0) {
                return false;
            }
        }

        return cnt == 0;
    }

    // Solution2: DFS
    public static List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null) {
            return res;
        }

        remove(s, res, 0, 0, new char[]{'(', ')'});
        return res;
    }

    private static void remove(String s, List<String> res, int last_i, int last_j, char[] pair) {
    	int cnt = 0;
    	for (int i = last_i; i < s.length(); i++) {
    		if (s.charAt(i) == pair[0]) {
    			cnt++;
    		} else if (s.charAt(i) == pair[1]) {
    			cnt--;
    		}
    		if (cnt >= 0) {
    			continue;
    		}
    		for (int j = last_j; j <= i; j++) {
    			if (s.charAt(j) == pair[1] && (j == last_j || s.charAt(j - 1) != pair[1])) {
    				remove(s.substring(0, j) + s.substring(j + 1), res, i, j, pair);
    			}
    		}
    		return;
    	}

    	String reversed = new StringBuilder(s).reverse().toString();
    	if (pair[0] == '(') {
    		remove(reversed, res, 0, 0, new char[]{')', '('});
    	} else {
    		res.add(reversed);
    	}
    }
}