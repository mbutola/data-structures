package com.msb.lrg.problems.practice.package1;

/*

Count Numbers with Unique Digits :: LeetCode (357, Medium)
	Given an integer n, return the count of all numbers with unique digits, x, where 0 <= x < 10n.
	Example 1:
		Input: n = 2
		Output: 91
		Explanation: The answer should be the total numbers in the range of 0 ≤ x < 100, excluding 11,22,33,44,55,66,77,88,99
	Example 2:
		Input: n = 0
		Output: 1
	Constraints:
		0 <= n <= 8
		
	f(0) : 1
	f(1) : 10
	f(2) : 9*9
	f(3) : 9*9*8
	f(4) : 9*9*8*7
	f(n) : 9*9*8*7....f(10 - (n-1))
	
	count(n) : f(0) + f(1) + f(2) + .... + f(n) 

 */
public class Problem17 {

	public static void main(String[] args) {
		int n = 4;
		int res = countNumbersWithUniqueDigits(n);
		System.out.println("Result :: " + res);
	}

    public static int countNumbersWithUniqueDigits(int n) {
    	if(n == 0) return 1;
    	if(n == 1) return 10;
    	
    	int ans = 10;
    	int product = 9;
    	int available = 9;
    	
    	for(int i = 2; i <= n && available > 0; i++) {
    		product*=available;
    		ans+=product;
    		available--;
    	}
    	
    	return ans;
    }
    
}
