package com.msb.lrg.ds.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class BiHeapMedian {

	public static void main(String[] args) {
		int[] data = new int[]{12,15,10,5,8,7,16};
		System.out.println("I/P : " + Arrays.toString(data));
		
		solve(data);
	}

	public static void solve(int[] data) {
		PriorityQueue<Integer> s = new PriorityQueue<Integer>((i1,i2) -> i2-i1);
		PriorityQueue<Integer> g = new PriorityQueue<Integer>();
		
		s.add(data[0]);
		System.out.println(0 + ": " + (double)s.peek());
		for (int i = 1; i < data.length; i++) {
			if(s.size() > g.size()) {
				if(data[i] < s.peek()) {
					g.add(s.poll());
					s.add(data[i]);
				}else {
					g.add(data[i]);
				}
				System.out.println(i + ": " + (double)(s.peek()+g.peek())/2);
			}else {
				if(data[i] > g.peek()) {
					s.add(g.poll());
					g.add(data[i]);
				}else {
					s.add(data[i]);
				}
				System.out.println(i + ": " + (double)s.peek());
			}
		}	
	}
}
