package com.msb.lrg.problems.advance;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/*

🧩 Problem 2 — Group Employees by Dept and Find Highest Paid
	❓ Problem
		Given employees:
			class Emp {
			    String dept;
			    String name;
			    int salary;
			}
		Find the highest-paid employee per department.
		Output:
			Map<Dept, Emp>

	🧠 Explanation
		1️⃣ Group by department
			IT → [A,B]
			HR → [C,D]
		2️⃣ For each group → find max salary
		3️⃣ maxBy returns Optional → convert using collectingAndThen
		Collector concepts:
			groupingBy
			maxBy
			collectingAndThen

 */
public class Problem31 {

	public static void main(String[] args) {
		
		List<Emp> list = List.of(
								new Emp("IT","A",100),
								new Emp("IT","B",150),
								new Emp("HR","C",120),
								new Emp("HR","D",110));
        
		System.out.println(maxPerDept(list));
        // {IT=B(150), HR=C(120)}
	}

    public static Map<String, Emp> maxPerDept(List<Emp> emps) {
    	
    	return emps.stream()
    				.collect(Collectors.groupingBy(
    							e -> e.dept,
    							Collectors.collectingAndThen(
    										Collectors.maxBy(
    												Comparator.comparing(e -> e.salary)),
    										Optional::get)));
    
    }
    
	static 	class Emp {
	    String dept;
	    String name;
	    int salary;
	    
	    Emp(String d, String n, int s) {
	    	this.dept = d;
	    	this.name = n;
	    	this.salary = s;
	    }

	    public String toString() {
	        return name + "(" + salary + ")";
	    }
}

}
