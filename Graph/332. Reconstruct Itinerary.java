/*
Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:

    If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
    All airports are represented by three capital letters (IATA code).
    You may assume all tickets form at least one valid itinerary.

Example 1:
tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Return ["JFK", "MUC", "LHR", "SFO", "SJC"].

Example 2:
tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order. 
*/

public class Solution {
	public static void main(String[] args) {
		String[][] tic1 = new String[][]{{"MUC", "LHR"}, {"JFK", "MUC"}, {"SFO", "SJC"}, {"LHR", "SFO"}};
		String[][] tic2 = new String[][]{{"JFK", "SFO"}, {"JFK", "ATL"}, {"SFO", "ATL"}, {"ATL","JFK"}, {"ATL","SFO"}};
		String[][] tic3 = new String[][]{{"JFK", "KUL"}, {"JFK", "NRT"}, {"NRT","JFK"}};

		System.out.println(findItinerary(tic1));
		// output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
		System.out.println(findItinerary(tic2));
		// output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
		System.out.println(findItinerary(tic3));
		// output: ["JFK","NRT","JFK", "KUL"]

	}

	// iterative
    public static List<String> findItinerary(String[][] tickets) {
    	List<String> res = new ArrayList<>();
        if (tickets == null || tickets.length == 0 || tickets[0] == null || tickets[0].length == 0) {
        	return res;
        }

        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for (String[] ticket : tickets) {
        	map.putIfAbsent(ticket[0], new PriorityQueue<>());
        	map.get(ticket[0]).offer(ticket[1]);
        }

        Stack<String> stack = new Stack<>();
        stack.push("JFK");

        while (!stack.empty()) {
        	String from = stack.peek();
        	while (map.containsKey(from) && !map.get(from).isEmpty()) {
                from = stack.peek();
        		String to = map.get(from).poll();
        		stack.push(to);
        	}
            res.add(0, stack.pop());
        }

        return res;
    }

    // recursive
    public static List<String> findItinerary(String[][] tickets) {
        List<String> res = new ArrayList<>();
        if (tickets == null || tickets.length == 0 || tickets[0] == null || tickets[0].length == 0) {
            return res;
        }

        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for (String[] ticket : tickets) {
            map.putIfAbsent(ticket[0], new PriorityQueue<>());
            map.get(ticket[0]).offer(ticket[1]);
        }

        visit(map, "JFK", res);
        return res;
    }

    private static void visit(Map<String, PriorityQueue<String>> map, String from, List<String> res) {
        while (map.containsKey(from) && !map.get(from).isEmpty()) {
            String to = map.get(from).poll();
            visit(map, to, res);
        }
        res.add(0, from);
    }
}