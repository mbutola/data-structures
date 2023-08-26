package com.msb.lrg.ds.recursion;

public class TowerOfHanoi {

	public static void main(String[] args) {
		TowerOfHanoi.move(5, "A", "B", "C");
	}
	
	public static void move(int n, String t1, String t2, String t3) {
		if(n==1) {
			System.out.println("MOVE "+ n + " FROM " + t1 + " TO " + t3);
			return;
		}
		
		move(n-1, t1, t3, t2);
		System.out.println("MOVE "+ n + " FROM " + t1 + " TO " + t3);
		move(n-1, t2, t1, t3);
	}

}
