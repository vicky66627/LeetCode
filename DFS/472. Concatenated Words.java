public class Solution {
	public static void main(String[] args) {
		String[] words = new String[]{"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};
		System.out.println(findAllConcatenatedWordsInADict(words));
		// output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
	}

	// Solution1: DFS + Trie
    public static List<String> findAllConcatenatedWordsInADict(String[] words) {
    	List<String> res = new ArrayList<>();
        if (words == null || words.length == 0) {
        	return res;
        }

        TrieNode root = new TrieNode();
        for (String word : words) {      // construct Trie
        	if (word.length() == 0) {
        		continue;
        	}
        	addWord(word.toCharArray(), root);
        }

        for (String word : words) {     // test word is a concatenated word or not
        	if (word.length() == 0) {
        		continue;
        	}
        	if (testWord(word.toCharArray(), root, 0, 0)) {
        		res.add(word);
        	}
        }
        return res;
    }

    private static boolean testWord(char[] chs, TrieNode root, int start, int cnt) {
    	TrieNode curt = root;
        for (int i = start; i < chs.length; i++) {
        	curt = curt.next[chs[i] - 'a'];
            if (curt == null) {
                return false;
            }

            if (curt.isWord) {
                if (i == chs.length - 1) {
                    return cnt >= 1;
                }

                if (testWord(chs, root, i + 1, cnt + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void addWord(char[] chs, TrieNode root) {
    	TrieNode curt = root;
    	for (char c : chs) {
    		if (curt.next[c - 'a'] == null) {
    			curt.next[c - 'a'] = new TrieNode();
    		}
    		curt = curt.next[c - 'a'];
    	}
    	curt.isWord = true;
    }

    // Solution2: DP
    public static List<String> findAllConcatenatedWordsInADict(String[] words) {
    	List<String> res = new ArrayList<>();
        if (words == null || words.length == 0) {
        	return res;
        }
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        Set<String> preWords = new HashSet<>();
        for (String word : words) {
        	if (word.length() == 0) {
                continue;
            }
        	if (canForm(word, preWords)) {
        		res.add(word);
        	}
        	preWords.add(word);
        }
        return res;
    }

    private static boolean canForm(String word, Set<String> dict) {
    	if (dict.isEmpty()) {
    		return false;
    	}
    	boolean[] dp = new boolean[word.length() + 1];
    	dp[0] = true;

    	for (int i = 1; i <= word.length(); i++) {
    		for (int j = 0; j < i; j++) {
    			if (dict.contains(word.substring(j, i))) {
    				dp[i] = true;
    				break;
    			}
    		}
    	}
    	return dp[word.length()];
    }
}

class TrieNode {
	TrieNode[] next;
	boolean isWord;

	public TrieNode() {
		next = new TrieNode[26];
	}
}