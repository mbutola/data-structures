package com.msb.lrg.problems.practice.package5;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/*

Remove K Digits :: LeetCode (402, medium)
	Given string num representing a non-negative integer num, and an integer k, 
	return the smallest possible integer after removing k digits from num.
	Example 1:
		Input: num = "1432219", k = 3
		Output: "1219"
		Explanation: Remove the three digits 4, 3, and 2 to 
		form the new number 1219 which is the smallest.
	Example 2:
		Input: num = "10200", k = 1
		Output: "200"
		Explanation: Remove the leading 1 and the number is 
		200. Note that the output must not contain leading 
		zeroes.
	Example 3:
		Input: num = "10", k = 2
		Output: "0"
		Explanation: Remove all the digits from the number and 
		it is left with nothing which is 0.
	Constraints:
		1 <= k <= num.length <= 105
		num consists of only digits.
		num does not have any leading zeros except for the zero itself.

 */
public class Problem53 {

	public static void main(String[] args) {
		String num = "1432219";
		int k = 3;
		String res = removeKdigits(num, k);
		System.out.println("Resut :: " + res);
	}

    public static String removeKdigits(String num, int k) {
        
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : num.toCharArray()) {
            while (k > 0 && !stack.isEmpty() && stack.peekLast() > c) {
                stack.removeLast();
                k--;
            }
            stack.addLast(c);
        }

        // remove remaining from end
        while (k-- > 0)
            stack.removeLast();

        // build result
        StringBuilder sb = new StringBuilder();
        boolean leadingZero = true;

        for (char c : stack) {
            if (leadingZero && c == '0') continue;
            leadingZero = false;
            sb.append(c);
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }

}
