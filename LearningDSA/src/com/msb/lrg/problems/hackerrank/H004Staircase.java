package com.msb.lrg.problems.hackerrank;

/*

	Staircase detail
		This is a staircase of size :
			   #
			  ##
			 ###
			####
		Its base and height are both equal to . It is drawn using # symbols and spaces. The last line is not preceded by any spaces.
		Write a program that prints a staircase of size .
	Function Description
		Complete the  function with the following parameter(s):
			: an integer
	Print
		Print a staircase as described above. No value should be returned.
		Note: The last line is not preceded by spaces. All lines are right-aligned.
	Input Format
		A single integer, , denoting the size of the staircase.
		Sample Input
			6 
	Sample Output
		     #
		    ##
		   ###
		  ####
		 #####
		######

 */
public class H004Staircase {

	public static void main(String[] args) {
		int n = 6;
		staircase(n);
	}

//    public static void staircase(int n) {
//    	for(int i = 1; i <= n; i++) {
//    		System.out.println(" ".repeat(n - i) + "#".repeat(i));
//    	}
//    }

    public static void staircase(int n) {
    	for(int i = 1; i <= n; i++) {
    		StringBuffer sb = new StringBuffer();
    		for(int j = 0; j < n - i; j++) sb.append(" ");
    		for(int j = 0; j < i; j++) sb.append("#");
    		System.out.println(sb.toString());
    	}
    }


}
