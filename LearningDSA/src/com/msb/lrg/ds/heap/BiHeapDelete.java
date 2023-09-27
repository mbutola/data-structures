package com.msb.lrg.ds.heap;

import com.msb.lrg.ds.Heap;
import com.msb.lrg.ds.Utility;

public class BiHeapDelete {

	public static void main(String[] args) {
		int[] data = new int[]{10,20,30,40,50,35,38,45};
		Heap heap = new Heap(data, 15);
		Utility.printBinaryHeap(heap);
		
		int key = 3;
		heap.delete(key);
		Utility.printBinaryHeap(heap);
	}

}
