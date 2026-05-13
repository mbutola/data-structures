package com.msb.lrg.problems.hackerrank;

/*

	You are choreographing a circus show with various animals. For one act, you are given two kangaroos 
	on a number line ready to jump in the positive direction (i.e, toward positive infinity).
	The first kangaroo starts at location  and moves at a rate of  meters per jump.
	The second kangaroo starts at location  and moves at a rate of  meters per jump.
	You have to figure out a way to get both kangaroos at the same location at the same time as part of 
	the show. If it is possible, return YES, otherwise return NO.
	Example
		After one jump, they are both at , (, ), so the answer is YES.
	Function Description
		Complete the function kangaroo in the editor below.
		kangaroo has the following parameter(s):
			int x1, int v1: starting position and jump distance for kangaroo 1
			int x2, int v2: starting position and jump distance for kangaroo 2
		Returns
			string: either YES or NO
	Input Format
		A single line of four space-separated integers denoting the respective values of , , , and .
	Sample Input 0
		0 3 4 2
	Sample Output 0
		YES

 */
public class H010NumberLineJump {

	public static void main(String[] args) {
		int x1 = 2, v1 = 1, x2 = 1, v2 = 2;
		String res = kangaroo(x1, v1, x2, v2);
		System.out.println(res);
	}

//    public static String kangaroo(int x1, int v1, int x2, int v2) {
//    	if(x1 == x2 & v1 != v2)
//    		return "NO";
//    	if((x1 > x2 & v1 > v2) || (x1 < x2 & v1 < v2)) {
//    		return "NO";
//    	}else {
//    		if(Math.abs(x2 - x1) % Math.abs(v2 - v1) != 0)
//    			return "NO";
//    	}
//    	return "YES";
//    }

    public static String kangaroo(int x1, int v1, int x2, int v2) {
        if(x1 == x2) {
        	if(v1 != v2) return "NO";
        } else {
        	if(Math.abs(v2 - v1) == 0) {
        		return "NO";
        	}else if((x1 > x2 & v1 > v2) || (x1 < x2 & v1 < v2)) {
        		return "NO";
        	}else{
        		if(Math.abs(x2 - x1) % Math.abs(v2 - v1) != 0)
        			return "NO";
        	}
        }
        return "YES";
    }
}
