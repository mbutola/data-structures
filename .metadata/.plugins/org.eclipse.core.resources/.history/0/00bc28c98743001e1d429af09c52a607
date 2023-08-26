package com.msb.lrg.ds.recursion;

public class RopeCutting {

	public static void main(String[] args) {
		System.out.println("Max pieces : " + RopeCutting.maxPieces(23, 11, 9, 12));
	}

	public static int maxPieces(int len, int a, int b, int c) {
		System.out.println("len: " + len);
		
		if(len == 0)
			return 0;
		
		if(len < 0)
			return -1;
		
		int res = Math.max(
						Math.max(maxPieces(len-a, a, b, c),
									maxPieces(len-b, a, b, c)),
						maxPieces(len-c, a, b, c));
		
		if(res == -1)
			return -1;
		
		return res+1 ;
	}
	
}
