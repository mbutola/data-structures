package com.msb.lrg.problems.practice.package2;

import java.util.HashMap;
import java.util.Map;

/*

Longest Substring Without Repeating Characters :: LeetCode (3, Medium)
	Given a string s, find the length of the longest substring without duplicate characters.
	Example 1:
		Input: s = "abcabcbb"
		Output: 3
		Explanation: The answer is "abc", with the length of 3. 
		Note that "bca" and "cab" are also correct answers.
	Example 2:
		Input: s = "bbbbb"
		Output: 1
		Explanation: The answer is "b", with the length of 1.
	Example 3:
		Input: s = "pwwkew"
		Output: 3
		Explanation: The answer is "wke", with the length of 3.
	Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
		Constraints:
		0 <= s.length <= 5 * 104
		s consists of English letters, digits, symbols and spaces.

 */
public class Problem22 {

	public static void main(String[] args) {
//		String s = "abcabcbb";
//		String s = "bbbbb";
		String s = "au";
		int res = lengthOfLongestSubstring(s);
		System.out.println("Result :: " + res);
	}

    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int maxLen = 0;
        int left = 0;
        
        for(int right = 0; right < s.length(); right++) {
        	char c = s.charAt(right);
        	if(map.containsKey(c) && map.get(c) >= left) {
        		left = map.get(c) + 1;
        		map.put(c, right);
        		continue;
        	}
        	
        	map.put(c, right);
        	maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}
