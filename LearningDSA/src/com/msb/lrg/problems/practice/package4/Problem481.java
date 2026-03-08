package com.msb.lrg.problems.practice.package4;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/*

Course Schedule II :: LeetCode (210, medium)
	There are a total of numCourses courses you have to take, labeled from 0 to 
	numCourses - 1. You are given an array prerequisites where 
	prerequisites[i] = [ai, bi] indicates that you must take course bi first if 
	you want to take course ai.
		For example, the pair [0, 1], indicates that to take course 0 you have 
		to first take course 1.
	Return the ordering of courses you should take to finish all courses. If there are 
	many valid answers, return any of them. If it is impossible to finish all courses, 
	return an empty array.
	Example 1:
		Input: numCourses = 2, prerequisites = [[1,0]]
		Output: [0,1]
		Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
	Example 2:
		Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
		Output: [0,2,1,3]
		Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
		So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
	Example 3:
		Input: numCourses = 1, prerequisites = []
		Output: [0]
	Constraints:
		1 <= numCourses <= 2000
		0 <= prerequisites.length <= numCourses * (numCourses - 1)
		prerequisites[i].length == 2
		0 <= ai, bi < numCourses
		ai != bi
		All the pairs [ai, bi] are distinct.

 */
public class Problem481 {

	public static void main(String[] args) {
		int[][] prerequisites = {{1,4},{2,4},{3,1},{3,2}};
		int numCourses = 5;
		int[] res = findOrder(numCourses, prerequisites);
		System.out.println("Result :: " + Arrays.toString(res));
	}

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
    	List<List<Integer>> graph = getGraph(numCourses, prerequisites);
    	
    	boolean[] visited = new boolean[numCourses];
    	boolean[] inStack = new boolean[numCourses];
    	Deque<Integer> stack = new ArrayDeque<>();
    	for(int i = 0; i < numCourses; i++) {
    		if(!visited[i] && hasCycle(graph, visited, inStack, stack, i)) {
    			return new int[0];
    		}
    	}
    	
    	int[] res = new int[stack.size()];
    	int i = 0;
    	while(!stack.isEmpty()) {
    		res[i++] = stack.pop();
    	}
    	return res;
    }
    
    public static boolean hasCycle(List<List<Integer>> graph, boolean[] visited, boolean[] inStack, Deque<Integer> stack, int n) {
    	visited[n] = true;
    	inStack[n] = true;
    	
    	for(int to : graph.get(n)) {
    		if(!visited[to]) {
    			if(hasCycle(graph, visited, inStack, stack, to))
    				return true;
    		}else if(inStack[to]) {
    			return true;
    		}
    	}
    	inStack[n] = false;
    	stack.push(n);
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
