package com.msb.lrg.ds.heap;

import com.msb.lrg.ds.Heap;
import com.msb.lrg.ds.Utility;

public class BiHeapPurcMaxItem {

	public static void main(String[] args) {
		int[] in = new int[]{12,1,5,111,200};
		Heap heap = new Heap(in,15);
		Utility.printBinaryHeap(heap);
		
		heap.buildMinHeap();
		Utility.printBinaryHeap(heap);

		int sum = 10;
		System.out.println("O/P : " + solve(heap, sum));
	}

	public static int solve(Heap heap, int sum) {
		int res = 0;
		while(sum - heap.peek() > 0) {
			res++;
			System.out.printf("%4s", heap.extractMin());
		}
		System.out.println();
		return res;
	}
}
