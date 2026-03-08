package com.msb.lrg.problems.practice.package4;

import java.util.ArrayList;
import java.util.List;

/*

Course Schedule :: LeetCode (207, medium)
	There are a total of numCourses courses you have to take, labeled from 0 to 
	numCourses - 1. You are given an array prerequisites where 
	prerequisites[i] = [ai, bi] indicates that you must take course bi first if 
	you want to take course ai.
	For example, the pair [0, 1], indicates that to take course 0 you have 
	to first take course 1.
	Return true if you can finish all courses. Otherwise, return false.
	Example 1:
		Input: numCourses = 2, prerequisites = [[1,0]]
		Output: true
		Explanation: There are a total of 2 courses to take. 
			To take course 1 you should have finished course 0. So it is possible.
	Example 2:
		Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
		Output: false
		Explanation: There are a total of 2 courses to take. 
			To take course 1 you should have finished course 0, 
			and to take course 0 you should also have finished course 1. So 
			it is impossible.
	Constraints:
		1 <= numCourses <= 2000
		0 <= prerequisites.length <= 5000
		prerequisites[i].length == 2
		0 <= ai, bi < numCourses
		All the pairs prerequisites[i] are unique.

 */
public class Problem48 {

	public static void main(String[] args) {
//		int[][] prerequisites = {{1,0}};
		int[][] prerequisites = {{1,4},{2,4},{3,1},{3,2}};
		int numCourses = 5;
		boolean res = canFinish(numCourses, prerequisites);
		System.out.println("Result :: " + res);
	}

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
    	List<List<Integer>> graph = getGraph(numCourses, prerequisites);
    	
    	boolean[] visited = new boolean[numCourses];
    	boolean[] inStack = new boolean[numCourses];
    	for(int i = 0; i < numCourses; i++) {
    		if(!visited[i] && hasCycle(graph, visited, inStack, i)) {
    			return false;
    		}
    	}
    	
    	return true;
    }
    
    public static boolean hasCycle(List<List<Integer>> graph, boolean[] visited,boolean[] inStack, int n) {
    	visited[n] = true;
    	inStack[n] = true;
    	
    	for(int to : graph.get(n)) {
    		if(!visited[to]) {
    			if(hasCycle(graph, visited, inStack, to))
    				return true;
    		}else if(inStack[to]) {
    			return true;
    		}
    	}
    	inStack[n] = false;
    	return false;
    }
    
    static List<List<Integer>> getGraph(int numCourses, int[][] prerequisites){
    	List<List<Integer>> graph = new ArrayList<>();
    	
    	for(int i = 0; i < numCourses; i++) {
    		graph.add(new ArrayList<Integer>());
    	}
    	
    	for(int[] edge : prerequisites) {
    		graph.get(edge[1]).add(edge[0]);
    	}
    	
    	return graph;
    }

}
