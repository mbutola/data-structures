package com.msb.lrg.problems.practice.package4;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*

Course Schedule :: LeetCode (1462, medium)
	There are a total of numCourses courses you have to take, labeled from 0 to 
	numCourses - 1. You are given an array prerequisites where 
	prerequisites[i] = [ai, bi] indicates that you must take course ai first if 
	you want to take course bi.
		For example, the pair [0, 1] indicates that you have to take course 0 
		before you can take course 1.
	Prerequisites can also be indirect. If course a is a prerequisite of course b, 
	and course b is a prerequisite of course c, then course a is a prerequisite of 
	course c.
	You are also given an array queries where queries[j] = [uj, vj]. For the 
	jth query, you should answer whether course uj is a prerequisite of course 
	vj or not.
	Return a boolean array answer, where answer[j] is the answer to the jth 
	query.
	Example 1:
		Input: numCourses = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
		Output: [false,true]
		Explanation: The pair [1, 0] indicates that you have to take course 1 before you can take course 0.
		Course 0 is not a prerequisite of course 1, but the opposite is true.
	Example 2:
		Input: numCourses = 2, prerequisites = [], queries = [[1,0],[0,1]]
		Output: [false,false]
		Explanation: There are no prerequisites, and each course is independent.
	Example 3:
		Input: numCourses = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
		Output: [true,true]
	Constraints:
		2 <= numCourses <= 100
		0 <= prerequisites.length <= (numCourses * (numCourses - 1) / 2)
		prerequisites[i].length == 2
		0 <= ai, bi <= numCourses - 1
		ai != bi
		All the pairs [ai, bi] are unique.
		The prerequisites graph has no cycles.
		1 <= queries.length <= 104
		0 <= ui, vi <= numCourses - 1
		ui != vi

 */
public class Problem483 {

	public static void main(String[] args) {
//		int numCourses = 2;
//		int[][] prerequisites = {};
//		int[][] queries = {{0,1},{1,0}};
		int numCourses = 4;
		int[][] prerequisites = {{2,3},{2,1},{0,3},{0,1}};
		int[][] queries = {{0,1},{0,3},{2,3},{3,0},{2,0},{0,2}};
		List<Boolean> results = checkIfPrerequisite(numCourses, prerequisites, queries);
		System.out.println("Results :: " + results.toString());
	}
	
    public static List<Boolean> checkIfPrerequisite(int numCourses, 
    									int[][] prerequisites, 
    									int[][] queries) {
    	int[] indeg = new int[numCourses];
    	List<List<Integer>> graph = getGraph(numCourses, prerequisites, indeg);
    	
    	List<Set<Integer>> pre = new ArrayList<>();
        for (int i = 0; i < numCourses; i++)
        	pre.add(new HashSet<Integer>());
        	
        Deque<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < numCourses; i++)
            if (indeg[i] == 0)
                queue.offer(i);
        
        while(!queue.isEmpty()) {
        	
        	int u = queue.poll();
        	
        	for(int v : graph.get(u)) {
        		pre.get(v).add(u);
        		pre.get(v).addAll(pre.get(u));

            	if(--indeg[v] == 0)
            		queue.offer(v);
        	}
        }
        
        List<Boolean> res = new ArrayList<>();
        for (int[] query : queries)
            res.add(pre.get(query[1]).contains(query[0]));
    	
        return res;
    }

    static List<List<Integer>> getGraph(int numCourses, int[][] prerequisites, int[] indeg){
    	List<List<Integer>> graph = new ArrayList<>();
    	
    	for(int i = 0; i < numCourses; i++) {
    		graph.add(new ArrayList<Integer>());
    	}
    	
    	for(int[] edge : prerequisites) {
    		graph.get(edge[0]).add(edge[1]);
    		indeg[edge[1]]++;
    	}
    	
    	return graph;
    }


}
