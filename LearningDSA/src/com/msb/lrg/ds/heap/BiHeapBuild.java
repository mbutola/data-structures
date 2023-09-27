package com.msb.lrg.ds.heap;

import com.msb.lrg.ds.Heap;
import com.msb.lrg.ds.Utility;

public class BiHeapBuild {

	public static void main(String[] args) {
		int[] data = new int[]{10,5,20,2,4,8};
		Heap heap = new Heap(data, 15);
		Utility.printBinaryHeap(heap);
		
		heap.buildMinHeap();
		Utility.printBinaryHeap(heap);
	}

}
