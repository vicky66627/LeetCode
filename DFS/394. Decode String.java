public class Solution {
	public static void main(String[] args) {
		System.out.println(decodeString("3[a]2[bc]"));
		// output: aaabcbc
		System.out.println(decodeString("3[a2[c]]"));
		// output: accaccacc
		System.out.println(decodeString("2[abc]3[cd]ef"));
		// output: abcabccdcdcdef
	}

	// Solution1.1: recursive
	public String decodeString(String s) {
        if (s == null || s.length() == 0) {
        	return "";
        }

        return dfs(s.toCharArray(), new int[1]);
    }

    private String dfs(char[] s,  int[] idx) { 	
    	String res = "";
        
        while (idx[0] < s.length && s[idx[0]] != ']') {
        	if (Character.isDigit(s[idx[0]])) {
        		int num = 0;
        		while (idx[0] < s.length && Character.isDigit(s[idx[0]])) {
        			num = num * 10 + s[idx[0]++] - '0';
        		}
        		idx[0]++;  // '['
        		String tmp = dfs(s, idx);
        		idx[0]++;  // ']'

        		while (num-- > 0) {
        			res += tmp;
        		}
        	} else {
        		res += s[idx[0]++];
        	}
        }

        return res;
    }

    // Solution1.2: recursive
    public static String decodeString(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        return dfs(s, 0, s.length() - 1);
    }

    private static String dfs(String s, int start, int end) {
        StringBuilder res = new StringBuilder();

        int num = 0;
        for (int i = start; i <= end; i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                num = num * 10 + c - '0';
            } else if (c == '[') {
                int cnt = 1;
                int subStart = i + 1;
                while (cnt != 0) {
                    i++;
                    if (s.charAt(i) == '[') {
                        cnt++;
                    } else if (s.charAt(i) == ']') {
                        cnt--;
                    }
                }
                int subEnd = i - 1;
                String sub = dfs(s, subStart, subEnd);
                if (num == 0) {
                    num = 1;
                }

                for (int j = 0; j < num; j++) {
                    res.append(sub);
                }
                num = 0;
            } else {
                res.append(c);
            }
        }

        return res.toString();
    }

    // Solution2: iterative
    public static String decodeString(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        String res = "";
        Stack<Integer> cntStack = new Stack<>();
        Stack<String> resStack = new Stack<>();
        int idx = 0;

        while (idx < s.length()) {
        	if (Character.isDigit(s.charAt(idx))) {
        		int cnt = 0;
        		while (Character.isDigit(s.charAt(idx))) {
        			cnt = 10 * cnt + (s.charAt(idx++) - '0');
        		}
        		cntStack.push(cnt);
        	} else if (s.charAt(idx) == '[') {
        		resStack.push(res);
        		res = "";
        		idx++;
        	} else if (s.charAt(idx) == ']') {
        		StringBuilder tmp = new StringBuilder(resStack.pop());
        		int cnt = cntStack.pop();
        		while (cnt-- > 0) {
        			tmp.append(res);
        		}
        		res = tmp.toString();
        		idx++;
        	} else {
        		res += s.charAt(idx++);
        	}
        }
        return res;
    }
}