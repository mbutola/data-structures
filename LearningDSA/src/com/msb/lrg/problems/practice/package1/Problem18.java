package com.msb.lrg.problems.practice.package1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
Word Ladder :: LeetCode (127, Hard)
	A transformation sequence from word beginWord to word endWord using a dictionary 
	wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
		Every adjacent pair of words differs by a single letter.
		Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
		sk == endWord
		Given two words, beginWord and endWord, and a dictionary wordList, return the number of words 
		in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
	Example 1:
		Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
		Output: 5
		Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which 
		is 5 words long.
	Example 2:
		Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
		Output: 0
		Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
	Constraints:
		1 <= beginWord.length <= 10
		endWord.length == beginWord.length
		1 <= wordList.length <= 5000
		wordList[i].length == beginWord.length
		beginWord, endWord, and wordList[i] consist of lowercase English letters.
		beginWord != endWord
		All the words in wordList are unique.
Solution
	Here is the cleanest explanation + optimal BFS solution for
	LeetCode 127 – Word Ladder.
	This problem is a shortest path in an unweighted graph, where:
		Nodes = words in the wordList
		Edges = words differing by exactly 1 letter
		We must find the shortest transformation length from beginWord → endWord.
	Perfect fit for BFS.
	Why BFS?
		We need the minimum number of steps to reach endWord.
		Each word-change costs exactly 1 step, i.e., edges are unweighted → BFS guarantees shortest path.
	Optimization Trick (very important)
		Instead of comparing every word with every other word (O(N² × L)),
		we create intermediate patterns like:
			dog → *og
			dog → d*g
			dog → do*
		Store all words that match each pattern.
		Then adjacency lookup becomes fast.

 */
public class Problem18 {

	public static void main(String[] args) {
		 String beginWord = "hit";
		 String endWord = "cog";
		 List<String> wordList = List.of("hot","dot","dog","lot","log","cog");

		 int res = ladderLength(beginWord, endWord, wordList);
		 System.out.println("Result :: " + res);

	}

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        
    	if(beginWord.length() != endWord.length())
    		return 0;
    	
    	if(!wordList.contains(endWord))
    		return 0;
    	
    	Map<String, List<String>> patternMap = new HashMap<>();
    	
    	for(String word : wordList){
    		for(int i = 0; i < word.length(); i++) {
    			String pattern = word.substring(0,i) + "*" + word.substring(i + 1);
    			patternMap.computeIfAbsent(pattern, k -> new ArrayList<>()).add(word);
    		}
    	}
    	
    	Map<String, String> parentMap = new HashMap<>(); 
    	Queue<Pair<String, Integer>> queue = new LinkedList<>();
    	queue.offer(new Pair<>(beginWord, 1));
    	Set<String> visited = new HashSet<>();
    	visited.add(beginWord);
    	
    	while(!queue.isEmpty()) {
    		Pair<String, Integer> curr = queue.poll();
    		String word = curr.first;
    		int level = curr.second;
    		
    		for(int i = 0; i < word.length(); i++) {
    			String pattern = word.substring(0,i) + "*" + word.substring(i + 1);
    			List<String> neighbours = patternMap.getOrDefault(pattern, new ArrayList<>());
    			if(neighbours == null)
    				System.out.println(pattern);
    			
    			for(String nei : neighbours) {
    				
    				if(nei.equals(endWord)) {
    					parentMap.put(endWord, word);
    				
    					List<String> result = new LinkedList<>();
    					String parent = endWord;
    					while(parent != beginWord) {
    						result.add(parent);
    						parent = parentMap.get(parent);
    					}
    					result.add(parent);
    					Collections.reverse(result);
    					System.out.println(result.toString());
    					return level + 1;
    				}
    				
    				if(!visited.contains(nei)) {
    					parentMap.put(nei, word);
    					visited.add(nei);
    			    	queue.add(new Pair<>(nei, level + 1));
    				}
    			}
    		}
    	}
    	return 0;
    }

    static class Pair <U,V> {
    	U first;
    	V second;
    	
    	Pair(U first, V second) {
    		this.first = first;
    		this.second = second;
    	}
    }
}

