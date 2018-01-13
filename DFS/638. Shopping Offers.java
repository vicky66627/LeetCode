public class Solution {
	public static void main(String[] args) {
		List<Integer> price1 = Arrays.asList(new Integer[]{2,5});
		List<Integer> sp1 = Arrays.asList(new Integer[]{3,0,5});
		List<Integer> sp2 = Arrays.asList(new Integer[]{1,2,10});
		List<List<Integer>> special1 = new ArrayList<>();
		special1.add(sp1);
		special1.add(sp2);
		List<Integer> needs1 = Arrays.asList(new Integer[]{3,2});

		List<Integer> price2 = Arrays.asList(new Integer[]{2,3,4});
		List<Integer> spe1 = Arrays.asList(new Integer[]{1,1,0,4});
		List<Integer> spe2 = Arrays.asList(new Integer[]{2,2,1,9});
		List<List<Integer>> special2 = new ArrayList<>();
		special2.add(spe1);
		special2.add(spe2);
		List<Integer> needs2 = Arrays.asList(new Integer[]{1,2,1});

		System.out.println(shoppingOffers(price1, special1, needs1));
		// output: 14
		System.out.println(shoppingOffers(price2, special2, needs2));
		// output: 11
	}

	// DFS with memoization
    public static int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        if (price == null || needs == null || price.size() != needs.size()) {
        	return 0;
        }

        Map<List<Integer>, Integer> map = new HashMap<>();
        return shopping(price, special, needs, 0, map);
    }

    private static int shopping(List<Integer> price, List<List<Integer>> special, List<Integer> needs, int idx, Map<List<Integer>, Integer> map) {
    	if (map.containsKey(needs)) {
    		return map.get(needs);
    	}

    	int res = dotProduct(price, needs);
    	for (int i = idx; i < special.size(); i++) {
    		List<Integer> sp = special.get(i);
    		List<Integer> remain = new ArrayList(needs);
    		for (int j = 0; j <= needs.size(); j++) {
    			if (j < needs.size()) {
    				int diff = needs.get(j) - sp.get(j);
    				if (diff < 0) {
    					break;
    				}
    				remain.set(j, diff);
    			} else {
    				res = Math.min(res, sp.get(j) + shopping(price, special, remain, i, map));
    			}
    		}
    	}

    	map.put(needs, res);
    	return res;
    }

    private static int dotProduct(List<Integer> li1, List<Integer> li2) {
    	int sum = 0;
    	for (int i = 0; i < li1.size(); i++) {
    		sum += li1.get(i) * li2.get(i);
    	}
    	return sum;
    }
}