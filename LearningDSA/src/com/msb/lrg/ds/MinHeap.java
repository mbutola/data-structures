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
			swap(parent(i), i);
			i = parent(i);
		}
	}
	
	public void swap(int s, int d) {
		int temp = 	data[s];
		data[s] = data[d];
		data[d] = temp; 
	}
}
