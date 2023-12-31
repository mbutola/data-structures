package com.msb.lrg.ds.dp;

public class GameStrategyRecursive1 {

	public static void main(String[] args) {
		int[] points = new int[] {20,5,4,6};
		int sum = 0;
		for (int i = 0; i < points.length; i++) {
			sum+=points[i];
		}
		System.out.println("O/P : "  + GameStrategyRecursive1.solve(points, 0, points.length-1, sum));
	}
	
	public static int solve(int[] points, int i, int j, int sum) {
		
		if(i+1==j)
			return Math.max(points[i], points[j]);
		/*
		 * From sum of points I should get max of
		 * sums left if I choose First or Last element.
		 * 
		 */
		int res = Math.max(sum - solve(points, i+1, j, sum - points[i]),
				sum - solve(points, i, j-1, sum - points[j]));
		
		return res;
	}

}
