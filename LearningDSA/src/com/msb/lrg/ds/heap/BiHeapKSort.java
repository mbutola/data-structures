package com.msb.lrg.ds.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class BiHeapKSort {

	public static void main(String[] args) {
		int k = 2;
		int[] in = new int[]{7,8,9,19,18};
		System.out.println(Arrays.toString(in));
		System.out.print("O/P : ");
		Ksort(in, k);
		System.out.println(Arrays.toString(in));
	}
	
	public static void Ksort(int[] in, int k) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for (int i = 0; i <= k; i++) {
			pq.add(in[i]);
		}
		
		int index = 0;
		for (int i = k+1; i < in.length; i++) {
			in[index++] = pq.poll();
			pq.add(in[i]);
		}
		
		while(!pq.isEmpty()) {
			in[index++] = pq.poll();
		}
		
	}

}
