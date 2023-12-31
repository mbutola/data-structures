package com.msb.lrg.ds.dp;

public class EggDropRecursive {

	public static void main(String[] args) {
		int floors = 5;
		int eggs = 3;
		System.out.println("O/P : " + EggDropRecursive.breakFloor(floors, eggs));
	}
	
	public static int breakFloor(int f, int e) {
		
		if(e == 1)
			return f;
		
		if(f == 1 || f == 0)
			return f;
		
		int res;
		int min = Integer.MAX_VALUE;
		for (int i = 1; i <= f; i++) {
			res = Math.max(breakFloor(i-1, e-1), 
					breakFloor(f-i, e));
			min = Math.min(min, res);
		}
		
		return min+1;
	}

}
