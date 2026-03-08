package com.msb.lrg.problems.practice.package0;

import java.util.Arrays;

/*
 LeetCode(132 Hard) Palindrome Partitioning II
 
Given a string s, partition s such that every substring of the partition is a palindrome.
Return the minimum cuts needed for a palindrome partitioning of s.
Example 1:
	Input: s = "aab"
	Output: 1
	Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
Example 2:
	Input: s = "a"
	Output: 0
Example 3:
	Input: s = "ab"
	Output: 1

 */
public class Problem4 {

	public static void main(String[] args) {
		String input = "bananacad";
		int res = minCut(input);
		System.out.println("Result :: " + res);
	}
	
	public static int minCut(String s) {
		int[] temp = new int[s.length()];
		Arrays.fill(temp, -1);
		return solve(s);
//		return solve(s,temp, 0) -1;
//		return solve(s, 0) -1;
	}


	
	/*
	 * DP :: Bottoms up
	 */
	static int solve(String s) {
        int n = s.length();

        // -------- STEP 1: PRECOMPUTE PALINDROMES ----------
        boolean[][] isPal = new boolean[n][n];

        for (int end = 0; end < n; end++) {
            for (int start = 0; start <= end; start++) {
                if (s.charAt(start) == s.charAt(end) &&
                     (end - start <= 2 || isPal[start + 1][end - 1])) {
                    isPal[start][end] = true;
                }
            }
        }

        // -------- STEP 2: BOTTOM-UP DP FOR MIN CUTS ----------
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            if (isPal[0][i]) {
                dp[i] = 0;
                continue;
            }

            int minCut = Integer.MAX_VALUE;
            for (int j = 1; j <= i; j++) {
                if (isPal[j][i]) {
                    minCut = Math.min(minCut, dp[j - 1] + 1);
                }
            }
            dp[i] = minCut;
        }

        return dp[n - 1];
	}

	/*
	 * DP :: Top down with memoization
	 */
	static int solve(String s, int[] temp, int start) {
		if(start == s.length())
			return 0;
		
		if(temp[start] != -1)
			return temp[start];			
			
		int minParts = Integer.MAX_VALUE; 
		
		for(int end=start; end<s.length(); end++) {
			if(isPalindrome(s, start, end)) {
				int parts =  1 + solve(s, temp, end+1);
				minParts = Math.min(minParts, parts);
			}
		}
		return temp[start] = minParts;
	}

	/*
	 * Brute force
	 */
	static int solve(String s, int start) {
		if(start == s.length())
			return 0;
		
		int minParts = Integer.MAX_VALUE; 
		
		for(int end=start; end<s.length(); end++) {
			if(isPalindrome(s, start, end)) {
				int parts =  1 + solve(s, end+1);
				minParts = Math.min(minParts, parts);
			}
		}
		return minParts;
	}
	
	public static boolean isPalindrome(String word, int start, int end) {
		while(start < end) {
			if(word.charAt(start++) != word.charAt(end--))
				return false;
		}
		return true;
	}

}
