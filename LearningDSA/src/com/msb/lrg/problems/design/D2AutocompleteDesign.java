package com.msb.lrg.problems.design;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*

🚨 Why That Solution Is NOT Optimized
	In previous design:
		TrieNode:
		   Map<String, Integer> freqMap
		Meaning:
			If prefix "i" matches 1M sentences:
				Node stores → 1M sentences
		Then query:
			Build PQ → expensive
		So:
			❌ High memory
			❌ High query latency
			❌ Not Google-level design
	This is only Leetcode acceptable, not FAANG production.
✅ REAL Optimized FAANG Idea
	Store only:
		Top-K sentences per node
		NOT all sentences.
		This is key optimization.
🧠 How Optimized Trie Works
	Instead of:
		freqMap (all sentences)
	We store:
		List<String> topK
	So:
		Example:
			Prefix "i" matches:
			1M sentences
		But node stores:
			ONLY top 3
		So:
			Query becomes:
				O(1)
		This is true optimized autocomplete.
🔥 Tradeoff
	Optimized design:
		✔ Fast query
		❌ Slower insert
	Because:
		We must maintain sorted top-K.
	But in real systems:
		👉 Query >> Insert
	So optimization is correct.
🧠 Insert Logic in Optimized Design
	When inserting sentence:
		At each node:
			Update frequency
			Recompute top-K list
			Trim to K
		So memory:
			Nodes × K
		NOT:
			Nodes × N
		Huge difference.
📊 Complexity Comparison
	❌ Non-Optimized Trie
	Insert:
		O(L)
	Query:
		O(N log N)
	Space:
		O(N × prefixes)
✅ Optimized Trie (Real FAANG Coding)
	Insert:
		O(L log K)
	Query:
		O(1)
	Space:
		O(prefixes × K)
Massive improvement.


 */
public class D2AutocompleteDesign {

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
		
		static class AutoComplete {
			
			TrieNode root;
			StringBuilder current;
			TrieNode node;
			int K = 3;
			
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
					updateTopK(curr);
				}
			}
			
		    private void updateTopK(TrieNode node) {
		        PriorityQueue<String> pq = new PriorityQueue<>(
		            (a, b) -> {
		                int diff = node.freqMap.get(a) - node.freqMap.get(b);
		                if (diff == 0) return b.compareTo(a);
		                return diff;
		            });

		        for (String s : node.freqMap.keySet()) {
		            pq.offer(s);
		            if (pq.size() > K) pq.poll();
		        }

		        List<String> list = new ArrayList<>();
		        while (!pq.isEmpty()) list.add(pq.poll());
		        Collections.reverse(list);

		        node.topK = list;
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
				
				return node.topK;
			}
			
		}
		
		
		static class TrieNode {	
			Map<Character, TrieNode> children = new HashMap<>();
			HashMap<String, Integer> freqMap = new HashMap<>();
			List<String> topK = new ArrayList<>();
		}		

}
