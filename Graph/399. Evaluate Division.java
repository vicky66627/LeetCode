public class Solution {
	public static void main(String[] args) {
        String[][] equations = new String[][]{{"a", "b"}, {"b", "c"}};
        double[] values = new double[]{2.0, 3.0};
        String[][] queries = new String[][]{{"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}};

        printArr(calcEquation(equations, values, queries));
        // output: [6.0, 0.5, -1.0, 1.0, -1.0]
    }

    public static double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        if (equations == null || values == null || equations.length != values.length) {
            return new double[0];
        }

        Map<String, List<String>> pairs = new HashMap<>();
        Map<String, List<Double>> valuePairs = new HashMap<>();

        for (int i = 0; i < equations.length; i++) {
            String[] equation = equations[i];
            if (!pairs.containsKey(equation[0])) {
                pairs.put(equation[0], new ArrayList<>());
                valuePairs.put(equation[0], new ArrayList<>());
            }
            if (!pairs.containsKey(equation[1])) {
                pairs.put(equation[1], new ArrayList<>());
                valuePairs.put(equation[1], new ArrayList<>());
            }
            pairs.get(equation[0]).add(equation[1]);
            pairs.get(equation[1]).add(equation[0]);
            valuePairs.get(equation[0]).add(values[i]);
            valuePairs.get(equation[1]).add(1 / values[i]);
        }

        double[] res = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String[] query = queries[i];
            res[i] = dfs(query[0], query[1], pairs, valuePairs, new HashSet<String>(), 1.0);
        }
        return res;
    }

    private static double dfs(String start, String end, Map<String, List<String>> pairs, Map<String, List<Double>> valuePairs, Set<String> visited, double value) {
        if (visited.contains(start)) {
            return -1.0;
        }
        if (!pairs.containsKey(start)) {
            return -1.0;
        }
        if (start.equals(end)) {
            return value;
        }
        visited.add(start);

        List<String> strList = pairs.get(start);
        List<Double> valueList = valuePairs.get(start);
        for (int i = 0; i < strList.size(); i++) {
            double res = dfs(strList.get(i), end, pairs, valuePairs, visited, value * valueList.get(i));
            if (res != -1.0) {
                return res;
            }
        }

        visited.remove(start);
        return -1.0;
    }

    private static void printArr(double[] arr) {
        for (double val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}