public class Solution {
	public static void main(String[] args) {
		String begin1 = "hit";
        String end1 = "cog";
        List<String> wordList1 = Arrays.asList(new String[]{"hot","dot","dog","lot","log","cog"});
        List<String> wordList2 = Arrays.asList(new String[]{"hot","dot","dog","lot","log","how"});

        String begin2 = "low";
        String end2 = "how";
        String begin3 = "dan";
        String end3 = "cat";

        System.out.println(ladderLength(begin1, end1, wordList1));
        // 5 hit -> hot -> dot -> dog -> cog
        System.out.println(ladderLength(begin2, end1, wordList1));
        // 4 low -> log -> cog
        System.out.println(ladderLength(begin1, end2, wordList2));
        // 3 hit -> hot -> how
        System.out.println(ladderLength(begin3, end1, wordList1));
        // 0
        System.out.println(ladderLength(begin1, end3, wordList1));
        // 0
        System.out.println(ladderLength(begin1, end1, wordList2));
        // 0
	}

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
    	if (beginWord.length() != endWord.length()) {
            return 0;
        }
        if (beginWord.equals(endWord)) {
            return 1;
        }

        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        Set<String> dict = new HashSet<>();

        for (String word : wordList) {
        	dict.add(word);
        }
        beginSet.add(beginWord);
        endSet.add(endWord);

        if (dict.contains(beginWord)) {
        	dict.remove(beginWord);
        }
        if (dict.contains(endWord)) {
        	dict.remove(endWord);
        } else {
        	return 0;
        }

        int len = 2;

        while (!beginSet.isEmpty()) {
        	if (beginSet.size() > endSet.size()) {
        		Set<String> set = beginSet;
        		beginSet = endSet;
        		endSet = set;
        	}

        	Set<String> visited = new HashSet<>();
        	for (String word : beginSet) {
        		char[] chs = word.toCharArray();

        		for (int i = 0; i < chs.length; i++) {
        			char old = chs[i];
        			for (char c = 'a'; c <= 'z'; c++) {
        				chs[i] = c;
        				String target = new String(chs);

        				if (endSet.contains(target)) {
        					return len;
        				}

        				if (dict.contains(target)) {
        					visited.add(target);
        					dict.remove(target);
        				}
        				chs[i] = old;
        			}
        		}
        	}
        	beginSet = visited;
        	len++;
        }

        return 0;
    }
}