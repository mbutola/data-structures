package com.msb.lrg.problems.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphCourseSchedule {

	public static void main(String[] args) {
		int numCourses = 2;
		int[][] prerequisites = {{0,1}};
		List<List<Integer>> graph = getGraph(numCourses, prerequisites);
		
		GraphCourseSchedule problem = new GraphCourseSchedule();
		boolean res = problem.canFinishBFS(numCourses, graph);
		System.out.println("Can Finish BFS :: " + res);

		res = problem.canFinishDFS(numCourses, graph);
		System.out.println("Can Finish DFS :: " + res);
	}
	
	boolean canFinishDFS(int numCourses, List<List<Integer>> graph) {
		boolean[] visited = new boolean[numCourses];
		boolean[] onPath = new boolean[numCourses];
		
		for(int i=0; i<numCourses; i++) {
			if(!visited[i] && isCycleDFS(graph, visited, onPath, i))
				return false;
		}
		return true;
	}
	
	boolean isCycleDFS(List<List<Integer>> graph, boolean[] visited, boolean[] onPath, int i) {
		
		if(onPath[i])
			return true;
				
		if(visited[i])
			return false;
		
		onPath[i] = visited[i] = true;
		
		for(int edgeTo : graph.get(i)) {
			if(isCycleDFS(graph, visited, onPath, edgeTo))
				return true;
			
		}
		return onPath[i] = false;
	}
	
	
	boolean canFinishBFS(int numCourses, List<List<Integer>> graph) {
		
		int[] inDegree = new int[numCourses];
		
		for(int i=0; i<numCourses; i++) {
			for(int edgeTo: graph.get(i)) {
				inDegree[edgeTo]++;
			}
		}
		
		Queue<Integer> queue = new LinkedList<>();
		
		for(int i=0; i<numCourses; i++) {
			if(inDegree[i] == 0)
				queue.add(i);
		}
		
		while(!queue.isEmpty()){
			int edgeFrom = queue.poll();
			
			for(int edgeTo : graph.get(edgeFrom)) {
				inDegree[edgeTo]--;
			
				if(inDegree[edgeTo] == 0)
					queue.add(edgeTo);
			}
		}
		
		for(int i=0; i<numCourses; i++) {
			if(inDegree[i] != 0)
				return false;
		}

		return true;
        
    }
	
	static List<List<Integer>> getGraph(int numCourses, int[][] prerequisites){
		List<List<Integer>> graph = new ArrayList<>(numCourses);
		for(int i=0; i<numCourses; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int[] edge: prerequisites){
			graph.get(edge[1]).add(edge[0]);
		}
		
		return graph;
		
	}
}
