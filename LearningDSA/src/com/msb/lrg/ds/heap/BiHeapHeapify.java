package com.msb.lrg.ds.heap;

import com.msb.lrg.ds.Heap;
import com.msb.lrg.ds.Utility;

public class BiHeapHeapify {

	public static void main(String[] args) {
		int[] data = new int[]{40,20,30,35,25,80,32,100,70,60};
		Heap heap = new Heap(data, 15);
		Utility.printBinaryHeap(heap);
		
		int pos = 0;
		heap.minHeapify(pos);
		Utility.printBinaryHeap(heap);
	}

}
