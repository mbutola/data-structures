package com.msb.lrg.problems.practice.package2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/*

Alien Dictionary :: LeetCode (269, Hard)
	You are given a list of words sorted according to an unknown alien language.
	Your task is to deduce the order of characters in that alien alphabet.
	If the order is invalid, return "".
	Example
		words = ["wrt", "wrf", "er", "ett", "rftt"]
	Output:
		"wertf"

 */
public class Problem23 {

	public static void main(String[] args) {
		int[][] data = {{5,0},{5,2},{4,0},{4,1},{2,3},{3,1}};
		int N = 6;
		List<List<Integer>> graph = getGraph(data, N);
		ArrayList<Integer> resBFS = topoSortBFS(graph, N);
		System.out.println("Result BFS :: " + resBFS);
		ArrayList<Integer> resDFS = topoSortDFS(graph, N);
		System.out.println("Result DFS :: " + resDFS);
		
//		String[] words = {"wrt", "wrf", "er", "ett", "rftt"};
		String[] words = {"baa", "abcd", "abca", "cab", "cad"};
		String resAlien =  alienOrder(words);
		System.out.println("Result alien :: " + resAlien);
	}
	
	public static String alienOrder(String[] words){

		Map<Character, Set<Character>> graph = new HashMap<>();
		Map<Character, Integer> indegree = new HashMap<>();
		
		for(String word : words) {
			for(char c : word.toCharArray()) {
				graph.putIfAbsent(c, new HashSet<Character>());
				indegree.putIfAbsent(c, 0);
			}
		}
		
		for(int i = 0; i < words.length -1; i++) {
			String w1 = words[i];
			String w2 = words[i + 1];
			
			if(w1.startsWith(w2) && w1.length() > w2.length()) {
				System.out.println(w1 + "::" + w2);
				return "";
			}

			int len = Math.min(w1.length(), w2.length());
			for(int j = 0; j < len; j++) {
				char c1 = w1.charAt(j);
				char c2 = w2.charAt(j);
				if(c1 != c2) {
					if(!graph.get(c1).contains(c2)){
						graph.get(c1).add(c2);
						indegree.put(c2, indegree.get(c2) + 1);
					}
					break;
				}
			}	
		}

		Queue<Character> queue = new LinkedList<>();
		for(char c : indegree.keySet()) {
			if(indegree.get(c) == 0)
				queue.offer(c);
		}
		
		StringBuilder sb = new StringBuilder();
		while(!queue.isEmpty()) {
			char c  = queue.poll();
			sb.append(c);
			for(char nei : graph.get(c)) {
				indegree.put(nei, indegree.get(nei) - 1);
				if(indegree.get(nei) == 0)
					queue.add(nei);
			}
		}
		
		return sb.length() == indegree.size() ? sb.toString() : "";
	}
	
	public static ArrayList<Integer> topoSortDFS(List<List<Integer>> graph, int N){
		ArrayList<Integer> res = new ArrayList<>();
		boolean[] visited = new boolean[N];
		Stack<Integer> st = new Stack<>();
		
		for(int i = 0; i < N; i++) {
			if(!visited[i]){
				dfs(graph, st, visited, i);
			}
		}
		
		while(!st.isEmpty()) {
			res.add(st.pop());
		}
		
		return res;
	}
	
	public static void dfs(List<List<Integer>> graph, Stack<Integer> st, boolean[] visited, int n) {
		visited[n] = true;
		for(int to : graph.get(n)) {
			if(!visited[to])
				dfs(graph, st, visited, to);
		}

		st.add(n);
	}
	
	public static ArrayList<Integer> topoSortBFS(List<List<Integer>> graph, int N){
		ArrayList<Integer> res = new ArrayList<>();
		
		int[] in = new int[N];

		for(List<Integer> toList : graph) {
			for(int to : toList) {
				in[to]++;
			}
		}
		
		Queue<Integer> queue = new LinkedList<Integer>();
		for(int i = 0; i < N; i++) {
			if(in[i] == 0)
				queue.offer(i);
		}
		
		while(!queue.isEmpty()) {
			int from  = queue.poll();
			res.add(from);
			for(int to : graph.get(from)) {
				if(--in[to] == 0)
					queue.add(to);
			}
		}
		return res;
	}
	
	public static List<List<Integer>> getGraph(int[][] data, int N) {
		List<List<Integer>> graph = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		for(int i = 0; i < data.length; i++) {
			graph.get(data[i][0]).add(data[i][1]);
		}
		
		return graph;
	}

}
