package com.msb.lrg.ds.dp;

public class MtxMultResursive {

	public static void main(String[] args) {
		int[] matrixes = new int[] {10,20,40,30,50};
		System.out.println("O/P : " + MtxMultResursive.multiplications(matrixes, 0, matrixes.length-1));
	}

	public static int multiplications(int[] mat, int i, int j) {
		
		if(i+1==j)
			return 0;
		
		int res = Integer.MAX_VALUE;
		
		for (int k = i+1; k < j; k++) {
			res = Math.min(res, multiplications(mat, i, k)
									+ multiplications(mat, k, j)
									+ mat[i]*mat[k]*mat[j]);
		}
		
		return res;
	}
}
