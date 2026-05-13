package com.msb.lrg.problems.hackerrank;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class H018CountByMatch {

	public static void main(String[] args) {
		List<Integer> arr = Arrays.asList(1,2,1,2,1,3,2);
		int res = sockMerchant(arr.size(), arr);
		System.out.println(res);
	}

    public static int sockMerchant(int n, List<Integer> ar) {
    	int pair = 0;
    	Set<Integer> set = new HashSet<>();
    	
    	for(int sock : ar) {
    		if(set.contains(sock)) {
    			pair++;
    			set.remove(sock);
    		} else {
    			set.add(sock);
    		}
    	}
    	return pair;
    }

    public static int sockMerchant2(int n, List<Integer> ar) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int c : ar){
            map.put(c, map.getOrDefault(c,0) + 1);
        }
        
        int total = 0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            total += entry.getValue()/2;
        }
        return total;
    }
	
}
