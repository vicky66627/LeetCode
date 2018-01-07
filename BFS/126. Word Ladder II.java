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

    // Two-end BFS + DFS: fast
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

    // another solution: BFS + DFS
    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        if (beginWord.length() != endWord.length()) {
            return res;
        }
        if (beginWord.equals(endWord)) {
            res.add(new ArrayList<String>(Arrays.asList(beginWord)));
            return res;
        }

        Set<String> dict = new HashSet<>(wordList);

        if (dict.contains(beginWord)) {
            dict.remove(beginWord);
        }
        if (!dict.contains(endWord)) {
            return res;
        }

        Map<String, List<String>> map = new HashMap<>();
        Map<String, Integer> dist = new HashMap<>();

        processMap(dict, beginWord, endWord, map, dist);
//        System.out.println(map + " " + dist);
        generateList(beginWord, endWord, map, dist, new ArrayList<>(), res);

        return res;
    }

    // BFS
    private static void processMap(Set<String> dict, String beginWord, String endWord, Map<String, List<String>> map, Map<String, Integer> dist) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        dist.put(beginWord, 0);

        boolean found = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curt = queue.poll();
                int curtDist = dist.get(curt);
                List<String> neighbors = getNeighbors(curt, dict);
                for (String neighbor : neighbors) {
                    map.putIfAbsent(curt, new ArrayList<>());
                    map.get(curt).add(neighbor);

                    if (!dist.containsKey(neighbor)) {  // check if visited
                        dist.put(neighbor, curtDist + 1);
                        if (endWord.equals(neighbor)) {
                            found = true;
                        } else {
                            queue.offer(neighbor);
                        }
                    }
                }
                if (found) {
                    break;
                }
            }
         }
    }

    private static List<String> getNeighbors(String node, Set<String> dict) {
        List<String> res = new ArrayList<>();
        char[] chs = node.toCharArray();

        for (int i = 0; i < chs.length; i++) {
            char old = chs[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == old) {
                    continue;
                }
                chs[i] = c;
                String target = new String(chs);

                if (dict.contains(target)) {
                    res.add(target);
                }
            }
            chs[i] = old;
        }
        return res;
    }

    // DFS
    private static void generateList(String curt, String endWord, Map<String, List<String>> map, Map<String, Integer> dist, List<String> inner, List<List<String>> res) {
        inner.add(curt);
        if (curt.equals(endWord)) {
            res.add(new ArrayList<>(inner));
            inner.remove(inner.size() - 1);
            return;
        }

        if (!map.containsKey(curt)) {
            return;
        }

        for (String next : map.get(curt)) {
            if (dist.get(next) == dist.get(curt) + 1) {
                generateList(next, endWord, map, dist, inner, res);
            }

        }
        inner.remove(inner.size() - 1);
    }
}