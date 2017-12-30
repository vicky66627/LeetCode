/*
 There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

Example 1:
Given the following words in dictionary,

[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]

The correct order is: "wertf".

Example 2:
Given the following words in dictionary,

[
  "z",
  "x"
]

The correct order is: "zx".

Example 3:
Given the following words in dictionary,

[
  "z",
  "x",
  "z"
]

The order is invalid, so return "".

Note:

    You may assume all letters are in lowercase.
    You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
    If the order is invalid, return an empty string.
    There may be multiple valid order of letters, return any one of them is fine.
*/

public class Solution {
	public static void main(String[] args) {
		String[] words1 = new String[]{"wrt", "wrf", "er", "ett", "rftt"};
        String[] words2 = new String[]{"z", "x"};
        String[] words3 = new String[]{"z", "x", "z"};
        String[] words4 = new String[]{"za","zb","ca","cb"};

        System.out.println(alienOrder(words1));
        // output: "wertf"
        System.out.println(alienOrder(words2));
        // output: "zx"
        System.out.println(alienOrder(words3));
        // output: ""
        System.out.println(alienOrder(words4));
        // output: "azbc" or "zcab" or...
    }

    // Solution1: BFS
    public static String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }

        Map<Character, Set<Character>> dict = new HashMap<>();
        Map<Character, Integer> indegrees = new HashMap<>();

        for (String word : words) {
            for (char c : word.toCharArray()) {
                dict.put(c, new HashSet<>());
                indegrees.put(c, 0);
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            int idx = 0;
            while (idx < words[i].length() && idx < words[i + 1].length()) {
                char curt = words[i].charAt(idx);
                char next = words[i + 1].charAt(idx);
                if (curt == next) {
                    idx++;
                } else {
                    if (!dict.get(curt).contains(next)) {
                        dict.get(curt).add(next);
                        indegrees.put(next, indegrees.getOrDefault(next, 0) + 1);
                    }
                    break;
                }
            }
        }

        Queue<Character> queue = new LinkedList<>();
        for (char node : indegrees.keySet()) {
            if (indegrees.get(node) == 0) {
                queue.offer(node);
            }
        }
        
        StringBuilder res = new StringBuilder();
        while (!queue.isEmpty()) {
            char curt = queue.poll();
            res.append(curt);
            for (char next : dict.get(curt)) {
                indegrees.put(next, indegrees.get(next) - 1);
                if (indegrees.get(next) == 0) {
                    queue.offer(next);
                }
            }
        }
        
        return indegrees.size() == res.length() ? res.toString() : "";
    }

    // Solution2: DFS
    public static String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }

        Map<Character, Set<Character>> dict = new HashMap<>();

        for (String word : words) {
            for (char c : word.toCharArray()) {
                dict.put(c, new HashSet<>());
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            int idx = 0;
            while (idx < words[i].length() && idx < words[i + 1].length()) {
                char curt = words[i].charAt(idx);
                char next = words[i + 1].charAt(idx);
                if (curt == next) {
                    idx++;
                } else {
                    dict.get(curt).add(next);
                    break;
                }
            }
        }

        StringBuilder res = new StringBuilder();
        boolean[] visited = new boolean[26];
        for (char curt : dict.keySet()) {
            if (hasCycle(dict, res, curt, visited, new boolean[26])) {
                return "";
            }
        }

        return res.reverse().toString();
    }

    private static boolean hasCycle(Map<Character, Set<Character>> dict, StringBuilder res, char curt, boolean[] visited, boolean[] onpath) {
        if (visited[curt - 'a']) {
            return false;
        }

        onpath[curt - 'a'] = visited[curt - 'a'] = true;
        for (char next : dict.get(curt)) {
            if (onpath[next - 'a'] || hasCycle(dict, res, next, visited, onpath)) {
                return true;
            }
        }
        onpath[curt - 'a'] = false;
        res.append(curt);

        return false;
    }
}