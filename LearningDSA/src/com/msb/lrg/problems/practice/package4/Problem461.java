package com.msb.lrg.problems.practice.package4;

import java.util.ArrayDeque;
import java.util.Deque;

/*

Longest Valid Parentheses :: LeetCode (32. Hard)
	Given a string containing just the characters '(' and ')', return the length of 
	the longest valid (well-formed) parentheses substring.
	Example 1:
		Input: s = "(()"
		Output: 2
		Explanation: The longest valid parentheses substring is "()".
	Example 2:
		Input: s = ")()())"
		Output: 4
		Explanation: The longest valid parentheses substring is "()()".
	Example 3:
		Input: s = ""
		Output: 0
	Constraints:
		0 <= s.length <= 3 * 104
		s[i] is '(', or ')'.

 */
public class Problem461 {

	public static void main(String[] args) {
		String s = "()())";
		int res = longestValidParentheses(s);
		System.out.println("Result :: " + res);
	}

    public static int longestValidParentheses(String s) {
    	
    	int maxLength = 0;
    	Deque<Integer> stack = new ArrayDeque<>();
    	stack.push(-1);
    	
    	for(int i = 0; i < s.length(); i++) {
    		char c = s.charAt(i);
    		if(c == '(') {
    			stack.push(i);
    		} else {
    			stack.pop();
    			if(stack.isEmpty()) {
    				stack.push(i);
    			} else {
    				maxLength = Math.max(maxLength, i - stack.peek());
    			}
    		}
    	}
        
    	return maxLength;
    }

}
