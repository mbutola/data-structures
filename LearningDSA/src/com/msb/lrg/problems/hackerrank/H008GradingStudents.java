package com.msb.lrg.problems.hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*

	HackerLand University has the following grading policy:
		Every student receives a  in the inclusive range from  to .
		Any  less than  is a failing grade.
	Sam is a professor at the university and likes to round each student's  according to these rules:
		If the difference between the  and the next multiple of  is less than , round  up to the next multiple of .
		If the value of  is less than , no rounding occurs as the result will still be a failing grade.
	Examples
		 round to  (85 - 84 is less than 3)
		 do not round (result is less than 38)
		 do not round (60 - 57 is 3 or higher)
		Given the initial value of  for each of Sam's  students, write code to automate the rounding process.
	Function Description
		Complete the function  with the following parameter(s):
			: the grades before rounding
	Returns
		: the grades after rounding
	Input Format
		The first line contains a single integer, , the number of students.
		Each line  of the  subsequent lines contains a single integer, .
	Sample Input 0
		4
		73
		67
		38
		33
	Sample Output 0
		75
		67
		40
		33

 */
public class H008GradingStudents {

	public static void main(String[] args) {
		List<Integer> grades = Arrays.asList(73, 67, 38, 33);
		List<Integer> res = gradingStudents(grades);
		System.out.println(res.toString());
	}

    public static List<Integer> gradingStudents(List<Integer> grades) {
    	List<Integer> res = new ArrayList<>();
    	for(int grade : grades) {
    		if(grade >= 38 && grade % 5 >= 3) {
    			grade += (5 - grade % 5);
    		}
    		res.add(grade);
    	}
    	return res;
    }

//    public static List<Integer> gradingStudents(List<Integer> grades) {
//    	List<Integer> res = new ArrayList<>();
//    	for(int grade : grades) {
//    		if(grade >=38) {
//	    		int nextMultiple = (grade/5 + 1) * 5; 
//	    		if(nextMultiple - grade < 3) {
//	        		grade = nextMultiple;
//	    		}
//    		}
//    		res.add(grade);
//    	}
//    	return res;
//    }
}
