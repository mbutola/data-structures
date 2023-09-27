package com.msb.lrg.ds.heap;

import java.util.Arrays;

import com.msb.lrg.ds.Heap;
import com.msb.lrg.ds.Utility;

public class BiHeapSort {

	public static void main(String[] args) {
		int[] data = new int[]{10,15,50,4,20};
		Heap heap = new Heap(data, 15);
		Utility.printBinaryHeap(heap);
		
		heap.sort();
		Utility.printBinaryHeap(heap);
		System.out.println("O/P : " + Arrays.toString(heap.data));
	}

}
