package com.msb.lrg.problems.hackerrank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class H0001MapSort {

	public static void main(String[] args) {
		
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 50);
        map.put(2, 30);
        map.put(3, 50);
        map.put(4, 20);
       
//        sortWithStream(map);
//        sortWithList(map);
        sortWithPriorityQueue(map);
	}
	
	public static void sortWithPriorityQueue(Map<Integer, Integer> map) {
		PriorityQueue<Map.Entry<Integer, Integer>> pq = 
								new PriorityQueue<>((e1, e2) -> {
													int cmp = e2.getValue() - e1.getValue();
													if(cmp != 0)
														return cmp;
													return e1.getKey() - e2.getKey();				
												});
		pq.addAll(map.entrySet());
		while(!pq.isEmpty()) {
			Map.Entry<Integer, Integer> entry = pq.poll();
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}
	
	public static void sortWithList(Map<Integer, Integer> map) {
		List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
		
		Collections.sort(list, (e1, e2) -> {
										int cmp = e2.getValue() - e1.getValue();
										if(cmp != 0)
											return cmp;
										return e1.getKey() - e2.getKey();				
									});
		for(Map.Entry<Integer, Integer> entry : list) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}
	
	public static void sortWithStream(Map<Integer, Integer> map) {
		map.entrySet()
			.stream()
			.sorted((e1, e2) -> {
						int cmp = e2.getValue() - e1.getValue();
						if(cmp != 0)
							return cmp;
						return e1.getKey() - e2.getKey();				
					})
			.forEach(System.out::println);
	}

}
