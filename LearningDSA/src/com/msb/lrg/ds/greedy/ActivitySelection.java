package com.msb.lrg.ds.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ActivitySelection {

	public static void main(String[] args) {		
		List<Activity> list = ActivitySelection.getActivityList();
		System.out.print("Original list : ");
		System.out.print(list.toString());
		System.out.println();
		Collections.sort(list, (i1, i2) -> i1.finish - i2.finish);
		System.out.print("Ordered list  : ");
		System.out.print(list.toString());
		System.out.println("\n------------");
		System.out.println("Activity count : " + ActivitySelection.maxActivity(list));
		
	}

	public static int maxActivity(List<Activity> list) {
		int count = 1;
		int prev = 0;
		for (int i = 1; i < list.size(); i++) {
			if(list.get(prev).finish <= list.get(i).start) {
				count++;
				prev = i;
			}
		}
		return count;
	}
	
	public static class Activity{
		public int start;
		public int finish;
		public Activity(int start, int finish) {
			super();
			this.start = start;
			this.finish = finish;
		}
		@Override
		public String toString() {
			return "(" + start + "," + finish + ") ";
		}
		
	}
	
	public static List<Activity> getActivityList(){
		return new ArrayList<Activity>(
					Arrays.asList(
							new Activity(12,25),
							new Activity(10,20),
							new Activity(20,30)
					)
				);
	}
}
