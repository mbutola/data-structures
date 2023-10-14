package com.msb.lrg.ds.sort;

import java.util.Arrays;

public class MergeOverlappingIntervals {

	public static void main(String[] args) {
		Interval[] a = new Interval[]{new Interval(5,10), new Interval(3,15), new Interval(18,30), new Interval(2,7)};
		System.out.println("I/P : " + Arrays.toString(a));
		MergeOverlappingIntervals.mergeIntervals(a);;
	}
	
	public static void mergeIntervals(Interval[] a) {
		Arrays.sort(a);
		System.out.println("Sort : " + Arrays.toString(a));
		int res = 0;
		for (int i = 1; i < a.length; i++) {
			if(a[res].end >= a[i].start) {
				a[res].start = Math.min(a[res].start,a[i].start);
				a[res].end = Math.max(a[res].end,a[i].end);
			}else {
				res++;
				a[res] = a[i];
			}
		}
		
		System.out.print("O/P :");
		for (int i = 0; i <= res; i++) {
			System.out.printf("%8s",a[i]);
		}
	}

}

class Interval implements Comparable<Interval>{
	int start;
	int end;
	public Interval(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}
	@Override
	public String toString() {
		return "("+start+","+end+")";
	}
	@Override
	public int compareTo(Interval o) {
		return this.start - o.start;
	}
}