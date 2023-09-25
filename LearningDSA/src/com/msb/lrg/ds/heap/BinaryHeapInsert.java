package com.msb.lrg.ds.heap;

import com.msb.lrg.ds.MinHeap;
import com.msb.lrg.ds.Utility;

public class BinaryHeapInsert {

	public static void main(String[] args) {
		
		int[] data = new int[]{10,20,15,40,50,100,25,45};
		MinHeap heap = new MinHeap(data, 15);
		
		Utility.printBinaryHeap(heap);

	}

	
}
