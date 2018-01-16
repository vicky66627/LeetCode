public class Solution {
	public static void main(String[] args) {
		String board1 = "WRRBBW";
		String hand1 = "RB";
		String board2 = "WWRRBBWW";
		String hand2 = "WRBRW";
		String board3 = "G";
		String hand3 = "GGGGG";
		String board4 = "RBYYBBRRB";
		String hand4 = "YRBGB";
		String board5 = "RRWWRRBBRR";
		String hand5 = "WB";

		System.out.println(findMinStep(board1, hand1));
		// output: -1
		System.out.println(findMinStep(board2, hand2));
		// output: 2
		System.out.println(findMinStep(board3, hand3));
		// output: 2
		System.out.println(findMinStep(board4, hand4));
		// output: 3
		System.out.println(findMinStep(board5, hand5));
		// output: -1  (2?)
	}

	static final int MAXCNT = 6; // the max balls you need will not exceed 6 since "The number of balls in your hand won't exceed 5"
    public static int findMinStep(String board, String hand) {
        if (board == null || board.length() == 0) {
        	return 0;
        }

        int[] handCnt = new int[26];
        for (char c : hand.toCharArray()) {
        	handCnt[c - 'A']++;
        }

        int res = dfs(board + "#", handCnt);  // append a "#" to avoid special process while j == board.length()
        return res == MAXCNT ? -1 : res;
    }

    private static int dfs(String board, int[] handCnt) {
    	board = removeConsecutive(board);
    	if (board.equals("#")) {
    		return 0;
    	}

    	int res = MAXCNT;
    	for (int i = 0, j = 0; j < board.length(); j++) {
    		if (board.charAt(i) == board.charAt(j)) {
    			continue;
    		}
    		int need = 3 - (j - i);   // balls need to remove current consecutive balls
    		if (handCnt[board.charAt(i) - 'A'] >= need) {
    			handCnt[board.charAt(i) - 'A'] -= need;
    			res = Math.min(res, need + dfs(board.substring(0, i) + board.substring(j), handCnt));
    			handCnt[board.charAt(i) - 'A'] += need;
    		}
    		i = j;
    	}
    	return res;
    }

    private static String removeConsecutive(String board) {
    	for (int i = 0, j = 0; j < board.length(); j++) {
    		if (board.charAt(i) == board.charAt(j)) {
    			continue;
    		}
    		if (j - i >= 3) {
    			return removeConsecutive(board.substring(0, i) + board.substring(j));
    		} else {
    			i = j;
    		}
    	}
    	return board;
    }
}