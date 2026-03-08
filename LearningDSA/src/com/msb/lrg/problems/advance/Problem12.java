package com.msb.lrg.problems.advance;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/*

🧩 Problem 2 — Top-K Frequent Elements
	🔎 Problem Explanation
		Given an integer array, return the K most frequent numbers.
			Input: nums=[1,1,1,2,2,3], k=2
			Output: [1,2]

 */
public class Problem12 {

	public static void main(String[] args) {
		int[] nums= {1,1,1,2,2,3,3,3,3};
		int k=2;
		
		List<Integer> res = 
				Arrays.stream(nums)
						.boxed()
						.collect(Collectors.groupingBy(
												Function.identity(),
												Collectors.counting()))
						.entrySet()
						.stream()
						.sorted((a,b) -> Long.compare(b.getValue(), a.getValue()))
						.limit(k)
						.map(Map.Entry::getKey)
						.toList();

		System.out.println(res.toString());
	}

}
