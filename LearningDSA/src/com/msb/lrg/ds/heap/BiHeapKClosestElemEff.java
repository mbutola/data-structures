package com.msb.lrg.ds.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class BiHeapKClosestElemEff {

	public static void main(String[] args) {
		int x = 38;
		int k = 3;
		int[] data = new int[]{30,40,32,33,46,37};
		System.out.println("I/P : " + Arrays.toString(data));
		
		solve(data, x, k);

	}
	public static void solve(int[] data, int x, int k){	
		PriorityQueue<MyPair> pq = new PriorityQueue<MyPair>();
		for (int i = 0; i < k; i++) {
			pq.add(new MyPair(i, Math.abs(data[i]-x)));
		}

		for (int i = k; i < data.length; i++) {
			int diff = Math.abs(data[i]-x);
			if(diff < pq.peek().val) {
				pq.poll();
				pq.add(new MyPair(i, diff));
			}
		}

		System.out.println("O/P : ");
		for (int i = 0; i < k; i++) { 
			System.out.printf("%4s", data[pq.poll().index]);
		}
	}
	
	static class MyPair implements Comparable<MyPair>{
		int index;
		int val;
		public MyPair(int index, int val) {
			super();
			this.index = index;
			this.val = val;
		}
		@Override
		public int compareTo(MyPair arg) {
			return arg.val - val;
		}
		
	}
}
