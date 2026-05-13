package com.msb.lrg.problems.frequentlyasked;

/*

	🧮 Product of Array Except Self (no division)
	🧠 One-line idea
		👉 For each index, multiply all elements to its left (prefix) and right (suffix)
	🧩 Problem
		nums = [1, 2, 3, 4]
	👉 Output:
		[24, 12, 8, 6]
	⚙️ Why not simple multiplication?
		👉 You might think:
			totalProduct / nums[i]
		❌ Not allowed (division constraint)
		❌ Fails with zeros
	⚙️ Technique: Prefix + Suffix
		Step 1: Prefix product
			prefix[i] = product of all elements before i
			[1, 1, 2, 6]
		Step 2: Suffix product
			suffix[i] = product of all elements after i
			[24, 12, 4, 1]
		Step 3: Multiply
			result[i] = prefix[i] * suffix[i]
			[24, 12, 8, 6]
	⚙️ Optimized (O(1) extra space)
	👉 No separate suffix array needed

 */
public class F009ProductExceptSelf {

	public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int[] res = productExceptSelf(nums);

        for (int num : res) {
            System.out.print(num + " ");
        }
        // Output: 24 12 8 6
	}
	
	static int[]  productExceptSelf(int[] nums) {
		int n = nums.length;
		int[] results = new int[n];
		
		results[0] = 1;	
		for(int i = 1; i < n; i++) {
			results[i] = results[i - 1] * nums[i - 1];
		}
		
		int suffix = 1;
		for(int i = n - 1; i >= 0; i--) {
			results[i] *= suffix;
			suffix *= nums[i];
		}
		
		return results;
	}

}
