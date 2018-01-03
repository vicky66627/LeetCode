/*
Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs. The org sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 104. Reconstruction means building a shortest common supersequence of the sequences in seqs (i.e., a shortest sequence so that all sequences in seqs are subsequences of it). Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.

Example 1:

Input:
org: [1,2,3], seqs: [[1,2],[1,3]]

Output:
false

Explanation:
[1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can be reconstructed.

Example 2:

Input:
org: [1,2,3], seqs: [[1,2]]

Output:
false

Explanation:
The reconstructed sequence can only be [1,2].

Example 3:

Input:
org: [1,2,3], seqs: [[1,2],[1,3],[2,3]]

Output:
true

Explanation:
The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].

Example 4:

Input:
org: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]]

Output:
true

UPDATE (2017/1/8):
The seqs parameter had been changed to a list of list of strings (instead of a 2d array of strings). Please reload the code definition to get the latest changes. 
*/

public class Solution {
	public static void main(String[] args) {
		int[] org1 = new int[]{1,2,3};
		int[] org2 = new int[]{4,1,5,2,6,3};
		int[] org3 = new int[]{4,7,8,1,5,2};
		
		List<Integer> s1 = Arrays.asList(new Integer[]{1,2});
		List<Integer> s2 = Arrays.asList(new Integer[]{1,3});
		List<Integer> s3 = Arrays.asList(new Integer[]{2,3});
		List<Integer> s4 = Arrays.asList(new Integer[]{5,2,6,3});
		List<Integer> s5 = Arrays.asList(new Integer[]{4,1,5,2});
		List<Integer> s6 = Arrays.asList(new Integer[]{4,7,8,1});
		List<List<Integer>> seq1 = new ArrayList<>();
		seq1.add(s1);
        seq1.add(s2);
        List<List<Integer>> seq2 = new ArrayList<>();
        seq2.add(s1);
        List<List<Integer>> seq3 = new ArrayList<>();
        seq3.add(s1);
        seq3.add(s2);
        seq3.add(s3);
        List<List<Integer>> seq4 = new ArrayList<>();
        seq4.add(s4);
        seq4.add(s5);
        List<List<Integer>> seq5 = new ArrayList<>();
        seq5.add(s6);
        seq5.add(s5);

		System.out.println(sequenceReconstruction(org1, seq1));
		// output: false - [1,2,3], [[1,2],[1,3]]
		System.out.println(sequenceReconstruction(org1, seq2));
		// output: false - [1,2,3], [[1,2]]
		System.out.println(sequenceReconstruction(org1, seq3));
		// output: true - [1,2,3], [[1,2],[1,3],[2,3]]
		System.out.println(sequenceReconstruction(org2, seq4));
		// output: true - [4,1,5,2,6,3], [[5,2,6,3],[4,1,5,2]]
		System.out.println(sequenceReconstruction(org3, seq5));
		// output: false - [4,7,8,1,5,2], [[4,7,8,1],[4,1,5,2]]
	}

    public static boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        if (org == null || org.length == 0 || seqs == null || seqs.size() == 0) {
        	return false;
        }

        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> indegrees = new HashMap<>();

        for (List<Integer> seq : seqs) {
            for (int i = 0; i < seq.size(); i++) {
                graph.putIfAbsent(seq.get(i), new HashSet<>());
                indegrees.putIfAbsent(seq.get(i), 0);
                if (i > 0) {
                	if (graph.get(seq.get(i - 1)).add(seq.get(i))) {    // 注意这步，否则导致indegrees数据错误
                 	   indegrees.put(seq.get(i), indegrees.get(seq.get(i)) + 1);
                	}
                }
            }
        }

        if (org.length != indegrees.size()) {
        	return false;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int key : indegrees.keySet()) {
        	if (indegrees.get(key) == 0) {
        		queue.offer(key);
        	}
        }

        int cnt = 0;
        while (queue.size() == 1) {
        	cnt++;
        	int curt = queue.poll();
        	for (int next : graph.get(curt)) {
        		indegrees.put(next, indegrees.get(next) - 1);
        		if (indegrees.get(next) == 0) {
        			queue.offer(next);
        		}
        	}
        }

        return cnt == org.length;
    }
}