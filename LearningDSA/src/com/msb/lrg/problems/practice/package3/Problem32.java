package com.msb.lrg.problems.practice.package3;

import java.util.ArrayList;
import java.util.List;

/*
Expression Add Operators :: LeetCode (282 Hard)
	Given a string num that contains only digits and an integer target, return all possibilities 
	to insert the binary operators '+', '-', and/or '*' between the digits of num so that the 
	resultant expression evaluates to the target value.
	Note that operands in the returned expressions should not contain leading zeros.
	Note that a number can contain multiple digits.
	Example 1:
		Input: num = "123", target = 6
		Output: ["1*2*3","1+2+3"]
		Explanation: Both "1*2*3" and "1+2+3" evaluate to 6.
	Example 2:
		Input: num = "232", target = 8
		Output: ["2*3+2","2+3*2"]
		Explanation: Both "2*3+2" and "2+3*2" evaluate to 8.
	Example 3:
		Input: num = "3456237490", target = 9191
		Output: []
		Explanation: There are no expressions that can be created from "3456237490" to evaluate to 9191.
	Constraints:
		1 <= num.length <= 10
		num consists of only digits.
		-231 <= target <= 231 - 1

 */
public class Problem32 {

	public static void main(String[] args) {
		 String num = "123";
		 int target = 6;

		 List<String> res = addOperators(num, target);
		 for(String exp : res) {
			 System.out.println(exp);
		 }
	}

    public static List<String> addOperators(String num, int target) {
    	List<String> res = new ArrayList<>();
    	dfs(res, num, target, "", 0, 0, 0);
        return res;
    }
    
    public static void dfs(List<String> res, String num, int target, String path, long prev, long val, int index) {
    	
    	if(index == num.length()) {
    		if(val == target) {
    			res.add(path);
    		}
			return;
    	}
    		
   		for(int i = index; i < num.length(); i++) {
   			if(i != index && num.charAt(index) == '0') {
   				break;
   			}
   			long curr = Long.parseLong(num.substring(index, i + 1));;
   			if(index == 0) {
   				dfs(res, num, target, path + curr, curr, curr, i + 1);
   			} else {
   				dfs(res, num, target, path + "+" + curr, curr, val + curr, i + 1);
   				dfs(res, num, target, path + "-" + curr, -curr, val - curr, i + 1);
   				dfs(res, num, target, path + "*" + curr, prev * curr, val - prev + (prev * curr), i + 1);
   			}
    	}
    }
	
}
