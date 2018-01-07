class Employee {
    // It's the unique id of each node;
    // unique id of this employee
    public int id;
    // the importance value of this employee
    public int importance;
    // the id of direct subordinates
    public List<Integer> subordinates;
}

public class Solution {
	public static void main(String[] args) {
        Employee e1 = new Employee();
        e1.id = 1;
        e1.importance = 15;
        e1.subordinates = Arrays.asList(new Integer[]{2});
        Employee e2 = new Employee();
        e2.id = 2;
        e2.importance = 10;
        e2.subordinates = Arrays.asList(new Integer[]{3});
        Employee e3 = new Employee();
        e3.id = 3;
        e3.importance = 5;

        Employee ee1 = new Employee();
        ee1.id = 1;
        ee1.importance = 5;
        ee1.subordinates = Arrays.asList(new Integer[]{2, 3});
        Employee ee2 = new Employee();
        ee2.id = 2;
        ee2.importance = 3;
        Employee ee3 = new Employee();
        ee3.id = 3;
        ee3.importance = 3;

        System.out.println(getImportance(Arrays.asList(new Employee[]{e1, e2, e3}), 1));
        // output: 30  (15 + 10 + 5)
        System.out.println(getImportance(Arrays.asList(new Employee[]{ee1, ee2, ee3}), 1));
        // output: 11  (5 + 3 + 3)
    }

    // Solution1: BFS
    public static int getImportance(List<Employee> employees, int id) {
        if (employees == null || employees.size() == 0) {
            return 0;
        }

        Map<Integer, Employee> map = new HashMap<>();
        for (Employee em : employees) {
            map.put(em.id, em);
        }
        if (!map.containsKey(id)) {
            return 0;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(id);
        int res = 0;
        while (!queue.isEmpty()) {
            int curtId = queue.poll();
            Employee curt = map.get(curtId);
            res += curt.importance;
            if (curt.subordinates == null || curt.subordinates.isEmpty()) {
                continue;
            }
            for (int nextId : curt.subordinates) {
                queue.offer(nextId);
            }
        }

        return res;
    }

    // Solution2: DFS
    public static int getImportance(List<Employee> employees, int id) {
        if (employees == null || employees.size() == 0) {
            return 0;
        }

        Map<Integer, Employee> map = new HashMap<>();
        for (Employee em : employees) {
            map.put(em.id, em);
        }
        if (!map.containsKey(id)) {
            return 0;
        }

        int[] res = new int[1];
        dfs(map, id, res);

        return res[0];
    }

    private static void dfs(Map<Integer, Employee> map, int curtId, int[] res) {
    	Employee curt = map.get(curtId);
    	res[0] += curt.importance;
    	if (curt.subordinates == null || curt.subordinates.size() == 0) {
    		return;
    	}

    	for (int nextId : curt.subordinates) {
    		dfs(map, nextId, res);
    	}
    }
}