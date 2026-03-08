package com.msb.lrg.problems.practice.package3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
Top K Frequent Elements :: LeetCode (347 Medium)
	Given an integer array nums and an integer k, return the k most frequent 
	elements. You may return the answer in any order.
	Example 1:
		Input: nums = [1,1,1,2,2,3], k = 2
		Output: [1,2]
	Example 2:
		Input: nums = [1], k = 1
		Output: [1]
	Example 3:
		Input: nums = [1,2,1,2,1,2,3,1,3,2], k = 2
		Output: [1,2]
	Constraints:
		1 <= nums.length <= 105
		-104 <= nums[i] <= 104
		k is in the range [1, the number of unique elements in the array].
		It is guaranteed that the answer is unique.

 */
public class Problem36 {

	public static void main(String[] args) {
		int[] nums = {1,1,1,2,2,3}; 
		int k = 2;
		int[] res = topKFrequent(nums, k);
		System.out.println("Results :: " + Arrays.toString(res));
	}

    public static int[] topKFrequent(int[] nums, int k) {
//        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for(int item : nums)
        	map.put(item, map.getOrDefault(item, 0) + 1);

        PriorityQueue<Integer> pq = new PriorityQueue<>(
        									(a,b) -> map.get(a) - map.get(b)); 
        
        for(int num : map.keySet()) {
        	pq.offer(num);
        	if(pq.size() > k)
        		pq.poll();
        }
        
        return pq.stream()
        			.mapToInt(Integer::intValue)
        			.toArray();

        //        int[] res = new int
        
//        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
//        Collections.sort(list, ( a, b) -> b.getValue() - a.getValue());
////        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>(){
////										public int compare(Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b) {
////											return b.getValue() - a.getValue();
////										}
////									});
//        for(int i = 0; i < k; i++){
//        	res.add(list.get(i).getKey());
//        }
//        return res.stream().mapToInt(Integer::intValue).toArray();
    }

}
