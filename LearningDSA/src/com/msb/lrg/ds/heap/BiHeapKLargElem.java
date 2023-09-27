package com.msb.lrg.ds.heap;

import com.msb.lrg.ds.Heap;

public class BiHeapKLargElem {

	public static void main(String[] args) {
		int k = 3;
		int[] data = new int[]{5,15,10,20,8,25,18};
		
		solve(data, k);

	}
	public static void solve(int[] data, int k){	
		int[] in = new int[k];
		for (int i = 0; i < k; i++) {
			in[i] = data[i];
		}
		Heap heap = new Heap(in, 15);
		heap.buildMinHeap();
		
		for (int i = k; i < data.length; i++) {
			if(data[i] > heap.peek()) {
				heap.extractMin();
				heap.insert(data[i]);
			}
		}

		System.out.println("O/P : ");
		for (int i = 0; i < k; i++) { 
			System.out.printf("%4s", heap.extractMin());
		}
	}
}
