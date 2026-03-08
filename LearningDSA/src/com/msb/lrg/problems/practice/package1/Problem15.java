package com.msb.lrg.problems.practice.package1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem15 {

	public static void main(String[] args) {
		int[][] times = {{0,1,6},{0,2,7},{1,2,8},{1,3,5},{1,4,-4},{2,3,-3},{2,4,9},{3,1,-2},{4,0,2},{4,3,7}};
		int N = 5;
		int start = 0;
		int[] dist = networkDelayTime(times, N, start);
		System.out.println("dist :: " + Arrays.toString(dist));
	}
	
    public static int[] networkDelayTime(int[][] times, int N, int start) {
    	List<Pair<Integer,Pair<Integer, Integer>>> graph = getGraph(times, N);
    	int[] dist = new int[N];
    	Arrays.fill(dist, Integer.MAX_VALUE);
    	dist[start] = 0;
    	
    	for(int i = 1; i < N; i++) {
    		boolean updated = false;
    		for(Pair<Integer,Pair<Integer, Integer>> edge : graph) {
    			int from = edge.first;
    			int to = edge.second.first;
    			int wt = edge.second.second;
	    			
    			if(dist[from] != Integer.MAX_VALUE && dist[from] + wt < dist[to]) {
    				dist[to] = dist[from] + wt;
    				updated = true;
    			}
    		}
    		if(!updated)
    			break;
    	}
    	
    	// -ve cycle
		for(Pair<Integer,Pair<Integer, Integer>> edge : graph) {
			int from = edge.first;
			int to = edge.second.first;
			int wt = edge.second.second;
    			
			if(dist[from] != Integer.MAX_VALUE && dist[from] + wt < dist[to]) {
				System.out.println("Negative weight cycle found!");
				return null;
			}
		}
    	return dist;
	}

    public static List<Pair<Integer,Pair<Integer, Integer>>> getGraph(int[][] times, int N){
    	List<Pair<Integer,Pair<Integer, Integer>>> graph = new ArrayList<>();
    	
    	for(int i = 0;i < times.length; i++) {
    		graph.add(new Pair<Integer, Pair<Integer, Integer>>(times[i][0], new Pair<Integer, Integer>(times[i][1], times[i][2])));
    	}
    	
    	return graph;
    }

    static class Pair <U,V> {
    	U first;
    	V second;
    	
    	Pair(U first, V second){
    		this.first = first;
    		this.second = second;
    	}
    }

}

