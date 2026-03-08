package com.msb.lrg.problems.advance;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*

🧩 Problem 3 — Partition Strings by Palindrome and Count
	❓ Problem
		Partition strings into:
			palindrome
			non-palindrome
		For each group compute count.
		Output:
			Map<Boolean, Long>
	🧠 Explanation
		partitioningBy splits into:
			true  → palindromes
			false → others
		Downstream collector = counting
		So result:
			true → 3
			false → 2
		Collector concepts:
			partitioningBy
			downstream collector

 */
public class Problem33 {

	public static void main(String[] args) {
		 List<String> list = List.of("level","java","madam","code","radar");
	        System.out.println(partition(list));
	        // {true=3, false=2}

	}
	
	public static Map<Boolean, Long> partition(List<String> list) {
		
		return list.stream()
				.collect(Collectors.partitioningBy(
										s -> s.equals(new StringBuilder(s).reverse().toString()),
										Collectors.counting()));
		
	}

}
