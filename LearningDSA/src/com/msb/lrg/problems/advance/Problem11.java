package com.msb.lrg.problems.advance;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/*

Problem 1 — Group Anagrams
	🔎 Problem Explanation
		Given a list of words, group those that are anagrams
		(words containing the same letters in different order).
		Example:
			Input:  ["eat","tea","tan","ate","nat","bat"]
			Output: [[eat,tea,ate],[tan,nat],[bat]]
	🧠 Solution Explanation
		Key idea:
			Anagrams have identical sorted characters.
				eat → aet
				tea → aet
				ate → aet
			So we group by sorted string.
		Flow:
			words → stream → groupingBy(sorted) → groups → list
		Lambda:
			w -> sorted(w)

 */
public class Problem11 {

	public static void main(String[] args) {
		List<String> input = List.of("eat","tea","tan","ate","nat","bat");

		List<List<String>> res = 
			input.stream()
					.collect(Collectors.groupingBy(
											w -> {
												char[] arr = w.toCharArray();
												Arrays.sort(arr);
												return new String(arr);
											}
									))
					.values()
					.stream()
					.toList();
		
		System.out.println(res.toString());
		
	}

}
