package com.msb.lrg.problems.frequentlyasked;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/*

Course Schedule
	You are given:
		numCourses
		prerequisites
	Need to determine:
		Can all courses be finished?
	Key Observation
		This is a:
			Cycle Detection in Directed Graph
		problem.
	Why?
		If courses depend on each other cyclically:
			0 → 1 → 2 → 0
		then impossible to finish.
	Example 1
		numCourses = 2
		prerequisites = [[1,0]]
		Meaning:
			To take 1, complete 0 first
		Graph:
			0 → 1
			No cycle.
		Answer:
			true
	Example 2
		numCourses = 2
		prerequisites = [
		  [1,0],
		  [0,1]
		]
		Graph:
			0 → 1
			↑   ↓
			└───┘
			Cycle exists.
		Answer:
			false
	Technique: Topological Sort (Kahn's Algorithm)
		Topological sort works ONLY for:
			DAG (Directed Acyclic Graph)
		If topo sort cannot process all nodes:
			cycle exists
	Main Idea
		Courses with:
			indegree = 0
		have no prerequisites.
		We can take them first.
	Steps
		Build graph
		Compute indegree
		Push indegree-0 nodes into queue
		Process queue
		Reduce neighbors indegree
		Count processed nodes
	Final Rule
		If processed courses == total courses:
			No cycle
		Else:
			Cycle exists

 */
public class F032CourseSchedule {

	public static void main(String[] args) {
        int numCourses = 4;

        int[][] prerequisites = {{1,0},{2,0},{3,1},{3,2}};

        System.out.println(
                canFinish(numCourses, prerequisites));
	}
	
	static boolean canFinish(int numCourses, int[][] prerequisites) {
		
		int[] indegree = new int[numCourses];
		int completed = 0;
		Queue<Integer> queue = new ArrayDeque<>();
		
		List<List<Integer>> graph = new ArrayList<>();
		
		for(int i = 0; i < numCourses; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int[] edge : prerequisites) {
			int course = edge[0];
			int prereq = edge[1];
			graph.get(prereq).add(course);
			indegree[course]++;
		}

		for(int i = 0; i < numCourses; i++) {
			if(indegree[i] == 0) 
				queue.offer(i);
		}
		
		while(!queue.isEmpty()) {
			int curr = queue.poll();
			completed++;
			for(int neighbour : graph.get(curr)) {
				indegree[neighbour]--;
				if(indegree[neighbour] == 0) 
					queue.offer(neighbour);
			}
		}
		
		return numCourses == completed;
	}
}
