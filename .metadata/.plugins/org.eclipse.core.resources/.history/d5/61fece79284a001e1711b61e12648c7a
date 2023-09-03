package com.msb.lrg.ds.dp;

public class PalinPartitionRecursive {

	public static void main(String[] args) {
		String str = "bebcd";
		System.out.println("O/P : " + PalinPartitionRecursive.PalinPartition(str, 0, str.length()-1));
	}

	public static int PalinPartition(String str, int i, int j) {
		
		if(Utility.isPalindrome(str, i, j))
			return 0;
		
		int res = Integer.MAX_VALUE;
		
		for (int k = i; k < j; k++) {
			res = Math.min(res, 1+ PalinPartition(str, i, k)
									+ PalinPartition(str, k+1, j));
		}
		
		return res;
	}
	
}
