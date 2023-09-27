package com.msb.lrg.ds.heap;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class BiHeapMerKSortArrays {

	public static void main(String[] args) {
		int k = 3;
		int[][] data = new int[k][];
		data[0] = new int[]{5,10};
		data[1] = new int[]{4,9};
		data[2] = new int[]{6};
		
		solve(data, k);

	}
	public static void solve(int[][] data, int k){	
		PriorityQueue<Triplet> pq = new PriorityQueue<Triplet>();
		for (int i = 0; i < k; i++) {
			pq.add(new Triplet(i,0,data[i][0]));
		}
		
		ArrayList<Integer> res = new ArrayList<Integer>();
		while(!pq.isEmpty()){
			Triplet rec = pq.poll();
			int row = rec.row;
			int col = rec.col;
			int val = rec.val;
			res.add(val);
			if(col+1 < data[row].length)
				pq.add(new Triplet(row, col+1, data[row][col+1]));
		}

		System.out.printf("O/P : ");
		System.out.println(res.toString());
	}
	
	static class Triplet implements Comparable<Triplet>{
		int row;
		int col;
		int val;
		public Triplet(int i, int j, int val) {
			super();
			this.row = i;
			this.col = j;
			this.val = val;
		}
		@Override
		public int compareTo(Triplet arg) {
			return val - arg.val;
		}
		
	}
}
