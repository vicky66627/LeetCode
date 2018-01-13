/*
Given a string representing arbitrarily nested ternary expressions, calculate the result of the expression. You can always assume that the given expression is valid and only consists of digits 0-9, ?, :, T and F (T and F represent True and False respectively).

Note:

    The length of the given string is ≤ 10000.
    Each number will contain only one digit.
    The conditional expressions group right-to-left (as usual in most languages).
    The condition will always be either T or F. That is, the condition will never be a digit.
    The result of the expression will always evaluate to either a digit 0-9, T or F.

Example 1:

Input: "T?2:3"

Output: "2"

Explanation: If true, then result is 2; otherwise result is 3.

Example 2:

Input: "F?1:T?4:5"

Output: "4"

Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:

             "(F ? 1 : (T ? 4 : 5))"                   "(F ? 1 : (T ? 4 : 5))"
          -> "(F ? 1 : 4)"                 or       -> "(T ? 4 : 5)"
          -> "4"                                    -> "4"

Example 3:

Input: "T?T?F:5:3"

Output: "F"

Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:

             "(T ? (T ? F : 5) : 3)"                   "(T ? (T ? F : 5) : 3)"
          -> "(T ? F : 3)"                 or       -> "(T ? F : 5)"
          -> "F"                                    -> "F"

*/

public class Solution {
	public static void main(String[] args) {
		String s1 = "T?2:3";
		String s2 = "F?1:T?4:5";
		String s3 = "T?T?F:5:3";
		String s4 = "T?F?25:38:47";

		System.out.println(parseTernary(s1));
		// output: 2
		System.out.println(parseTernary(s2));
		// output: 4
		System.out.println(parseTernary(s3));
		// output: F
		System.out.println(parseTernary(s4));
		// output: 38
	}

	// Solution1: recursive
    public static String parseTernary(String expression) {
        if (expression == null || expression.length() == 0) {
        	return "";
        }

        int totalMark = 0;
        for (char c : expression.toCharArray()) {
        	if (c == '?') {
        		totalMark++;
        	}
        }

        return dfs(expression, 0, expression.length() - 1, totalMark);
    }

    private static String dfs(String expression, int start, int end, int totalMark) {
    	if (totalMark == 0) {
    		return new String(expression.substring(start, end + 1));
    	}

    	int cnt = 0;
    	int cntMark = 0;
    	int idx = start;
    	for (; idx <= end; idx++) {
    		char c = expression.charAt(idx);
    		if (c == '?') {
    			cnt++;
    			cntMark++;
    		} else if (c == ':') {
    			if (--cnt == 0) {
    				break;
    			}
    		}
    	}

    	return expression.charAt(start) == 'T' ? dfs(expression, start + 2, idx - 1, cntMark - 1) : dfs(expression, idx + 1, end, totalMark - cntMark);
    }

    // Solution2: iterative - use stack
    public static String parseTernary(String expression) {
        if (expression == null || expression.length() == 0) {
        	return "";
        }

        Stack<String> stack = new Stack<>();
        for (int i = expression.length() - 1; i >= 0; i--) {
        	char c = expression.charAt(i);
        	if (!stack.empty() && stack.peek().equals("?")) {
        		stack.pop();   // pop "?"
        		String first = stack.pop();
        		stack.pop();   // pop ":"
        		String sec = stack.pop();
        		if (c == 'T') {
        			stack.push(first);
        		} else {
        			stack.push(sec);
        		}
        	} else {
	        	if (c >= '0' && c <= '9') {
	        		int end = i;
		        	while (i >= 0 && expression.charAt(i) >= '0' && expression.charAt(i) <= '9') {
                        i--;
                    }
                    i++;
                    stack.push(expression.substring(i, end + 1));
		        } else {
		        	stack.push(c + "");
		        }
		    }
        }
        return stack.empty() ? "" : stack.peek();
    }
}