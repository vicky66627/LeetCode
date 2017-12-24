/*
Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

G(n): the number of unique BST for a sequence of length n.

F(i), 1 <= i <= n: the number of unique BST, where the number i is the root of BST, and the sequence ranges from 1 to n.

G(n) = F(1) + F(2) + ... + F(n).

F(i) = G(i-1) * G(n-i)	1 <= i <= n

=> G(n) = G(0) * G(n-1) + G(1) * G(n-2) + … + G(n-1) * G(0) 
*/

// dynamic programming problem
public class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
        	for (int j = 1; j <= i; j++) {
        		dp[i] += dp[j - 1] * dp[i - j];
        	}
        }

        return dp[n];
    }
}