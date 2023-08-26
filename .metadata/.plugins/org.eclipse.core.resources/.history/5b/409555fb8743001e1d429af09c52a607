package com.msb.lrg.ds.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FractionalKnapsack {

	public static void main(String[] args) {		
		List<Item> list = FractionalKnapsack.getActivityList();
		System.out.print("Original list : ");
		System.out.print(list.toString());
		System.out.println();
		Collections.sort(list, (i1, i2) -> i2.val*i1.wt - i1.val*i2.wt);
		System.out.print("Ordered list  : ");
		System.out.print(list.toString());
		System.out.println("\n------------");
		System.out.println("Maximun value : " + FractionalKnapsack.maxValue(list, 50));
		
	}

	public static double maxValue(List<Item> list, int W) {
		double val = 0;
		int out = W;
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).wt <= out) {
				val = val + list.get(i).val;
				out = out - list.get(i).wt ;
			} else {
				val = val + ((list.get(i).val)*out/list.get(i).wt);
				break;
			}
		}
		return val;
	}
	
	public static List<Item> getActivityList(){
		return new ArrayList<Item>(
					Arrays.asList(
							new Item(10,60),
							new Item(40,40),
							new Item(20,100),
							new Item(30,120)
					)
				);
	}

	static class Item {
		public int wt;
		public int val;
		public Item(int wt, int val) {
			super();
			this.wt = wt;
			this.val = val;
		}
		@Override
		public String toString() {
			return "(" + wt + "," + val + ") ";
		}
		
	}

}

