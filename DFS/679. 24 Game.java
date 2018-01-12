public class Solution {
	static final double eps = 0.0001;
	public static void main(String[] args) {
		int[] nums1 = new int[]{4, 1, 8, 7};
		int[] nums2 = new int[]{1, 2, 1, 2};

		System.out.println(judgePoint24(nums1));
		// output: true
		System.out.println(judgePoint24(nums2));
		// output: false
	}

	// Solution1:
    public static boolean judgePoint24(int[] nums) {
        if (nums == null || nums.length == 0) {
        	return false;
        }

        List<Double> list = new ArrayList<>();
        for (int num : nums) {
        	list.add((double)num);
        }
        return solve(list);
    }

    private static boolean solve(List<Double> list) {
    	if (list.size() == 0) {
    		return false;
    	}
    	if (list.size() == 1) {
    		return Math.abs(list.get(0) - 24.0) < eps;
    	}

    	for (int i = 1; i < list.size(); i++) {
    		for (int j = 0; j < i; j++) {
    			List<Double> next = new ArrayList<>();

    			double p1 = list.get(i), p2 = list.get(j);
    			next.addAll(Arrays.asList(p1 + p2, p1 - p2, p2 - p1, p1 * p2));
    			if (Math.abs(p1) > eps) {
    				next.add(p2 / p1);
    			}
    			if (Math.abs(p2) > eps) {
    				next.add(p1 / p2);
    			}

    			list.remove(i);
    			list.remove(j);
    			for (Double num : next) {
    				list.add(num);
    				if (solve(list)) {
    					return true;
    				}
    				list.remove(list.size() - 1);
    			}
    			list.add(j, p2);  // the insertion order of i,j cannot be reversed
    			list.add(i, p1);
    		}
    	}
    	return false;
    }

    // Solution2:
    public static boolean judgePoint24(int[] nums) {
        if (nums == null || nums.length == 0) {
        	return false;
        }

        double[] arr = new double[4];
        for (int i = 0; i < nums.length; i++) {
        	arr[i] = nums[i];
        }
        
        return solve(arr);
    }

    private static boolean solve(double[] arr) {
    	if (arr.length == 1) {
    		return Math.abs(arr[0] - 24.0) < eps;
    	}
    	
    	for (int i = 1; i < arr.length; i++) {
    		for (int j = 0; j < i; j++) {
    			double[] newArr = new double[arr.length - 1];
    			int idx = 0;
    			for (int k = 0; k < arr.length; k++) {
    				if (k != i && k != j) {
    					newArr[idx++] = arr[k];
    				}
    			}

    			newArr[idx] = arr[i] + arr[j];
    			if (solve(newArr)) {
    				return true;
    			}
    			newArr[idx] = arr[i] - arr[j];
    			if (solve(newArr)) {
    				return true;
    			}
    			newArr[idx] = arr[j] - arr[i];
    			if (solve(newArr)) {
    				return true;
    			}
    			newArr[idx] = arr[i] * arr[j];
    			if (solve(newArr)) {
    				return true;
    			}

    			if (arr[j] != 0) {
	    			newArr[idx] = arr[i] / arr[j];
	    			if (solve(newArr)) {
	    				return true;
	    			}
	    		}

	    		if (arr[i] != 0) {
	    			newArr[idx] = arr[j] / arr[i];
	    			if (solve(newArr)) {
	    				return true;
	    			}
	    		}
    		}
    	}
    	return false;
    }
}