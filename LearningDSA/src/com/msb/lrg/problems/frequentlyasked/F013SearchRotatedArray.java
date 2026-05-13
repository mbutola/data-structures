package com.msb.lrg.problems.frequentlyasked;

/*

	🔄 Search in Rotated Sorted Array (Binary Search)
	🧠 One-line idea
		👉 At least one half is always sorted — find which half and decide where to go
	🧩 Problem
		nums = [4,5,6,7,0,1,2], target = 0
	👉 Output:
		⚙️ Key observation
		👉 Even after rotation:
			One side (left or right) is always sorted
	🔄 Core logic
		At each step:
			1. Find mid  
			2. Check which half is sorted  
			3. Decide where target lies  
			4. Move left/right accordingly
	🧠 Decision rules
		Case 1: Left half is sorted
			nums[left] <= nums[mid]
				👉 If target in this range:
					nums[left] ≤ target < nums[mid]
					➡️ Move left
				Else:
					➡️ Move right
		Case 2: Right half is sorted
			👉 Otherwise:
				nums[mid] < nums[right]
				👉 If target in this range:
					nums[mid] < target ≤ nums[right]
					➡️ Move right
				Else:
					➡️ Move left



 */
public class F013SearchRotatedArray {

    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        System.out.println(search(nums, 0)); // 4
    }
	
	/*
		🧠 Core idea (one line)
			👉 In a rotated sorted array, at least one half (left or right) is always sorted.
		🔍 Example
			Example 1
			[4, 5, 6, 7, 0, 1, 2]
			Let’s pick:
				left = 0 → 4
				mid  = 3 → 7
				right= 6 → 2
			🔑 How to detect which side is sorted
			Rule:
				👉 If nums[left] <= nums[mid] → LEFT side is sorted
				👉 Else → RIGHT side is sorted
		✅ Apply to example
			nums[left] = 4
			nums[mid]  = 7
			4 <= 7 ✔ → LEFT is sorted
			So:
				[4, 5, 6, 7]  → sorted
				[0, 1, 2]     → rotated part
		🎯 Now what?
			Once you know which side is sorted, check:
				👉 Does target lie inside that sorted range?
			Case 1: Left is sorted
				Check:
					if target >= nums[left] && target < nums[mid]
						✔ If yes → search LEFT
						❌ Else → search RIGHT
			Case 2: Right is sorted
				Check:
					if target > nums[mid] && target <= nums[right]
						✔ If yes → search RIGHT
						❌ Else → search LEFT
	 */
    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) return mid;

            // left half sorted
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // right half sorted
            else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }
	
}
