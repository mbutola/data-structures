package com.msb.lrg.problems.frequentlyasked;

/*

	⛰️ Find Peak Element (Binary Search)
	🧠 One-line idea
		👉 Compare mid with next element — move toward the side that’s rising
	🧩 Problem
		A peak element is:
			nums[i] > nums[i-1] AND nums[i] > nums[i+1]
		👉 Return index of any peak
	🔍 Example
		[1, 2, 3, 1]
		👉 Peak = 3 (index 2)
	⚙️ Technique: Binary Search
	🔄 Core logic
		If nums[mid] < nums[mid + 1]:
		    move right (peak must be there)
		else:
		    move left (including mid)
	🧠 Why it works
		👉 Think in terms of slope:
			Going up → peak ahead  
			Going down → peak behind
	🔍 Example walk
		[1,2,3,1]
		mid=1 → 2 < 3 → go right  
		mid=2 → 3 > 1 → peak found

 */
public class F012FindPeak {

    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        System.out.println(findPeakElement(nums)); // 2
    }

    public static int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;   // go right
            } else {
                right = mid;      // go left
            }
        }

        return left;
    }

}
