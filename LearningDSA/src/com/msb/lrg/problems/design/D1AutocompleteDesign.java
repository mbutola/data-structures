package com.msb.lrg.problems.design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*

🧠 FAANG Problem — Design Autocomplete System
	📌 Problem Statement
		Design an autocomplete system that:
			Takes a list of historical sentences with frequency
			When user types characters → return top 3 suggestions
		Suggestions sorted by:
			Highest frequency
			Lexicographically smaller if tie
		Example:
			Input:
				sentences = ["i love you", "island", "iroman", "i love leetcode"]
				freq = [5,3,2,2]
			Typing:
				input: i
				output: ["i love you", "island", "i love leetcode"]
				input: i 
				output: ["i love you", "i love leetcode"]
				input: #
				→ store new sentence


 */
public class D1AutocompleteDesign {

	public static void main(String[] args) {
		String[] sentences = {"i love you", "island", "iroman", "i love leetcode"};
		Integer[] freq = {5,3,2,2};
		
		AutoComplete ac = new AutoComplete(sentences, freq);

        System.out.println(ac.input('i'));
        System.out.println(ac.input(' '));
        System.out.println(ac.input('a'));
        System.out.println(ac.input('#'));
        System.out.println(ac.input('i'));
        System.out.println(ac.input(' '));
        System.out.println(ac.input('a'));
        System.out.println(ac.input('b'));
	}
	
	public static void brute(Map<String, Integer> freq, String e) {
		freq.entrySet().stream()
					.filter( m -> m.getKey().startsWith(e))
					.sorted((a,b) ->  Integer.compare(b.getValue(), a.getValue()))
					.limit(3)
					.forEach(m -> System.out.println(m.getKey()));
	}
	
	static class AutoComplete {
		
		TrieNode root;
		StringBuilder current;
		TrieNode node;
		
		AutoComplete(String[] sentences, Integer[] freq){
			root = new TrieNode();
			current = new StringBuilder();
			node = root;
			
			for(int i = 0; i < sentences.length; i++) {
				insert(sentences[i], freq[i]);
			}
		}

		public void insert(String sentence, int count) {
			TrieNode curr = root;
			
			for(char c : sentence.toCharArray()) {
				curr.children.putIfAbsent(c, new TrieNode());
				curr = curr.children.get(c);
				curr.freqMap.put(sentence, curr.freqMap.getOrDefault(sentence, 0) + count);
			}
		}
		
		public List<String> input(char c){
			if(c == '#') {
				insert(current.toString(), 1);
				current = new StringBuilder();
				node = root;
				return new ArrayList<>();
			}
			
			current.append(c);
			
			if(node != null) {
				node = node.children.get(c);
			}

			if(node == null)
				return new ArrayList<>();
			
			PriorityQueue<String> pq = new PriorityQueue<>(
												(a,b) -> {
													int cmp = node.freqMap.get(b) - node.freqMap.get(a);
													if(cmp != 0)
														return cmp;
													
													return a.compareTo(b);
												});
			pq.addAll(node.freqMap.keySet());
			
			List<String> res = new ArrayList<>();
			for(int i = 0; i < 3 && !pq.isEmpty(); i++) {
				res.add(pq.poll());
			}
			
			return res;
		}
		
	}
	
	
	static class TrieNode {	
		Map<Character, TrieNode> children = new HashMap<>();
		HashMap<String, Integer> freqMap = new HashMap<>();
	}		

}
