package com.msb.lrg.problems.frequentlyasked;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*

🔗 Intersection of Arrays — find common elements
	🧠 One-line idea
		👉 Put elements of one array in a set, then pick elements from the second array that exist in it
	🧩 Problem
		nums1 = [1, 2, 2, 1]
		nums2 = [2, 2]
	👉 Output (unique common elements):
		[2]
	⚙️ Technique: Set intersection
		Use a Set to store elements of first array
		Check each element of second array
		Add common ones to result set
	🔄 Steps
		1. Put nums1 into set1  
		2. Loop nums2  
		3. If element in set1 → add to result set  

 */
public class F006IntersectionOfArrays {

	public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};

        System.out.println(Arrays.toString(intersection(nums1, nums2)));
        System.out.println(Arrays.toString(intersect(nums1, nums2)));
	}

    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> result = new HashSet<>();

        for (int num : nums1) {
            set1.add(num);
        }

        for (int num : nums2) {
            if (set1.contains(num)) {
                result.add(num);
            }
        }

        // convert set to array
        int[] res = new int[result.size()];
        int i = 0;
        for (int num : result) {
            res[i++] = num;
        }

        return res;
    }
	
    /*
		⚠️ Variant (with duplicates allowed)
		If duplicates matter (like [2,2]):
		👉 Use map (frequency count) instead of set
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int num : nums2) {
            if (map.getOrDefault(num, 0) > 0) {
                result.add(num);
                map.put(num, map.get(num) - 1);
            }
        }

        // convert list to array
        return result.stream().mapToInt(i -> i).toArray();
    }
}
