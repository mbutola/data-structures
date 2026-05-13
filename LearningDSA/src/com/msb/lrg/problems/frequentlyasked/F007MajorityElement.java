package com.msb.lrg.problems.frequentlyasked;

/*

	🗳️ Majority Element (> n/2) — Boyer–Moore Voting
	🧠 One-line idea
		👉 Pair off different elements and cancel them; the majority element survives
	🧩 Problem
		nums = [2, 2, 1, 1, 1, 2, 2]
	👉 Output:
		2   // appears > n/2 times
	⚙️ Technique: Boyer–Moore Voting
		Maintain:
			candidate
			count
	🔄 Core logic
		If count == 0 → set candidate  
		If same as candidate → count++  
		Else → count--
	🔍 Why it works (intuition)
		👉 Every time you see a different number:
			It cancels one occurrence of the majority
		👉 Since majority > n/2:
			It can never be fully canceled
	🔍 Step-by-step (short)
		[2,2,1,1,1,2,2]
		candidate=2, count=1  
		2 → count=2  
		1 → count=1  
		1 → count=0  
		1 → candidate=1, count=1  
		2 → count=0  
		2 → candidate=2, count=1
	👉 Final candidate = 2

 */
public class F007MajorityElement {

    public static void main(String[] args) {
        int[] nums = {2,2,1,1,1,2,2};
        System.out.println(majorityElement(nums)); // 2
    }

    public static int majorityElement(int[] nums) {
        int candidate = 0, count = 0;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }

            if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }

}
