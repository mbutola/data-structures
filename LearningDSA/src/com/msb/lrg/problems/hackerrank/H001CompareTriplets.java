package com.msb.lrg.problems.hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

	Alice and Bob each created one problem for HackerRank. A reviewer rates the two challenges, awarding points 
	on a scale from 1 to 100 for three categories: problem clarity, originality, and difficulty.
	The rating for Alice's challenge is the triplet a = (a[0], a[1], a[2]), and the rating for Bob's challenge 
	is the triplet b = (b[0], b[1], b[2]).
	The task is to calculate their comparison points by comparing each category:
		If a[i] > b[i], then Alice is awarded 1 point.
		If a[i] < b[i], then Bob is awarded 1 point.
		If a[i] = b[i], then neither person receives a point.
	Example
			a = [1, 2, 3]
			b = [3, 2, 1]
		For elements *0*, Bob is awarded a point because a[0] < b[0].
		For the equal elements a[1] and b[1], no points are earned.
		Finally, for elements 2, a[2] > b[2] so Alice receives a point.
		The return array is [1, 1] with Alice's score first and Bob's second.
	Function Description
		Complete the function compareTriplets with the following parameter(s):
			int a[3]: Alice's challenge rating
			int b[3]: Bob's challenge rating
		Returns
			int[2]: the first element is Alice's score and the second is Bob's score
		Input Format
			The first line contains 3 space-separated integers, a[0], a[1], and a[2], the respective values in triplet a.
			The second line contains 3 space-separated integers, b[0], b[1], and b[2], the respective values in triplet b.
	Constraints
		1 ≤ a[i] ≤ 100
		1 ≤ b[i] ≤ 100
	Sample Input 0
		5 6 7
		3 6 10
	Sample Output 0
		1 1

 */
public class H001CompareTriplets {

	public static void main(String[] args) {
		List<Integer> a = Arrays.asList(1, 2, 3);
		List<Integer> b = Arrays.asList(3, 2, 1);
		List<Integer> res = compareTriplets(a, b);
		System.out.println(res.toString());
	}

    public static List<Integer> compareTriplets(List<Integer> a, List<Integer> b) {
        int alice = 0;
        int bob = 0;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) > b.get(i)) {
                alice++;
            } else if (a.get(i) < b.get(i)) {
                bob++;
            }
        }
        return Arrays.asList(alice, bob);
    }
    
//     public static List<Integer> compareTriplets(List<Integer> a, List<Integer> b) {
//         Map<String, Integer> map = new HashMap<>(Map.of("a", 0, "b", 0));
//         for(int i = 0; i < a.size(); i++){
//             if(a.get(i) > b.get(i))
//                 map.put("a", map.get("a")+ 1);
//             else if(a.get(i) < b.get(i))
//                 map.put("b", map.get("b") + 1);
//         }
//         return new ArrayList<Integer>(map.values());
//     }

    // public static List<Integer> compareTriplets(List<Integer> a, List<Integer> b) {
    //     int[] res = new int[2];
    //     for(int i = 0; i < a.size(); i++){
    //         if(a.get(i) > b.get(i))
    //             res[0]++;
    //         else if(a.get(i) < b.get(i))
    //             res[1]++;
    //     }
    //     return Arrays.stream(res)
    //                 .boxed()
    //                 .collect(Collectors.toList());
    // }

}
