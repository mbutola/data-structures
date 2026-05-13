package com.msb.lrg.problems.hackerrank;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

	Given an array of bird sightings where every element represents a bird type id, determine the id 
	of the most frequently sighted type. If more than 1 type has been spotted that maximum amount, return 
	the smallest of their ids.
	Example
		There are two each of types  and , and one sighting of type . Pick the lower of the two types seen twice: type .
	Function Description
		Complete the migratoryBirds function in the editor below.
		migratoryBirds has the following parameter(s):
			int arr[n]: the types of birds sighted
		Returns
			int: the lowest type id of the most frequently sighted birds
	Input Format
		The first line contains an integer, , the size of .
		The second line describes  as  space-separated integers, each a type number of the bird sighted.
	Sample Input 0
		6
		1 4 4 4 5 3
	Sample Output 0
		4

 */
public class H015MigratoryBirds {

	public static void main(String[] args) {
		List<Integer> arr = Arrays.asList(3,4,2,2,3);
		int res = migratoryBirds(arr);
		System.out.println(res);
	}

    public static int migratoryBirds(List<Integer> arr) {
    	int[] freq = new int[6];
    	int maxCount = Integer.MIN_VALUE;
    	int id = Integer.MAX_VALUE;
    	
    	for(int i : arr) {
    		freq[i]++;
    		if((freq[i] > maxCount) || (freq[i] == maxCount & i < id) ) {
    			id = i;
    			maxCount = freq[i];
    		}
    	}
    	return id;
    }

    public static int migratoryBirds_2(List<Integer> arr) {
    	Map<Integer, Integer> map = new HashMap<>();
    	for(int i : arr) {
    		map.put(i, map.getOrDefault(Integer.valueOf(i), 0) + 1);
    	}
    	
    	int max = Integer.MIN_VALUE;
    	int res = Integer.MAX_VALUE;
    	
    	for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
    		int key = entry.getKey();
    		int value = entry.getValue();
    		
    		if(value > max || ((value ==  max) &  key < res)) {
    			res = key;
    			max = value;
    		}
    	}
    	return res;
    }

}
