package com.msb.lrg.problems.threads;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SafeIteration {

	/*
	 * Explanation
	 * 		CopyOnWriteArrayList creates a snapshot copy on every modification.
	 * 		Readers always see a consistent view — no locking needed.
	 * 		Suitable for read-heavy, write-rarely scenarios.
	 * Advanced idea: immutable snapshot concurrency — sacrifices memory for safety.
	 * 
	 * Why normal ArrayList fails in concurrency
	 * Because:
	 * 		Iterator detects structural modification
	 * 		ArrayList is fail-fast
	 * 
	 * How CopyOnWriteArrayList fixes this
	 * 		Iterators work on a snapshot
	 * 		Modifications happen on a new copy
	 * 		No exception
	 * 		No synchronization needed for iteration
	 * 
	 */
	public static void main(String[] args) {

		List<String> list = new CopyOnWriteArrayList<>(Arrays.asList("A", "B", "C"));
		
		Runnable write = () -> {
			try {
				Thread.sleep(300);
			}catch(InterruptedException e) {}
			String str = "D"; 
			list.add(str);
			System.out.println("Added : " + str);
		};
		
		Runnable reader = () -> {
			for(String i : list) {
				System.out.println("Reading : " + i);
				try {
					Thread.sleep(200);
				}catch(InterruptedException e) {}
			}
		};
		
		new Thread(write).start();
		new Thread(reader).start();

	}

}
