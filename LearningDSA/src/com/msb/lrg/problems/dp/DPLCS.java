package com.msb.lrg.problems.dp;

import java.util.Arrays;

/*

What is Longest Common Substring?
	Given two strings:
		S1 = "abcde"
		S2 = "abfce"
	👉 Find the longest contiguous substring that appears in both strings.
		Answer: "ab" (length = 2)
	⚠️ Substring ≠ Subsequence
		Substring → characters must be continuous
		Subsequence → characters can be skipped

 */
public class DPLCS {
	
	static String s1 = "abcdgh";
	static String s2 = "abedfha";
	static int[][] dp;
	
	public static void main(String[] args) {

		int res;
		
		res = lcs(s1, s2, s1.length(), s2.length());
		System.out.println("Recursive :: " + res);

		dp = new int[s1.length()+1][s2.length()+1];
		for(int i=0; i<=s1.length(); i++) {
			Arrays.fill(dp[i], -1);
		}
		res = topDown(s1, s2, s1.length(), s2.length());
		System.out.println("Top down :: " + res);
		
		res = bottomUp(s1, s2, s1.length(), s2.length());
		System.out.println("Bottom up :: " + res);

		String resStr = bottomUpString(s1, s2, s1.length(), s2.length());
		System.out.println("Bottom up :: " + resStr);
}
	
	static int lcs(String s1, String s2, int i, int j) {
		
		if(i == 0 || j == 0)
			return 0;
		
		if(s1.charAt(i-1) == s2.charAt(j-1)) {
			return 1 + lcs(s1, s2, i-1, j-1);
		} else {
			return Math.max(lcs(s1, s2, i, j-1), lcs(s1, s2, i-1, j)); 
		}
		
	}
	
	static int topDown(String s1, String s2, int n, int m) {
		
		if(n == 0 || m== 0)
			return 0;
		
		if(dp[n][m] != -1)
			return dp[n][m];
		
		if(s1.charAt(n-1) == s2.charAt(m-1)) {
			return dp[n][m] = 1+ topDown(s1, s2, n-1, m-1); 
		}else {
			return dp[n][m] = Math.max(topDown(s1, s2, n-1, m), topDown(s1, s2, n, m-1));
		}
	}

	static int bottomUp(String s1, String s2, int n, int m) {
		for(int i=0; i<=s1.length(); i++) {
			for(int j=0; j<=s2.length(); j++) {
				if(i == 0 || j == 0)
					dp[i][j] = 0;
			}
		}

		for(int i=0; i<=s1.length(); i++) {
			for(int j=0; j<=s2.length(); j++) {
				if(s1.charAt(n-1) == s2.charAt(m-1)) {
					dp[n][m] = 1 + dp[n-1][m-1]; 
				}else {
					dp[n][m] = Math.max(dp[n][m-1], dp[n-1][m]);
				}
			}
		}
		return dp[n][m];
	}

	static String bottomUpString(String s1, String s2, int n, int m) {
		for(int i=0; i<=s1.length(); i++) {
			for(int j=0; j<=s2.length(); j++) {
				if(i == 0 || j == 0)
					dp[i][j] = 0;
			}
		}

		for(int i=0; i<=s1.length(); i++) {
			for(int j=0; j<=s2.length(); j++) {
				if(s1.charAt(n-1) == s2.charAt(m-1)) {
					dp[n][m] = 1 + dp[n-1][m-1]; 
				}else {
					dp[n][m] = Math.max(dp[n][m-1], dp[n-1][m]);
				}
			}
		}

		String res = "";
		
		int i = s1.length();
		int j = s2.length();
		while(i>0 && j>0) {
			if(s1.charAt(i-1) == s2.charAt(j-1)) {
				res += s1.charAt(i-1);
				i--;
				j--;
			}else {
				if(dp[i][j-1] > dp[i-1][j]) {
					j--;
				}else {
					i--;
				}
			}
		}
		
		return new StringBuilder(res).reverse().toString();
		
	}
}
