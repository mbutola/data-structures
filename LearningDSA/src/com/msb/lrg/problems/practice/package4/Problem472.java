package com.msb.lrg.problems.practice.package4;

import java.util.ArrayList;
import java.util.List;

/*

Combination Sum III :: LeetCode (216, medium)
	Find all valid combinations of k numbers that sum up to n such that the 
	following conditions are true:
		Only numbers 1 through 9 are used.
		Each number is used at most once.
	Return a list of all possible valid combinations. The list must not contain the 
	same combination twice, and the combinations may be returned in any order.
	Example 1:
		Input: k = 3, n = 7
		Output: [[1,2,4]]
		Explanation:
			1 + 2 + 4 = 7
			There are no other valid combinations.
	Example 2:
		Input: k = 3, n = 9
		Output: [[1,2,6],[1,3,5],[2,3,4]]
		Explanation:
			1 + 2 + 6 = 9
			1 + 3 + 5 = 9
			2 + 3 + 4 = 9
			There are no other valid combinations.
	Example 3:
		Input: k = 4, n = 1
		Output: []
		Explanation: There are no valid combinations.
			Using 4 different numbers in the range [1,9], the smallest sum we can get is 1+2+3+4 = 10 and since 10 > 1, there are no valid combination.
	Constraints:
		2 <= k <= 9
		1 <= n <= 60
 */
public class Problem472 {

	public static void main(String[] args) {
		int k = 3;
		int target = 7;
		List<List<Integer>> results =  combinationSum3(k, target);
		for(List<Integer> result : results) {
			System.out.println(result.toString());
		}
	}

    public static List<List<Integer>> combinationSum3(int k, int target) {
    	int[] candidates = {1,2,3,4,5,6,7,8,9};
    	List<List<Integer>> results = new ArrayList<>();
        int n = candidates.length;
    	recursive(results, new ArrayList<Integer>(), candidates, n, k, target);
    	return results;
    }
    
    public static void recursive(List<List<Integer>> results, List<Integer> result, int[] candidates, int n, int k, int target) {
    	if(target == 0 && k == 0) {
    		results.add(new ArrayList<>(result));
    		return;
    	}

    	if(n == 0 || target < 0 || k < 0)
    		return;

    	// take
    	result.add(candidates[n - 1]);
    	recursive(results, result, candidates, n - 1, k - 1, target - candidates[n - 1]);
    	k++;
    	result.remove(result.size() - 1);
    	
    	// skip
    	recursive(results, result, candidates, n-1, k - 1, target);
    	k++;
    }
    
}
