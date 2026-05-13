package com.msb.lrg.problems.frequentlyasked;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

🔤 Group Anagrams
	🧠 One-line idea
		👉 Words with same sorted characters (or same frequency) belong to the same group
	🧩 Problem
		["eat","tea","tan","ate","nat","bat"]
	👉 Output:
		[
		 ["eat","tea","ate"],
		 ["tan","nat"],
		 ["bat"]
		]
	⚙️ Approach 1: Sort characters (most common)
		👉 Key idea:
			Sort each word → use as key
		🔄 Example
			eat → aet  
			tea → aet  
			ate → aet
		👉 Same key → same group

 */
public class F017GroupAnagram {

	public static void main(String[] args) {
		String[] strs = { "eat", "tea", "tan", "ate", "nat", "bat" };
		System.out.println(groupAnagrams(strs));
	}

	public static List<List<String>> groupAnagrams(String[] strs) {
		Map<String, List<String>> map = new HashMap<>();

		for (String str : strs) {
			char[] chars = str.toCharArray();
			Arrays.sort(chars);
			String key = new String(chars);

			map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
		}

		return new ArrayList<>(map.values());
	}

	/*
	 * ⚙️ Approach 2: Character count (faster) 👉 Instead of sorting: Count
	 * frequency of letters → use as key
	 */
	public static List<List<String>> groupAnagrams2(String[] strs) {
		Map<String, List<String>> map = new HashMap<>();

		for (String str : strs) {
			int[] count = new int[26];

			for (char c : str.toCharArray()) {
				count[c - 'a']++;
			}

			String key = Arrays.toString(count);
			map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
		}

		return new ArrayList<>(map.values());
	}
}
