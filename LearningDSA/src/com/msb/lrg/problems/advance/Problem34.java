package com.msb.lrg.problems.advance;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*

🧩 Problem 4 — Multi-Level Grouping: Dept → Role → Avg Salary
	❓ Problem
		Employees:
			dept, role, salary
		Find:
			Map<Dept, Map<Role, AverageSalary>>

 */
public class Problem34 {

	public static void main(String[] args) {
        List<Emp> list = List.of(
                new Emp("IT","Dev",100),
                new Emp("IT","Dev",200),
                new Emp("IT","QA",150),
                new Emp("HR","Mgr",120)
        );

        System.out.println(avg(list));
        // {IT={Dev=150.0, QA=150.0}, HR={Mgr=120.0}}
	}
	
	public static Map<String, Map<String, Double>> avg(List<Emp> list) {
		
		return list.stream()
						.collect(Collectors.groupingBy(
										e -> e.dept,
										Collectors.groupingBy(
												e -> e.role,
												Collectors.averagingInt(e -> e.salary))));
	}

	static 	class Emp {
	    String dept;
	    String role;
	    int salary;
	    
	    Emp(String d, String r, int s) {
	    	this.dept = d;
	    	this.role = r;
	    	this.salary = s;
	    }

	    public String toString() {
	        return role + "(" + salary + ")";
	    }
	}
}
