package com.msb.lrg.ds.heap;

import com.msb.lrg.ds.MinHeap;
import com.msb.lrg.ds.Utility;

public class BiHeapExtractMin {

	public static void main(String[] args) {
		int[] data = new int[]{20,25,30,35,40,80,32,100,70,60};
		MinHeap heap = new MinHeap(data, 15);
		Utility.printBinaryHeap(heap);
		
		System.out.println("O/P : " + heap.extractMin());
		Utility.printBinaryHeap(heap);
	}
	
}
