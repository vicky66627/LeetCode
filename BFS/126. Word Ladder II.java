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

        String begin4 = "cat";
        String end4 = "fin";
        List<String> wordList3 = Arrays.asList(new String[]{"ion","rev","che","ind","lie","wis","oct","ham","jag","ray","nun","ref","wig","jul","ken","mit","eel","paw","per","ola","pat","old","maj","ell","irk","ivy","beg","fan","rap","sun","yak","sat","fit","tom","fin","bug","can","hes","col","pep","tug","ump","arc","fee","lee","ohs","eli","nay","raw","lot","mat","egg","cat","pol","fat","joe","pis","dot","jaw","hat","roe","ada","mac"});

        System.out.println(findLadders(begin1, end1, wordList1));
        // [["hit","hot","dot","dog","cog"], ["hit","hot","lot","log","cog"]]
        System.out.println(findLadders(begin2, end1, wordList1));
        // [["low", "log". "cog"]]
        System.out.println(findLadders(begin1, end2, wordList2));
        // [hit -> hot -> how]
        System.out.println(findLadders(begin3, end1, wordList1));
        // []
        System.out.println(findLadders(begin1, end3, wordList1));
        // []
        System.out.println(findLadders(begin1, end1, wordList2));
        // []
        System.out.println(findLadders(begin1, begin1, wordList1));
        // [["hit"]]
        System.out.println(findLadders(begin4, end4, wordList3));
        // [["cat","fat","fit","fin"],["cat","fat","fan","fin"],["cat","can","fan","fin"]]
	}

    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
    	if (beginWord.length() != endWord.length()) {
            return res;
        }
        if (beginWord.equals(endWord)) {
            res.add(new ArrayList<String>(Arrays.asList(beginWord)));
            return res;
        }

        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        Set<String> dict = new HashSet<>();

        for (String word : wordList) {
        	dict.add(word);
        }
        beginSet.add(beginWord);
        endSet.add(endWord);

        if (!dict.contains(endWord)) {
            return res;
        }

        Map<String, List<String>> map = new HashMap<>();

        processMap(dict, beginSet, endSet, map, false);

        List<String> inner = new ArrayList<String>(Arrays.asList(beginWord));
        generateList(beginWord, endWord, map, inner, res);

        return res;
    }

    private static boolean processMap(Set<String> dict, Set<String> beginSet, Set<String> endSet, Map<String, List<String>> map, boolean flip) {
        if (beginSet.isEmpty()) {
            return false;
        }

        if (beginSet.size() > endSet.size()) {
            return processMap(dict, endSet, beginSet, map, !flip);
        }
        dict.removeAll(beginSet);
        dict.removeAll(endSet);
        Set<String> visited = new HashSet<>();
        boolean done = false;

        for (String begin : beginSet) {
            char[] chs = begin.toCharArray();

            for (int i = 0; i < chs.length; i++) {
                char old = chs[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    chs[i] = c;
                    String target = new String(chs);

                    String key = flip ? target : begin;
                    String val = flip ? begin : target;
                    List<String> list = map.getOrDefault(key, new ArrayList<>());

                    if (endSet.contains(target)) {
                        list.add(val);
                        map.put(key, list);
                        done = true;
                    }

                    if (!done && dict.contains(target)) {
                        visited.add(target);
                        list.add(val);
                        map.put(key, list);
                    }
                    chs[i] = old;
                }
            }
        }

        return done || processMap(dict, endSet, visited, map, !flip);
    }

    private static void generateList(String beginWord, String endWord, Map<String, List<String>> map, List<String> inner, List<List<String>> res) {
        if (beginWord.equals(endWord)) {
            res.add(new ArrayList<>(inner));
            return;
        }

        if (!map.containsKey(beginWord)) {
            return;
        }

        for (String word : map.get(beginWord)) {
            inner.add(word);
            generateList(word, endWord, map, inner, res);
            inner.remove(inner.size() - 1);
        }
    }
}