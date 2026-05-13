package com.msb.lrg.problems.hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

	Given a sequence of  integers,  where each element is distinct and satisfies . For each  where , 
	that is  increments from  to , find any integer  such that  and keep a history of the values of  
	in a return array.
	Example
		Each value of  between  and , the length of the sequence, is analyzed as follows:
			, so 
			, so 
			, so 
			, so 
			, so 
		The values for  are .
	Function Description
		Complete the permutationEquation function in the editor below.
		permutationEquation has the following parameter(s):
			int p[n]: an array of integers
		Returns
			int[n]: the values of  for all  in the arithmetic sequence  to 
	Input Format
		The first line contains an integer , the number of elements in the sequence.
		The second line contains  space-separated integers  where .
	Constraints
		, where .
		Each element in the sequence is distinct.
	Sample Input 0
		3
		2 3 1
	Sample Output 0
		2
		3
		1

 */
public class H028SequenceEquation {

	public static void main(String[] args) {
		List<Integer> p = Arrays.asList(5,2,1,3,4); 
		List<Integer> res = permutationEquation(p);
		System.out.println(res);
	}


    public static List<Integer> permutationEquation(List<Integer> p) {
    	int[] pos = new int[p.size() + 1];
    	
        for(int i = 0; i < p.size(); i++){
            pos[p.get(i)] =  i + 1;
        }
    	
        List<Integer> res = new ArrayList<>();
        for(int i = 1; i <= p.size(); i++){
            res.add(pos[pos[i]]);
        }
        
        return res;    	
    }

    public static List<Integer> permutationEquation2(List<Integer> p) {
        
        List<Integer> res = new ArrayList<>();
        
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < p.size(); i++){
            map.put(p.get(i), i + 1);
        }
             
        for(int i = 1; i <= p.size(); i++){
            res.add(map.get(map.get(i)));
        }
        
        return res;
    }

}
