package com.msb.lrg.problems.practice.package5;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/*

Remove K-Balanced Substrings :: LeetCode (3703, medium)
	You are given a string s consisting of '(' and ')', and an integer k.
	A string is k-balanced if it is exactly k consecutive '(' followed by k 
	consecutive ')', i.e., '(' * k + ')' * k.
	For example, if k = 3, k-balanced is "((()))".
	You must repeatedly remove all non-overlapping k-balanced substrings 
	from s, and then join the remaining parts. Continue this process until no k-
	balanced substring exists.
	Return the final string after all possible removals.
	​​​​​​​Example 1:
		Input: s = "(())", k = 1
		Output: ""
		Explanation:
			k-balanced substring is "()"
				Step	Current s	k-balanced	Result s
				1	(())	(())	()
				2	()	()	Empty
			Thus, the final string is "".
	Example 2:
		Input: s = "(()(", k = 1
		Output: "(("
		Explanation:
			k-balanced substring is "()"
				Step	Current s	k-balanced	Result s
				1	(()(	(()(	((
				2	((	-	((
			Thus, the final string is "((".
	Example 3:
	Input: s = "((()))()()()", k = 3
	Output: "()()()"
	Explanation:
		k-balanced substring is "((()))"
			Step	Current s	k-balanced	Result s
			1	((()))()()()	((()))()()()	()()()
			2	()()()	-	()()()
		Thus, the final string is "()()()".
	Constraints:
		2 <= s.length <= 105
		s consists only of '(' and ')'.
		1 <= k <= s.length / 2

 */
public class Problem531 {

	public static void main(String[] args) {
//		String s = "(())";
		String s = "(()(";
//		String s = "))";
		int k = 1;
		String res = removeSubstring(s, k);
		System.out.println("Result :: " + res);
	}

    public static String removeSubstring(String s, int k) {
        
        StringBuilder sb = new StringBuilder();
        int block = 2 * k;

        for (char c : s.toCharArray()) {
        	sb.append(c);
        	if(sb.length() >= block && isBalanced(sb, k)) {
        			sb.setLength(sb.length() - block);
        	}
        }
        
        return sb.toString();
    }
    
    public static boolean isBalanced(StringBuilder sb, int k) {

    	for(int i = sb.length() - 1 ; i >= sb.length() - k; i--){
    		if(sb.charAt(i) != ')')
    			return false;
    	}

    	for(int i = sb.length() - 1 - k ; i >= sb.length() - 2 * k; i--){
    		if(sb.charAt(i) != '(')
    			return false;
    	}

    	return true;
    }

//    public static String removeSubstring(String s, int k) {
//        
//    	Deque<Character> stack = new ArrayDeque<>();
//        StringBuilder sb = new StringBuilder();
//
//        for (char c : s.toCharArray()) {
//        	stack.addLast(c);
//        	if(stack.size() > 2*k && isBalanced(stack, k)) {
//        		for(int i = 0; i < 2*k; i++)
//        			stack.removeLast();
//        	}
//        }
//        
//        for (char c : stack) {
//            sb.append(c);
//        }
//
//        return sb.toString();
//    }
//    
//    public static boolean isBalanced(Deque<Character> stack, int k) {
//
//    	
//    	Iterator<Character> it = stack.descendingIterator();
//    	
//    	for(int i = 0; i < k; i++){
//    		if(it.next() != ')')
//    			return false;
//    	}
//
//    	for(int i = 0; i < k; i++){
//    		if(it.next() != '(')
//    			return false;
//    	}
//
//    	return true;
//    }
    
}
