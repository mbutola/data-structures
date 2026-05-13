package com.msb.lrg.problems.frequentlyasked;

/*

🔤 Valid Anagram (character frequency)
	🧠 One-line idea
		👉 Two strings are anagrams if every character appears the same number of times in both
	🧩 Problem
		s = "anagram"  
		t = "nagaram"
	👉 Output:
		true
	⚙️ Technique: Frequency count (fastest)
		👉 Use an array of size 26 (for lowercase letters)
	🔄 Steps
		1. If lengths differ → false  
		2. Count chars in s  
		3. Subtract using t  
		4. If all counts = 0 → true

 */
public class F018ValidAnagram {

    public static void main(String[] args) {
        System.out.println(isAnagram("anagram", "nagaram")); // true
        System.out.println(isAnagram("rat", "car"));         // false
    }

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] count = new int[26];

        // count s
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // subtract using t
        for (char c : t.toCharArray()) {
            count[c - 'a']--;
        }

        // check all zero
        for (int num : count) {
            if (num != 0) return false;
        }

        return true;
    }

}
