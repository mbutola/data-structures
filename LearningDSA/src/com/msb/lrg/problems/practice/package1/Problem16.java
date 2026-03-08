package com.msb.lrg.problems.practice.package1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*

Shortest Path Visiting All Nodes :: LeetCode (847, Hard) 
	You have an undirected, connected graph of n nodes labeled from 0 to n - 1. You are given 
	an array graph where graph[i] is a list of all the nodes connected with node i by an edge.
	Return the length of the shortest path that visits every node. You may start and stop at any node, 
	you may revisit nodes multiple times, and you may reuse edges.
	Example 1:
		Input: graph = [[1,2,3],[0],[0],[0]]
		Output: 4
		Explanation: One possible path is [1,0,2,0,3]
	Example 2:
		Input: graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
		Output: 4
		Explanation: One possible path is [0,1,4,2,3]
	Constraints:
		n == graph.length
		1 <= n <= 12
		0 <= graph[i].length < n
		graph[i] does not contain i.
		If graph[a] contains b, then graph[b] contains a.
		The input graph is always connected.

 */
public class Problem16 {

	public static void main(String[] args) {
		int[][] graph = {{1,2,3},{0},{0},{0}};
//		int[][] graph = {{1},{0,2,4},{1,3,4},{2},{1,2}};
		int res = shortestPathLength(graph);
		System.out.println("Result :: " + res);
	}

    public static int shortestPathLength(int[][] graph) {
    	int n = graph.length;
    	
    	if(n == 1)
    		return 0;
        
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][1 << n];
        
        for(int i = 0; i < n; i++) {
        	int mask = 1 << i;
        	visited[i][mask] = true;
        	queue.offer(new int[]{-1, i, mask, 0});
        }
        
        int targetMask = (1 << n) - 1;
        
        while(!queue.isEmpty()) {
        	int[] cur = queue.poll();
        	int prev = cur[0];
        	int node = cur[1];
        	int mask = cur[2];
        	int dist = cur[3];
        	System.out.println("prev : " + prev + ", node : " + node + ", mask :" + mask);
        	
        	if(mask == targetMask) return dist;
        	
        	for(int nei : graph[node]) {
        		int nextMask = mask | (1 << nei);
        		if(!visited[nei][nextMask]) {
        			visited[nei][nextMask] = true;
        			queue.offer(new int[]{node, nei, nextMask, dist + 1});
        		}
        	}
        }
        
        return -1;
    }
    
    public static List<List<Integer>> getGraph(int[][] input){
    	List<List<Integer>> graph = new ArrayList<>();
    	
    	for(int i = 0; i < input.length; i++) {
    		graph.add(new ArrayList<>());
    	}
    	
    	for(int i = 0; i < input.length; i++) {
    		for(int to : input[i]) {
    			graph.get(i).add(to);
    		}
    	}
    	
    	return graph;
    }
}
