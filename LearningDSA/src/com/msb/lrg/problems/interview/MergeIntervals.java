package com.msb.lrg.problems.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {

	public static void main(String[] args) {
        MergeIntervals m = new MergeIntervals();
        int[][] res = m.merge(new int[][]{{1,3},{2,6},{8,10}});
        for(int[] r:res) System.out.println(Arrays.toString(r));
	}
	
	public int[][] merge(int[][] intervals){
		
		Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));
		
		List<int[]> res = new ArrayList<>();
		
		for(int[] interval : intervals) {
			if(res.isEmpty() || res.get(res.size() -1)[1] < interval[0])
				res.add(interval);
			else	
				res.get(res.size() - 1)[1] = Math.max(res.get(res.size() - 1)[1], interval[1]);
		}
		
		return res.toArray(new int[0][]);
	}

}
