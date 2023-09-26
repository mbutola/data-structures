package com.msb.lrg.ds.heap;

import com.msb.lrg.ds.MinHeap;
import com.msb.lrg.ds.Utility;

public class BiHeapHeapify {

	public static void main(String[] args) {
		int[] data = new int[]{40,20,30,35,25,80,32,100,70,60};
		MinHeap heap = new MinHeap(data, 15);
		Utility.printBinaryHeap(heap);
		
		int pos = 0;
		heap.heapify(pos);
		Utility.printBinaryHeap(heap);
	}

}
