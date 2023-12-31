package com.msb.lrg.ds.dp;

public class GameStrategyRecursive2 {

	public static void main(String[] args) {
		int[] points = new int[] {20,5,4,6};
		System.out.println("O/P : "  + GameStrategyRecursive2.solve(points, 0, points.length-1));
	}
	
	public static int solve(int[] points, int i, int j) {
		
		if(i+1==j)
			return Math.max(points[i], points[j]);
		
		/*
		 * Max of if I choose First(i) or Last(j) element
		 * If I choose i the opponent can choose (i+1) or (j)
		 * 		I should get minimum 
		 *     		if (i+1) : (i+2)...(j) 
		 *     		if (j) : (i+1)...(j-1)
		 * If I choose j the opponent can choose (i), (j-1)
		 * 		I should get minimum 
		 *     		if (i) : (i+1)...(j-1) 
		 *     		if (j-1) : (i)...(j-2)
		 */
		int res = Math.max( points[i] + Math.min(solve(points, i+2,j),solve(points, i+1,j-1)),
								points[j] + Math.min(solve(points, i+1,j-1),solve(points, i,j-2)));
		
		return res;
	}
	
}
