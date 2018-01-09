public class Solution {
	public static void main(String[] args) {
		String[] d1 = new String[]{"0201","0101","0102","1212","2002"};
		String t1 = "0202";
		String[] d2 = new String[]{"8888"};
		String t2 = "0009";
		String[] d3 = new String[]{"8887","8889","8878","8898","8788","8988","7888","9888"};
		String t3 = "8888";
		String[] d4 = new String[]{"0000"};
		String t4 = "8888";

		System.out.println(openLock(d1, t1));
        // output: 6 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"
        System.out.println(openLock(d2, t2));
        // output: 1
        System.out.println(openLock(d3, t3));
        // output: -1
        System.out.println(openLock(d4, t4));
        // output: -1
	}

    public static int openLock(String[] deadends, String target) {
        Set<String> deadSet = new HashSet<>();
        for (String dead : deadends) {
        	deadSet.add(dead);
        }
        if (deadSet.contains("0000")) {
        	return -1;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        Set<String> visited = new HashSet<>();
        visited.add("0000");

        int len = 0;
        while (!queue.isEmpty()) {
        	int size = queue.size();
        	for (int i = 0; i < size; i++) {
	        	String curt = queue.poll();

	        	if (curt.equals(target)) {
	        		return len;
	        	}
	        	char[] chs = curt.toCharArray();
	        	for (int j = 0; j < chs.length; j++) {
                    char old = chs[j];
                    for (int offset = -1; offset <= 1; offset += 2) {
                        chs[j] = (char)((chs[j] - '0' + 10 + offset) % 10 + '0');
                        String newStr = new String(chs);
                        if (!deadSet.contains(newStr) && !visited.contains(newStr)) {
                            queue.offer(newStr);
                            visited.add(newStr);
                        }
                        chs[j] = old;
                    }
                }
	        }
	        len++;
        }
        return -1;
    }
}