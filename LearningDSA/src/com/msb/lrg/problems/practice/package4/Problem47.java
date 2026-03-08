package com.msb.lrg.problems.practice.package4;

import java.util.ArrayList;
import java.util.List;

/*

Combination Sum :: LeetCode (39, medium)
	Given an array of distinct integers candidates and a target integer target, 
	return a list of all unique combinations of candidates where the chosen 
	numbers sum to target. You may return the combinations in any order.
	The same number may be chosen from candidates an unlimited number of times. 
	Two combinations are unique if the frequency of at least one of the 
	chosen numbers is different.
	The test cases are generated such that the number of unique combinations 
	that sum up to target is less than 150 combinations for the given input.
	Example 1:
		Input: candidates = [2,3,6,7], target = 7
		Output: [[2,2,3],[7]]
		Explanation:
			2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
			7 is a candidate, and 7 = 7.
			These are the only two combinations.
	Example 2:
		Input: candidates = [2,3,5], target = 8
		Output: [[2,2,2,2],[2,3,3],[3,5]]
	Example 3:
		Input: candidates = [2], target = 1
		Output: []
	Constraints:
		1 <= candidates.length <= 30
		2 <= candidates[i] <= 40
		All elements of candidates are distinct.
		1 <= target <= 40

 */
public class Problem47 {

	static List<List<Integer>> results = new ArrayList<>();
	
	public static void main(String[] args) {
//		int[] candidates = {2,3,5};
//		int target = 8;
		int[] candidates = {1};
		int target = 1;
		List<List<Integer>> results = combinationSum(candidates, target);
		for(List<Integer> result : results) {
			System.out.println(result.toString());
		}
	}

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
    	List<List<Integer>> results = new ArrayList<>();
        int n = candidates.length;
    	recursive(results, new ArrayList<Integer>(), candidates, n, target);
    	return results;
    }
    
    public static void recursive(List<List<Integer>> results, List<Integer> result, int[] candidates, int n, int target) {
    	if(n == 0 || target < 0 )
    		return;
    	
    	if(target == 0) {
    		results.add(new ArrayList<>(result));
    		return;
    	}
    	
    	// take
    	result.add(candidates[n - 1]);
    	recursive(results, result, candidates, n, target - candidates[n - 1]);
    	result.remove(result.size() - 1);
    	
    	// skip
    	recursive(results, result, candidates, n-1, target);
    	
    }
    
}
