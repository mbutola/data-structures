package com.msb.lrg.problems.practice.package4;

/*

Maximum Product of Word Lengths :: LeetCode (318, Medium)
	Given a string array words, return the maximum value of length(word[i]) * length(word[j]) 
	where the two words do not share common letters. If no such two words exist, return 0.
	Example 1:
		Input: words = ["abcw","baz","foo","bar","xtfn","abcdef"]
		Output: 16
		Explanation: The two words can be "abcw", "xtfn".
	Example 2:
		Input: words = ["a","ab","abc","d","cd","bcd","abcd"]
		Output: 4
		Explanation: The two words can be "ab", "cd".
	Example 3:
		Input: words = ["a","aa","aaa","aaaa"]
		Output: 0
		Explanation: No such pair of words.
	Constraints:
		2 <= words.length <= 1000
		1 <= words[i].length <= 1000
		words[i] consists only of lowercase English letters.

 */
public class Problem44 {

	public static void main(String[] args) {
		String[] words = {"abcw","baz","foo","bar","xtfn","abcdef"};
		int res = maxProduct(words);
		System.out.println("Result :: " + res);
	}

   public static int maxProduct(String[] words) {
	   int n = words.length;
	   int[] masks = new int[n];
	   int[] lengths = new int[n];
	   
	   for(int i = 0; i < n; i++) {
		   int mask = 0;
		   for(char c : words[i].toCharArray()) {
			   mask |= 1 << c - 'a';
		   }
		   masks[i] = mask;
		   lengths[i] = words[i].length();
	   }
	   
	   int max = 0;
	   for(int i = 0; i < n; i++) {
		   for(int j = i + 1; j < n; j++) {
			   if((masks[i] & masks[j]) == 0)
				   max = Math.max(max, lengths[i]*lengths[j]);
		   }		   
	   }
	        
	   return max;
    }
	
}
