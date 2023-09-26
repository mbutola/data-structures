package com.msb.lrg.ds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MinHeap {
	
	public int[] data;
	public int size;
	public int capacity;

	public MinHeap(int capacity) {
		super();
		this.data = new int[capacity];
		this.size = 0;
		this.capacity = capacity;
	}
	

	public MinHeap(int[] data, int capacity) {
		super();
		this.data = new int[capacity];
		for (int i = 0; i < data.length; i++) {
			this.data[i] = data[i];	
		}
		size = data.length;
		this.capacity = capacity;
		System.out.println(Arrays.toString(this.data));
	}


	public int left(int i) {
		return 2*i+1;
	}

	public int right(int i) {
		return 2*i+2;
	}

	public int parent(int i) {
		return (i-1)/2;
	}

	public void insert(int val){
		data[size++] = val;
		for (int i = size-1; i != 0 && data[i] < data[parent(i)];) {
			Utility.swap(data, parent(i), i);
			i = parent(i);
		}
	}

	public void heapify(int i) {
		int lt = left(i);
		int rt = right(i);
		int smallest = i;
		
		if(lt < size && data[lt] < data[i])
			smallest = lt;
		if(rt < size && data[rt] < data[smallest])
			smallest = rt;
		
		if(smallest != i) {
			Utility.swap(data, i, smallest);
			heapify(smallest);
		}
	}

	public int extractMin() {
		int res = 0;
		if(size == 0)
			return Integer.MAX_VALUE;
		
		if(size == 1) {
			size--;
			res = data[0];
			data[0] = 0;
		}
		
		Utility.swap(data, 0, size-1);
		res = data[size-1];
		data[size-1] = 0;
		size--;
		heapify(0);
		
		return res;
	}

}
