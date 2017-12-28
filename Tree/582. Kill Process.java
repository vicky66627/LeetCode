/*
Given n processes, each process has a unique PID (process id) and its PPID (parent process id).

Each process only has one parent process, but may have one or more children processes. This is just like a tree structure. Only one process has PPID that is 0, which means this process has no parent process. All the PIDs will be distinct positive integers.

We use two list of integers to represent a list of processes, where the first list contains PID for each process and the second list contains the corresponding PPID.

Now given the two lists, and a PID representing a process you want to kill, return a list of PIDs of processes that will be killed in the end. You should assume that when a process is killed, all its children processes will be killed. No order is required for the final answer.

Example 1:

Input: 
pid =  [1, 3, 10, 5]
ppid = [3, 0, 5, 3]
kill = 5
Output: [5,10]
Explanation: 
           3
         /   \
        1     5
             /
            10
Kill 5 will also kill 10.

Note:

    The given kill id is guaranteed to be one of the given PIDs.
    n >= 1.

*/

public class Solution {
	public static void main(String[] args) {
        Integer[] pidArr = new Integer[]{1,2,3,4,5,6,7,8};
        Integer[] ppidArr = new Integer[]{0,1,1,2,2,3,3,4};

        List<Integer> pid = Arrays.asList(pidArr);
        List<Integer> ppid = Arrays.asList(ppidArr);
        System.out.println(killProcess(pid, ppid, 4));
        // output: [4,8]
        System.out.println(killProcess(pid, ppid, 2));
        // output: [2,4,8,5]
        System.out.println(killProcess(pid, ppid, 3));
        // output: [3,6,7]
        System.out.println(killProcess(pid, ppid, 1));
        // output: [1,2,3,4,5,6,7,8]
	}

    public static List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
    	List<Integer> res = new ArrayList<>();
        if (pid == null || ppid == null || pid.size() != ppid.size()) {
        	return res;
        }

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < pid.size(); i++) {
        	map.putIfAbsent(ppid.get(i), new ArrayList<>());
        	map.get(ppid.get(i)).add(pid.get(i));
        }

        res.add(kill);
        killDescendants(map, res, kill);

        return res;
    }

    private static void killDescendants(Map<Integer, List<Integer>> map, List<Integer> res, int kill) {
    	if (map.containsKey(kill)) {
        	res.addAll(map.get(kill));

        	for (int killId : map.get(kill)) {
        		killDescendants(map, res, killId);
        	}
        }
    }
}