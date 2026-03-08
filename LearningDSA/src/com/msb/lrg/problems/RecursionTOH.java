package com.msb.lrg.problems;

public class RecursionTOH {

	public static void main(String[] args) {

		int n = 3;
		int from = 1;
		int to = 3;
		int aux = 2;
		int res = toh(n, from, to, aux);
		System.out.println("Steps :: " + res);
	}
	
	static int toh(int n, int from, int to, int aux){
		
		if(n == 1) {
			System.out.printf("Move disks from %d to %d%n", new Object[] {from, to});
			return 1;
		}
		
		int count = 0;
		
		count += toh(n-1, from, aux, to);
		System.out.printf("Move disks from %d to %d%n", new Object[] {from, to});
		count++;
		count += toh(n-1, aux, to, from);
		
		return count;
	}

}
