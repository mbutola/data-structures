package com.msb.lrg.ds;

import java.util.Arrays;

public class Heap {
	
	public int[] data;
	public int size;
	public int capacity;

	public Heap(int capacity) {
		super();
		this.data = new int[capacity];
		this.size = 0;
		this.capacity = capacity;
	}
	

	public Heap(int[] data, int capacity) {
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

	public void minHeapify(int i) {
		int lt = left(i);
		int rt = right(i);
		int smallest = i;
		
		if(lt < size && data[lt] < data[i])
			smallest = lt;
		if(rt < size && data[rt] < data[smallest])
			smallest = rt;
		
		if(smallest != i) {
			Utility.swap(data, i, smallest);
			minHeapify(smallest);
		}
	}

	public int extractMin() {
		int res = 0;
		if(size == 0) {
			return Integer.MAX_VALUE;
		}else if(size == 1) {
			size--;
			res = data[0];
			data[0] = 0;
		}else {
			Utility.swap(data, 0, size-1);
			res = data[size-1];
			data[size-1] = 0;
			size--;
			minHeapify(0);
		}
		
		return res;
	}

	public void decreaseKey(int key, int val) {
		data[key] = val;
		while(key != 0 && data[key] < data[parent(key)]) {
			Utility.swap(data, key, parent(key));
			key = parent(key);
		}
	}

	public void delete(int key) {
		decreaseKey(key, Integer.MIN_VALUE);
		extractMin();
	}
	
	public void buildMinHeap() {
		int s = parent(size-1);
		for (int i = s; i >= 0; i--) {
			minHeapify(i);
		}
	}

	public void buildMaxHeap() {
		int s = parent(size-1);
		for (int i = s; i >= 0; i--) {
			maxHeapify(i);
		}
	}

	public void maxHeapify(int i) {
		int lt = left(i);
		int rt = right(i);
		int largest = i;
		
		if(lt < size && data[lt] > data[i])
			largest = lt;
		if(rt < size && data[rt] > data[largest])
			largest = rt;
		
		if(largest != i) {
			Utility.swap(data, i, largest);
			maxHeapify(largest);
		}
	}

	public void sort(){
		buildMaxHeap();
		for (int i = size-1; i >= 1; i--) {
			Utility.swap(data, i, 0);
			size--;
			maxHeapify(0);
		}
	}
	
	public int peek() {
		return data[0];
	}

}
