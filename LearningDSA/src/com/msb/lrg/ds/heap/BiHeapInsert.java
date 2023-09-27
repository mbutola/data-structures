package com.msb.lrg.ds.heap;

import com.msb.lrg.ds.Heap;
import com.msb.lrg.ds.Utility;

public class BiHeapInsert {

	public static void main(String[] args) {
		
		int[] data = new int[]{10,20,15,40,50,100,25,45};
		Heap heap = new Heap(data, 15);
		Utility.printBinaryHeap(heap);
		
		int value = 12;
		heap.insert(value);
		Utility.printBinaryHeap(heap);
		
	}
	
}
