package com.msb.lrg.problems.trie;

import java.util.HashMap;
import java.util.Map;

public class TrieDemo {

	public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("car");
        trie.insert("cat");
        trie.insert("dog");

        System.out.println(trie.search("car"));       // true
        System.out.println(trie.startsWith("ca"));    // true
        System.out.println(trie.search("cap"));       // false
	}

}

class TrieNode {
	public Map<Character, TrieNode> children = new HashMap<>();
	public boolean isEndOfWord = false;
}

class Trie {
	private TrieNode root;
	
	Trie(){
		root = new TrieNode();
	}
	
	public void insert(String word) {
		TrieNode node = root;
		for(Character c: word.toCharArray()) {
			node.children.putIfAbsent(c, new TrieNode());
			node = node.children.get(c);
		}
		node.isEndOfWord = true;;
	}

	public boolean search(String word) {
		TrieNode node = root;
		for(Character c: word.toCharArray()) {
			if(!node.children.containsKey(c)) {
				return false;
			}
			node = node.children.get(c);
		}
		return node.isEndOfWord;
	}

	public boolean startsWith(String prefix) {
		TrieNode node = root;
		for(Character c: prefix.toCharArray()) {
			if(!node.children.containsKey(c)) {
				return false;
			}
			node = node.children.get(c);
		}
		return true;
	}
}