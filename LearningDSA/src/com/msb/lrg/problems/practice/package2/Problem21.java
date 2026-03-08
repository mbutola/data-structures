package com.msb.lrg.problems.practice.package2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

Group Anagrams :: LeetCode (49, Medium)
	Given an array of strings strs, group the anagrams together. You can return 
	the answer in any order.
	Example 1:
		Input: strs = ["eat","tea","tan","ate","nat","bat"]
		Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
		Explanation:
		There is no string in strs that can be rearranged to form "bat".
		The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
		The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.
	Example 2:
		Input: strs = [""]
		Output: [[""]]
	Example 3:
		Input: strs = ["a"]
		Output: [["a"]]
	Constraints:
		1 <= strs.length <= 104
		0 <= strs[i].length <= 100
		strs[i] consists of lowercase English letters.

 */
public class Problem21 {

	public static void main(String[] args) {
		String[] strs = {"eat","tea","tan","ate","nat","bat"};
		List<List<String>> res = groupAnagrams(strs);
		System.out.println("Result :: " + res.toString());
	}

    public static List<List<String>> groupAnagrams(String[] strs) {
    	Map<String, List<String>> map = new HashMap<>();
    	
    	for(String str : strs) {
    		char[] arr = str.toCharArray();
    		Arrays.sort(arr);
    		String newStr = new String(arr);
    		map.computeIfAbsent(newStr, k -> new ArrayList<String>()).add(str);		
    	}
    	
    	return new ArrayList<>(map.values());
    }

}
