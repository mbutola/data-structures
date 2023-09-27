package com.msb.lrg.ds.heap;

import com.msb.lrg.ds.Heap;
import com.msb.lrg.ds.Utility;

public class BiHeapDecKey {

	public static void main(String[] args) {
		int[] data = new int[]{10,20,40,80,100,70};
		Heap heap = new Heap(data, 15);
		Utility.printBinaryHeap(heap);
		
		int key = 3;
		int val = 5;
		heap.decreaseKey(key, val);
		Utility.printBinaryHeap(heap);
	}
	
}
