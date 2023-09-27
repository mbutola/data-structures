package com.msb.lrg.ds.heap;

public class BiHeapKClosestElemNaive {

	public static void main(String[] args) {
		int x = 35;
		int k = 3;
		int[] data = new int[]{10,30,5,40,38,80,70};
		
		System.out.println("O/P : ");
		solve(data, x, k);

	}
	public static void solve(int[] data, int x, int k){
		boolean[] visited = new boolean[data.length];
		for (int i = 0; i < k; i++) {
			int min_diff = Integer.MAX_VALUE;
			int min_ind = 0;
			for (int j = 0; j < data.length; j++) {
				if(!visited[j] && Math.abs(data[j] - x) < min_diff) {
					min_diff = Math.abs(data[j] - x);
					min_ind = j;
				}
			}
			System.out.printf("%4s", data[min_ind]);
			visited[min_ind] = true;
		}
	}
}
