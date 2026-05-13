package com.msb.lrg.problems.frequentlyasked;

/*

🔎 Substring Search (find first occurrence)
	🧠 One-line idea
		👉 Slide a window of size = pattern length and compare at each position
	🧩 Problem
		haystack = "sadbutsad"  
		needle   = "sad"
	👉 Output:
		0   // first occurrence
	⚙️ Technique: Sliding Window (naive)
		🔄 Steps
			1. Loop i from 0 → n - m  
			2. Compare substring starting at i  
			3. If match → return i
			n = length of haystack
			m = length of needle

 */
public class F020SubstringSearch {

    public static void main(String[] args) {
        System.out.println(strStr("sadbutsad", "sad")); // 0
        System.out.println(strStr("leetcode", "leeto")); // -1
    }

    public static int strStr(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();

        if (m == 0) return 0;

        for (int i = 0; i <= n - m; i++) {
            int j = 0;

            while (j < m && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }

            if (j == m) return i;
        }

        return -1;
    }

}
