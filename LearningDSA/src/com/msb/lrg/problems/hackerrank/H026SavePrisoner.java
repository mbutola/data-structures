package com.msb.lrg.problems.hackerrank;

/*

	A jail has a number of prisoners and a number of treats to pass out to them. Their jailer decides 
	the fairest way to divide the treats is to seat the prisoners around a circular table in sequentially 
	numbered chairs. A chair number will be drawn from a hat. Beginning with the prisoner in that chair, 
	one candy will be handed to each prisoner sequentially around the table until all have been distributed.
	The jailer is playing a little joke, though. The last piece of candy looks like all the others, but it 
	tastes awful. Determine the chair number occupied by the prisoner who will receive that candy.
	Example
		There are  prisoners,  pieces of candy and distribution starts at chair . The prisoners arrange 
		themselves in seats numbered  to . Prisoners receive candy at positions . The prisoner to be warned 
		sits in chair number .
	Function Description
		Complete the saveThePrisoner function in the editor below. It should return an integer representing 
		the chair number of the prisoner to warn.
		saveThePrisoner has the following parameter(s):
			int n: the number of prisoners
			int m: the number of sweets
			int s: the chair number to begin passing out sweets from
		Returns
			int: the chair number of the prisoner to warn
	Input Format
		The first line contains an integer, , the number of test cases.
		The next  lines each contain  space-separated integers:
			: the number of prisoners
			: the number of sweets
			: the chair number to start passing out treats at
	Sample Input 0
		2
		5 2 1
		5 2 2
	Sample Output 0
		2
		3

 */
public class H026SavePrisoner {

	public static void main(String[] args) {
		int n = 4;
		int m = 6;
		int s = 2;
		int res = saveThePrisoner(n, m, s);
		System.out.println(res);
	}

    public static int saveThePrisoner(int n, int m, int s) {
    	/*
    	 * Since mod can return 0 make everything zero base and then add 1 to result
    	 * So starting is s - 1
    	 * Since first candy is already given to s, we need to give m - 1 candies
    	 * The person who gets the candy is (s - 1) + (m - 1)
    	 * take mod ((s - 1) + (m - 1)) % m
    	 * add 1 to the result (((s - 1) + (m - 1)) % m) + 1
    	 */
        return (((s - 1) + (m - 1)) % n) + 1;
    }
	
}
