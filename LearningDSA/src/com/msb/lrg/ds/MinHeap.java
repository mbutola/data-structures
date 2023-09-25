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
}
