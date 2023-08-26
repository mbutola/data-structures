package com.msb.lrg.ds.dp;

public class CutRecursive {

	public static void main(String[] args) {
		System.out.println("O/P : " + CutRecursive.cut(9, 2, 3, 4));
	}
	
	public static int cut(int len, int a, int b, int c) {
		
		if(len < 0)
			return -1;
			
		if(len == 0)
			return 0;

		int res = Math.max(Math.max(cut(len-a, a, b, c), 
								cut(len-b, a, b, c)), 
						cut(len-c, a, b, c));
		
		if(res == -1)
			return res;
		else
			return res+1;
	}

}
