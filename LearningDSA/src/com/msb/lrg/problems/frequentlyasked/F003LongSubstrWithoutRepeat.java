package com.msb.lrg.problems.frequentlyasked;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*

	🔤 Longest Substring Without Repeating Characters
	🧠 One-line idea
		👉 Use a sliding window and keep expanding until a duplicate appears, then shrink from left
	🧩 Problem
		Given a string:
			"abcabcbb"
		👉 Find length of longest substring with all unique characters
		✅ Answer
			"abc" → length = 3
	⚙️ Technique: Sliding Window + Set
		Maintain:
			left pointer
			right pointer
			Set → stores current unique characters
		🔄 Core logic
			Move right → add char  
			If duplicate → move left until unique  
			Track max length
	🔍 Step-by-step (short)
		"abcabcbb"
		a → [a] → len=1  
		b → [a,b] → len=2  
		c → [a,b,c] → len=3  
		a → duplicate → remove from left  

 */
public class F003LongSubstrWithoutRepeat {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb")); // 3
    }

    /*
     * Better optimization (HashMap version)
     * 👉 Instead of removing one-by-one:
     */
    public static int lengthOfLongestSubstring(String s) {
    	Map<Character, Integer> map = new HashMap<>();
    	int left = 0, maxLength = 0;

    	for (int right = 0; right < s.length(); right++) {
    	    char ch = s.charAt(right);

    	    if (map.containsKey(ch)) {
    	        left = Math.max(left, map.get(ch) + 1);
    	    }

    	    map.put(ch, right);
    	    maxLength = Math.max(maxLength, right - left + 1);
    	}
    	
    	return maxLength;    
    }

    public static int lengthOfLongestSubstring2(String s) {
        Set<Character> set = new HashSet<>();

        int left = 0, maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);

            while (set.contains(ch)) {
                set.remove(s.charAt(left));
                left++;
            }

            set.add(ch);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

}
