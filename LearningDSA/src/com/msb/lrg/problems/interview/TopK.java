package com.msb.lrg.problems.interview;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopK {

	public static void main(String[] args) {
        TopK t = new TopK();
        System.out.println(Arrays.toString(t.topKFrequent(new int[]{1,1,1,2,2,3},2)
        ));
	}

	public int[] topKFrequent(int[] in, int k){
		Map<Integer, Integer> freq = new HashMap<>();
		for(int key : in) {
			freq.put(key, freq.getOrDefault(key, 0) + 1);
		}

		PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> {
											return Integer.compare(freq.get(a), freq.get(b));
										});
		
		for(int key : freq.keySet()) {
			pq.offer(key);
			if(pq.size() > k) pq.poll();
		}
		
		int[] res = new int[k];
		for(int i = 0; i < k; i++) {
			res[i] = pq.poll();
		}
		
		return res;
	}
}
