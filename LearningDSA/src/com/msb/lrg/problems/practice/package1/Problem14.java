package com.msb.lrg.problems.practice.package1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/*
Network Delay Time :: Leetcode (743, Medium)
	You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of 
	travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is 
	the target node, and wi is the time it takes for a signal to travel from source to target.
	We will send a signal from a given node k. Return the minimum time it takes for all the n 
	nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.
	Example 1:
		Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
		Output: 2
	Example 2:
		Input: times = [[1,2,1]], n = 2, k = 1
		Output: 1
	Example 3:
		Input: times = [[1,2,1]], n = 2, k = 2
		Output: -1
	Constraints:
		1 <= k <= n <= 100
		1 <= times.length <= 6000
		times[i].length == 3
		1 <= ui, vi <= n
		ui != vi
		0 <= wi <= 100
		All the pairs (ui, vi) are unique. (i.e., no multiple edges.)

 */
public class Problem14 {

	public static void main(String[] args) {
		int[][] times = {{2,1,1},{2,3,1},{3,4,1}};
		int N = 4;
		int start = 2;
		int res = networkDelayTime(times, N, start);
		System.out.println("Result :: " + res);
	}
	
    public static int networkDelayTime(int[][] times, int N, int start) {
 		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		List<List<Pair>> graph = getGraph(times, N);
		Queue<Pair> queue = new PriorityQueue<>((a,b) -> a.time - b.time);
		queue.offer(new Pair(2,0));
		dist[2] = 0;
		
		while(!queue.isEmpty()) {
			Pair cur = queue.poll();
			int node = cur.node;
			int time = cur.time;
			if(time > dist[node])
				continue;
			for(Pair nei : graph.get(node)){
				int next = nei.node;
				int newTime = dist[node] + nei.time;
				if(newTime < dist[next]) {
					dist[next] = newTime;
					queue.offer(new Pair(next, newTime));
				}
			}
		}
		
        int max = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] == Integer.MAX_VALUE) return -1;
            max = Math.max(max, dist[i]);
        }

        return max;		
		
	}

	public static List<List<Pair>> getGraph(int[][] input, int N){
		List<List<Pair>> graph = new ArrayList<>();
		
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int[] item : input) {
			graph.get(item[0]).add(new Pair(item[1], item[2]));
		}
		return graph;
	}	

	static class Pair {
		int node;
		int time;
		
		Pair(int node, int time){
			this.node = node;
			this.time = time;
		}
	}
}

