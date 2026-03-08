package com.msb.lrg.problems.LambdaAndStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PracticeLambdaAndStream {

	static class Sale {
	    String region; int year; double amount;
	    Sale(String r, int y, double a) { region = r; year = y; amount = a; }
	}

    record Employee(String name, String dept, double salary){};
    // record Sale(String region, int year, double amount){};

    
    public static void main(String[] args) throws Exception {
         printEvenNumbers();
         printSquareOfNumbers();
         printStringStartingWithA();
         printMaxAndMin();
         printNamesWithEachLetter();
         printAvgSalOfEmp();
         printJoinNamesWithComma();
         printPartitionBySalary();
         printFlatMap();
         scenario1();
         scenario2();
         scenario3();
         scenario4();
         scenario5();
         scenario6();
         scenario7();
         scenario8();
         scenario10();
         scenario11();
         scenario12();

    }

    /*
     * Stream operations return an Optional when the result might not exist 
     *      especially when reducing or finding a single element.
     * If you provide a default or identity value, the result will never be empty.
     */
    static void scenario12(){
        Optional<String> option = Optional.empty();

        // isPresent()
        if(option.isPresent()){
            System.out.println(option.get());
        }else{
            System.out.println("not present ...");
        }

        // isEmpty()
        if(!option.isEmpty()){
            System.out.println(option.get());
        }else{
            System.out.println("not present ...");
        }

        //ifPresent and orElse and ifPresenrOrElse
        option.ifPresent(System.out::println);         // Run if exists
        String value = option.orElse("default");       // Use default
        System.out.println("value :: " + value);
        option.ifPresentOrElse(val -> System.out.println(val),
                                () -> System.out.println("not present ..."));
        
        //orElseGet and orElseThrow
        String value2 = option.orElseGet(() -> "lazy"); // Compute default
        System.out.println("value2 :: " + value2);
        // option.orElseThrow(() -> new RuntimeException("Missing!"));
    }

    static void scenario11(){
        // Given a list of names, produce a single comma-separated string in sorted order —
        // but without using Collectors.joining() (use reduce manually).
        
        List<String> names = Arrays.asList("John", "Alice", "Bob", "David");

        String res = names.stream()
                            .sorted()
                            .reduce((s1,s2) -> s1 + ", " + s2)
                            .orElse("");
        
        System.out.println(res);
    }

    static void scenario10(){
        // Given a list of names, produce a single comma-separated string in sorted order —
        // but without using Collectors.joining() (use reduce manually).
    }

    static void scenario9(){
        // Given a list of integers, find which numbers are duplicated.

        List<Integer> list = Arrays.asList(1,2,3,4,5,2,3,6,4,4);

        Set<Integer> res = list.stream()
                                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                                .entrySet()
                                .stream()
                                .filter(e -> e.getValue() > 1)
                                .map(e -> e.getKey())
                                .collect(Collectors.toSet());
        System.out.println(res);

        for(int i : list){

        }
    } 

    static void scenario8(){
        // You want to sort employees first by department (alphabetically),
        // then by salary (descending).
        List<Employee> list = Arrays.asList(
                                        new Employee("Alice", "IT", 90000),
                                        new Employee("Bob", "HR", 50000),
                                        new Employee("Charlie", "IT", 95000),
                                        new Employee("David", "Finance", 75000));

        list.stream()
                .sorted((e1, e2) -> {
                                        int cmp = e1.dept.compareTo(e2.dept);
                                        if(cmp != 0) return cmp;
                                        return Double.compare(e2.salary,e1.salary);
                        })
                .forEach(System.out::println);

        list.stream()
                .sorted(Comparator.comparing((Employee e) -> e.dept)
                                    .thenComparing(Comparator.comparingDouble((Employee e) -> e.salary).reversed()))
                .forEach(System.out::println);

    }

    static void scenario7(){
        // You have a list of lists of integers (e.g., scores from multiple branches).
        // You want the total sum of all numbers.
        // List<List<Integer>> data1 = List.of(
        //                             List.of(10, 20, 30),
        //                             List.of(5, 15),
        //                             List.of(25, 35, 50));

        List<List<Integer>> data = Arrays.asList(
                                    Arrays.asList(10, 20, 30),
                                    Arrays.asList(5, 15),
                                    Arrays.asList(25, 35, 45));

        int sum = data.stream()
                    .flatMap(List::stream)
                    .mapToInt(Integer::intValue)
                    .sum();
        System.out.println(sum);
    }

    static void scenario6(){
        // You have a list of employees, each with a department and salary.
        // You want to find the employee with the highest salary per department.

        List<Employee> list = List.of(new Employee("Alice", "HR", 50000),
                                        new Employee("Bob", "IT", 80000),
                                        new Employee("Charlie", "IT", 95000),
                                        new Employee("David", "HR", 60000),
                                        new Employee("Eve", "Finance", 70000));

         Map<String, Optional<Employee>> report = list.stream()
                                .collect(Collectors.groupingBy(Employee::dept,
                                		Collectors.maxBy(Comparator.comparing(Employee::salary))
                                        ));                            
        report.forEach((k,v) -> System.out.println(k + ":" + v.get().name));
}


    static void scenario5(){
        // You have sales transactions with region, year, and amount.
        // You need a nested summary — total sales grouped by region → year.
        List<Sale> sales = List.of(new Sale("East", 2023, 1000),
                                    new Sale("East", 2023, 500),
                                    new Sale("East", 2024, 1500),
                                    new Sale("East", 2024, 1000),
                                    new Sale("West", 2023, 2000),
                                    new Sale("West", 2024, 3000));
    
        Map<String, Map<Integer, Double>> report = sales.stream()
                                    .collect(Collectors.groupingBy(s -> s.region,
                                                                Collectors.groupingBy(s -> s.year, 
                                                                                    Collectors.summingDouble(s -> s.amount))));
       System.out.println(report);
    }

    static void scenario4(){
        // Each employee has multiple skills.
        // Find the set of all distinct skills across the company.

        Map<String, List<String>> skills = Map.of(
                                                "Alice", List.of("Java", "Python"),
                                                "Bob", List.of("Python", "AWS"),
                                                "Carol", List.of("Docker", "Java"));
        Set<String> skill = skills.values().stream()
                                                .flatMap(List::stream)
                                                .collect(Collectors.toSet()); 
        System.out.println(skill.toString());
    }

    static void scenario3(){
        // From a list of employees, you need the top 3 by salary.
        // Sorting entire list manually + picking first 3 is tedious in loops.
        List<Employee> list = Arrays.asList(
                                         new Employee("Alice", "", 70000),
                                         new Employee("Bob", "", 90000),
                                         new Employee("Carol", "", 120000),
                                         new Employee("Dan", "", 60000),
                                         new Employee("Eve", "", 110000));

        list.stream()
               .sorted((e1, e2) -> Double.compare(e2.salary, e1.salary))
               .limit(3)
               .forEach(e -> System.out.println(e.name));
    }

    static void scenario2(){
        // Given a list of employees with department and salary,
        // you need to find average salary per department — like SQL GROUP BY.

        List<Employee> list = List.of(
                                        new Employee("Alice", "IT", 70000),
                                        new Employee("Bob", "IT", 90000),
                                        new Employee("Carol", "HR", 50000),
                                        new Employee("Dan", "HR", 60000)
                                    );
        Map<String, Double> result = list.stream()
                                            .collect(Collectors.groupingBy(
                                                                e -> e.dept, 
                                                                Collectors.averagingDouble(e -> e.salary)));

        result.forEach((k,v) -> System.out.println(k + ":" + v));
    }

    static void scenario1(){
        // You have a list of employee names.
        // You want to:
        //     Pick names that start with A
        //     Convert them to uppercase
        //     Sort alphabetically
        //     Return as a list

        List<String> list = List.of("Alice", "Bob", "Andrew", "Alex", "Tom");

        List<String> results = new ArrayList<>();
        for(String name : list){
            if(name.startsWith("A")){
                results.add(name.toUpperCase());
            }
        }
        Collections.sort(results);
        System.out.println(results.toString());

        List<String> results1 = list.stream()
                                        .filter(s -> s.startsWith("A"))
                                        .map(String::toUpperCase)
                                        .sorted()
                                        .collect(Collectors.toList());
        System.out.println(results1.toString());

    }

    static void printFlatMap() {
        List<List<Integer>> list = List.of(
                                            List.of(1,2),
                                            List.of(3,4),
                                            List.of(5,6)
                                        );
        list.stream()
                .flatMap(List::stream)
                .forEach(System.out::println);
    }

    static void printPartitionBySalary(){
        List<Employee> list = List.of(
                                    new Employee("A", "", 50000),
                                    new Employee("B", "",  70000),
                                    new Employee("C", "",  60000)); 
        Map<Boolean, List<Employee>> result = list.stream()
                                                .collect(Collectors.partitioningBy(e -> e.salary > 50000));
        
        result.forEach((k,v) -> System.out.println(k + " -> " + v.stream().map(e -> e.name).toList()));     
    }

    static void printJoinNamesWithComma() {
        List<String> names = List.of("Alice","Bob","Charlie");
        String result = names.stream()
                            .collect(Collectors.joining(", "));
        System.out.println(result);
    }

    
    static void printAvgSalOfEmp(){
        List<Employee> list = List.of(
                                    new Employee("A",  "", 50000),
                                    new Employee("B", "",  70000),
                                    new Employee("C", "",  60000)); 

        Double avg = list.stream()
                            .mapToDouble(e -> e.salary)
                            .average()
                            .orElse(0);
        System.out.println(avg);
    }

    static void printNamesWithEachLetter(){
        List<String> list = List.of("Alice","Adam", "Bob", "Brian", "Bella");
        Map<Character, Long> names = list.stream()
                                        .collect(Collectors.groupingBy(str -> str.charAt(0), 
                                                                        Collectors.counting()));
        names.forEach((k,v) -> System.out.println(k + ":" + v));
    }

    static void printMaxAndMin(){
        List<Integer> list = List.of(10,20,5,8,90);
        System.out.println(list.stream()
            .max(Integer::compareTo)
            .get());
    }

    static void printStringStartingWithA() {
        List<String> list =  List.of("Alice", "Bob", "Ankit", "David");
        list.stream()
            .filter(str -> str.startsWith("A"))
            .forEach(System.out::println);
    }
    static void printSquareOfNumbers(){
        List<Integer> list = List.of(1,2,3,4,5);
        list.stream()
            .map(x-> x*x)
            .forEach(System.out::println);
    }

    static void printEvenNumbers(){
        List<Integer> list = List.of(1,2,3,4,5,6,7,8);
        list.stream()
            .filter(x -> x%2 == 0)
            .forEach(System.out::println);
    }
	
	
}
