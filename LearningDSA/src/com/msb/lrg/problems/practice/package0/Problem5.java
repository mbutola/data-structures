package com.msb.lrg.problems.practice.package0;

/*
LeetCode (190 Easy) Reverse Bits

Reverse bits of a given 32 bits signed integer.
Example 1:
	Input: n = 43261596
	Output: 964176192
	Explanation:
		Integer		Binary
		43261596	00000010100101000001111010011100
		964176192	00111001011110000010100101000000
Example 2:
	Input: n = 2147483644
	Output: 1073741822
	Explanation:
		Integer		Binary
		2147483644	01111111111111111111111111111100
		1073741822	00111111111111111111111111111110
 

 */
public class Problem5 {

	public static void main(String[] args) {
		int res = reverseBits(43261596);
		System.out.println("Result :: " + res);
		System.out.println("In  :: " + 
				(String.format("%32s", Integer.toBinaryString(43261596))).replace(' ', '0'));
		System.out.println("Out :: " + 
				(String.format("%32s", Integer.toBinaryString(res))).replace(' ', '0'));
	}

	public static int reverseBits(int n) {
	       int result = 0;

	        for (int i = 0; i < 32; i++) {
	            // Shift result to left to make room for next bit
	            result <<= 1;

	            // Add the least significant bit of n
	            result |= (n & 1);

	            // Shift input to process the next bit
	            n >>>= 1;   // use unsigned shift!
	        }

	        return result;
	      }

}
