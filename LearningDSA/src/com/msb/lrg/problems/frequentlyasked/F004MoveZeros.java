package com.msb.lrg.problems.frequentlyasked;

/*

	🚚 Move Zeroes (keep order, push 0s to end)
	🧠 One-line idea
		👉 Use two pointers: place non-zero elements forward, push zeroes to the end
	🧩 Example
		Input:  [0, 1, 0, 3, 12]
		Output: [1, 3, 12, 0, 0]
	⚙️ Technique: Two pointers (stable)
		i → where next non-zero should go
		j → scans the array
	🔄 Core logic
		If nums[j] != 0:
		    swap(nums[i], nums[j])
		    i++
		👉 j always moves forward
	🔍 Walkthrough (short)
		[0,1,0,3,12]
		j=0 → 0 → skip  
		j=1 → 1 → swap with i=0 → [1,0,0,3,12], i=1  
		j=3 → 3 → swap → [1,3,0,0,12], i=2  
		j=4 → 12 → swap → [1,3,12,0,0]

 */
public class F004MoveZeros {

	public static void main(String[] args) {
		int[] nums = { 0, 1, 0, 3, 12 };
		moveZeroes(nums);

		for (int n : nums) {
			System.out.print(n + " ");
		}
		// Output: 1 3 12 0 0
	}

	/*
		Alternative (no swaps, fewer writes)
		Copy all non-zeroes forward
		Fill remaining with zeroes
	 */
	public static void moveZeroes(int[] nums) {
		int i = 0; // position for next non-zero

		for (int j = 0; j < nums.length; j++) {
			if (nums[j] != 0) {
				nums[i++] = nums[j];
			}
		}
			
		while(i < nums.length) {
			nums[i++] = 0;
		}
				
	}
	
	public static void moveZeroes2(int[] nums) {
		int i = 0; // position for next non-zero

		for (int j = 0; j < nums.length; j++) {
			if (nums[j] != 0) {
				// swap nums[i] and nums[j]
				int temp = nums[i];
				nums[i] = nums[j];
				nums[j] = temp;
				i++;
			}
		}
	}

}
