package com.msb.lrg.ds.sort;

import java.util.*;

public class SortCollections {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(10);
		list.add(5);
		list.add(20);
		System.out.println("I/P : " + list.toString());
		Collections.sort(list);
		System.out.println("O/P increasing : " + list.toString());
		Collections.sort(list, Collections.reverseOrder());
		System.out.println("I/P reverse : " + list.toString());
	}

}
