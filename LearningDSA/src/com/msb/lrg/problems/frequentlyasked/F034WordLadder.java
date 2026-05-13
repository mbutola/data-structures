package com.msb.lrg.problems.frequentlyasked;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*

Word Ladder
	Transform:
		beginWord → endWord
	by changing:
		ONE letter at a time
	Each intermediate word must exist in dictionary.
	Need:
		shortest transformation length
	Example
		beginWord = "hit"
		endWord   = "cog"
		wordList = ["hot","dot","dog","lot","log","cog"]
	Valid Transformation
		hit → hot → dot → dog → cog
	Length:
		5
	Why BFS?
		We need:
			shortest path
		in an unweighted graph.
		BFS always finds shortest path first.
	Graph Interpretation
		Each word is a node.
		Edge exists if:
			words differ by exactly one character
		Example:
			hot ↔ dot
		because only:
			h → d
		changes.
	Main BFS Idea
		Starting from beginWord:
			Generate all one-letter transformations
			If valid dictionary word:
				push into queue
			Continue level by level
			First time reaching endWord = shortest path

 */
public class F034WordLadder {

	public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");

        System.out.println(ladderLength(beginWord, endWord,  wordList)); // 5
	}
	
	static int ladderLength(String beginWord, String endWord,  List<String> wordList) {
		int level = 0;
		Queue<String> queue = new ArrayDeque<>();
		queue.offer(beginWord);
		Set<String> dict = new HashSet<>(wordList);
		
		while(!queue.isEmpty()) {
			level++;
			int size = queue.size();
			for(int i = 0; i < size; i++) {
				String word = queue.poll();
				if(word.equalsIgnoreCase(endWord)) {
					return level;
				}
				
				char[] arr = word.toCharArray();
				for(int j = 0; j < 3; j++) {
					char original = arr[j];
					for(char ch = 'a'; ch < 'z'; ch++) {
						arr[j] = ch;
						String next = new String(arr);
						if(dict.contains(next)) {
							queue.offer(next);
							dict.remove(next);
						}					
					}
					arr[j] = original;
				}
			}
		}
		
		return 0;
	}

}
