package com.msb.lrg.problems.practice.package4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*

Combination Sum II :: LeetCode (40, medium)
	Given a collection of candidate numbers (candidates) and a target number 
	(target), find all unique combinations in candidates where the candidate numbers sum to target.
	Each number in candidates may only be used once in the combination.
	Note: The solution set must not contain duplicate combinations.
	Example 1:
		Input: candidates = [10,1,2,7,6,1,5], target = 8
		Output: 
			[
			[1,1,6],
			[1,2,5],
			[1,7],
			[2,6]
			]
	Example 2:
		Input: candidates = [2,5,2,1,2], target = 5
		Output: 
			[
			[1,2,2],
			[5]
			]
	Constraints:
		1 <= candidates.length <= 100
		1 <= candidates[i] <= 50
		1 <= target <= 30

 */
public class Problem471 {

	public static void main(String[] args) {
		int[] candidates = {10,1,2,7,6,1,5};
		int target = 8;
		List<List<Integer>> results =  combinationSum2(candidates, target);
		for(List<Integer> result : results) {
			System.out.println(result.toString());
		}
	}

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
		Arrays.sort(candidates);
    	List<List<Integer>> results = new ArrayList<>();
    	recursive(results, new ArrayList<Integer>(), candidates, 0, target);
    	return results;
    }
    
    public static void recursive(List<List<Integer>> results, List<Integer> result, int[] candidates, int start, int target) {

    	if(target == 0) {
    		results.add(new ArrayList<>(result));
    		return;
    	}
    	
    	for(int i = start; i < candidates.length; i++) {
    		
    		if(i > start && candidates[i] == candidates[i - 1])
    			continue;
    		
    		if(candidates[i] > target)
    			break;
    		
    	   	result.add(candidates[i]);
        	recursive(results, result, candidates, i + 1, target - candidates[i]);
        	result.remove(result.size() - 1);
     
    	}
    }
    
}
