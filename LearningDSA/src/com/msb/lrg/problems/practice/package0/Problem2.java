package com.msb.lrg.problems.practice.package0;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
LeetCode(648) Replace Words

In English, we have a concept called root, which can be followed by some other word to 
form another longer word - let's call this word derivative. For example, when the root 
"help" is followed by the word "ful", we can form a derivative "helpful".

Given a dictionary consisting of many roots and a sentence consisting of words 
separated by spaces, replace all the derivatives in the sentence with the root forming it. If 
a derivative can be replaced by more than one root, replace it with the root that has the 
shortest length.

Return the sentence after the replacement.

Example 1:
	Input: dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
	Output: "the cat was rat by the bat"
Example 2:
	Input: dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
	Output: "a a b c"

 */
public class Problem2 {

	public static void main(String[] args) {
		List<String> dictionary = List.of("cat","bat","rat");
		String sentence = "the cattle was rattled by the battery";
		String res = replaceWords(dictionary, sentence);
		System.out.println("Result :: " + res);

		dictionary = List.of("a","b","c");
		sentence = "aadsfasf absbs bbab cadsfafs";
		res = replaceWords(dictionary, sentence);
		System.out.println("Result :: " + res);
	}
	
	public static String replaceWords(List<String> dictionary, String sentence) {
        StringBuffer res = new StringBuffer();
        Trie root = new Trie();
        for(String entry: dictionary) {
        	root.insert(entry);
        }
        for(String word: sentence.split(" ")) {
        	if(res.length()>0)
        		res.append(" ");
        	res.append(root.findRoot(word));
        }
        return res.toString();
    }

}

class TrieNode {
	TrieNode[] children = new TrieNode[26];
	boolean isEnd = false;
}

class Trie {
	private TrieNode root;
	
	Trie(){
		root = new TrieNode();
	}
	
	public void insert(String word) {
		TrieNode node = root;
		for(Character c: word.toCharArray()) {
			node.children[c - 'a'] = new TrieNode();
			node = node.children[c - 'a'];
		}
		node.isEnd = true;;
	}

	public String findRoot(String word) {
		TrieNode node = root;
		StringBuffer sb = new StringBuffer();
		for(Character c: word.toCharArray()) {
			if(node.children[c - 'a'] == null) {
				return word;
			}
			sb.append(c);
			node = node.children[c - 'a'];
			if(node.isEnd) {
				return sb.toString();
			}
		}
		return word;
	}

}

//class TrieNode {
//	public Map<Character, TrieNode> children = new HashMap<>();
//	public boolean isEndOfWord = false;
//}
//
//class Trie {
//	private TrieNode root;
//	
//	Trie(){
//		root = new TrieNode();
//	}
//	
//	public void insert(String word) {
//		TrieNode node = root;
//		for(Character c: word.toCharArray()) {
//			node.children.putIfAbsent(c, new TrieNode());
//			node = node.children.get(c);
//		}
//		node.isEndOfWord = true;;
//	}
//
//	public String findRoot(String word) {
//		TrieNode node = root;
//		StringBuffer sb = new StringBuffer();
//		for(Character c: word.toCharArray()) {
//			if(!node.children.containsKey(c)) {
//				return word;
//			}
//			sb.append(c);
//			node = node.children.get(c);
//			if(node.isEndOfWord) {
//				return sb.toString();
//			}
//		}
//		return word;
//	}
//
//}
