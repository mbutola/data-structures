package com.msb.lrg.problems;

import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@FunctionalInterface
interface RetryableTask<T> {
	T run() throws Exception;
}

public class JavaCollections {

	
	public static void main(String[] args) {
		mapOptions();
		advanceStreams();
		simpleStreams();
		advanceFuncIntf();
		map();
		arrayList();
		array();
		functionalInterface();
		priorityQueue();
	}

	record Employee(String dept, String role, Integer salary) {};
	record Product(String category, String brand, Integer price){};
	
	/*
	 * “In a Map, how can we add if not exists, or update if it already exists — in one call?”
	 */
	static void mapOptions() {
		// The Old Way (Before Java 8)
		Map<String, Integer> scores = new HashMap<>();
		if(scores.containsKey("Alice")) {
			scores.put("Alice", scores.get("Alice") + 5);
		}else{
			scores.put("Alice", 5);
		}
		
		scores.forEach((k,v) -> System.out.println("1) " + k+":"+v));
		
		// The Modern Way — Using Map.merge() (Best Option)
		scores.merge("Alice", 5, Integer::sum);
		scores.merge("Bob", 5, (oldValue, value) -> oldValue + value);
		
		scores.forEach((k,v) -> System.out.println("2) " + k+":"+v));
		
		// getOrDefault
		
		scores.put("Alice", scores.getOrDefault("Alice", 0) + 5);
		scores.put("Mark", scores.getOrDefault("Mark", 0) + 5);

		scores.forEach((k,v) -> System.out.println("3) " + k+":"+v));
		
		// Counting word frequency:
		
		Map<String, Integer> freq = new HashMap<>();
		for(String fruit : List.of("apple", "banana", "apple")) {
			freq.merge(fruit, 1, Integer::sum);
		}
		freq.forEach((k,v) -> System.out.println("1) " + k+":"+v));

		// Alternative — Using compute()
		// Works same as merge, but gives you more flexibility to compute a custom value.
		
		scores.compute("Brian", (key, oldValue) -> (oldValue == null) ? 5 : oldValue + 5);
		scores.compute("Alice", (key, oldValue) -> (oldValue == null) ? 5 : oldValue + 5);
		scores.forEach((k,v) -> System.out.println("4) " + k+":"+v));
		
		// computeIfAbsent() and computeIfPresent()
		scores.computeIfAbsent("John", k -> 2);
		scores.computeIfPresent("Alice", (k,v) -> v + 5);
		scores.computeIfPresent("Sam", (k,v) -> v + 5);
		scores.forEach((k,v) -> System.out.println("5) " + k+":"+v));
	}
	
	static void advanceStreams() {
		// Grouping and Aggregation
		System.out.println("1) Grouping and Aggregation :: ");
		List<Employee> l1 = List.of(
				new Employee("HR", "HIRE", 4000),
				new Employee("HR", "HIRE",  3000),
				new Employee("IT", "DEV",  8000),
				new Employee("IT", "QA",  6000),
				new Employee("Finance", "TAX",  5000)
				);

		Map<String, Integer> totalSalaryByDept =
			l1.stream()
				.collect(Collectors.groupingBy(
										Employee::dept,
										Collectors.summingInt(Employee::salary)));
		System.out.println(totalSalaryByDept);
		System.out.println("");
		
		// Multi-level Grouping
		System.out.println("2) Multi-level Grouping :: ");
		Map<String, Map<String, List<Employee>>> result =
							l1.stream()
								.collect(
									Collectors.groupingBy(
											Employee::dept,
											Collectors.groupingBy(
													Employee::role)));
		System.out.println(result);
		System.out.println("");


		
		// Partitioning (Boolean Grouping)
		System.out.println("3) Partitioning (Boolean Grouping) :: ");
		List<Integer> l3 = List.of(2,3,4,7,8,9);
		Map<Boolean, List<Integer>> map31 = l3.stream()
								.collect(Collectors.partitioningBy(n -> n%2 == 0));
		System.out.println(map31);
		System.out.println("");
		
		// Custom Collector (advanced reduction)
		System.out.println("4) Custom Collector (advanced reduction) :: ");
		List<String> l4 = List.of("John","Alice","Bob");
		String s41 = l4.stream()
							.collect(Collectors.collectingAndThen(
													Collectors.mapping(String::toUpperCase, 
																Collectors.joining(", ")),
													s -> "[ " + s + " ]"));
		System.out.println(s41);
		System.out.println("");

		// FlatMap (Flatten Nested Data)
		System.out.println("5) FlatMap (Flatten Nested Data) :: ");
		List<List<Integer>> l5 = List.of(
				 					List.of(1,2,3),
				 					List.of(4,5),
				 					List.of(6));
		
		List<Integer> l51 = l5.stream()
								.flatMap(Collection::stream)
								.toList(); 
		System.out.println(l51);
		System.out.println("");

		// Peek for Debugging / Logging
		System.out.println("6) Peek for Debugging / Logging :: ");
		List<String> l61 = Stream.of(" a "," b "," c ")
								.peek(s -> System.out.println("Before trimming: " + s))
								.map(String::trim)
								.peek(s -> System.out.println("After trimming: " + s))
								.toList();
		System.out.println(l61);
		System.out.println("");

		// Parallel Streams
		System.out.println("7) Parallel Streams :: ");
		List<Integer> l7 = IntStream.rangeClosed(1, 100).boxed().toList();
		Integer sum7 = l7.parallelStream()
							.mapToInt(Integer::intValue)
							.sum();
		System.out.println(sum7);
		System.out.println("");

		// Reduce for Custom Aggregation
		System.out.println("8) Reduce for Custom Aggregation :: ");
		List<String> l8 = List.of("apple","banana","pear","strawberry");
		String l81 = l8.stream()
							.reduce("", (a,b) -> a.length() > b.length() ? a : b);
		System.out.println(l81);
		System.out.println("");

		// Infinite / Generated Streams
		System.out.println("9) Infinite / Generated Streams :: ");
		List<Integer> l91 = Stream.iterate(new int[]{0,1},  arr -> new int[]{arr[1], arr[0] + arr[1]})
										.limit(10)
										.map(arr -> arr[0])
										.toList();
		System.out.println(l91);
		System.out.println("");

		// Collect into Custom Data Structure
		System.out.println("10) Collect into Custom Data Structure :: ");
		List<String> l10 = List.of("apple","pear","banana");
		TreeSet<String> l101 = l10.stream()
									.collect(Collectors.toCollection(TreeSet::new));
		System.out.println(l101);
		System.out.println("");

		// Map of Maps (Advanced Collectors)
		System.out.println("11) Map of Maps (Advanced Collectors) :: ");
		List<Product> products = List.of(
					new Product("Electronics", "Sony", 500),
					new Product("Electronics", "Apple", 1000),
					new Product("Clothing", "Nike", 150)
				);
		Map<String, Map<String, Double>> avgPriceByCatAndBrand = 
												products.stream()
															.collect(Collectors.groupingBy(
																			Product::category,
																			Collectors.groupingBy(
																				Product::brand,
																				Collectors.averagingDouble(Product::price)
																			)
																	));
		System.out.println(avgPriceByCatAndBrand);
		System.out.println("");

	}
	
	static void simpleStreams() {
		
		// Print All Elements
		System.out.println("1) Print All Elements :: ");
		List<Integer> l1 = List.of(1,2,3,4,5);
		l1.stream()
			.forEach(System.out::println);
		System.out.println("");
		
		// Filter Elements
		System.out.println("2) Filter Elements :: ");
		List<Integer> l2 = List.of(10,15,20,25);
		l2.stream()
			.filter(n -> n%2 == 0)
			.forEach(System.out::println);
		System.out.println("");
		
		// Map (Transform Data)
		System.out.println("3) Map (Transform Data) :: ");
		List<String> l3 = List.of("a","b","c");
		List<String> l31 = l3.stream()
								.map(String::toUpperCase)
								.toList();
		System.out.println(l31);
		System.out.println("");
		
		// Sort Stream
		System.out.println("4) Sort Stream :: ");
		List<Integer> l4 = List.of(3,2,1,4);
		List<Integer> l41 = l4.stream()
								.sorted()
								.toList();
		System.out.println(l41);
		System.out.println("");
		
		// Collect to List / Set / Map
		System.out.println("5) Collect to List / Set / Map :: ");
		List<String> l5 = List.of("A","B","A");
		Set<String> l51 = l5.stream()
							.collect(Collectors.toSet());
		System.out.println(l51);
		System.out.println("");
		
		// Count / Match Operations
		System.out.println("6) Count / Match Operations :: ");
		List<Integer> l6 = List.of(10,15,20,25);
		long count = l6.stream()
						.filter(n -> n > 15)
						.count();
		System.out.println(count);
		System.out.println("");
		
		// Reduce (Sum / Aggregate)
		System.out.println("7) Reduce (Sum / Aggregate) :: ");
		List<Integer> l7 = List.of(1,2,3,4,5);
		long sum = l7.stream()
						.reduce(0, Integer::sum);
		System.out.println(sum);
		System.out.println("");
		
		// Find First / Any
		System.out.println("8) Find First / Any :: ");
		List<String> l8 = List.of("Tom","Bob","Rita");
		l8.stream()
			.filter(s-> s.startsWith("B"))
			.findFirst()
			.ifPresent(System.out::print);
		System.out.println("");
		
		// Distinct + Limit + Skip
		System.out.println("9) Distinct + Limit + Skip :: ");
		List<Integer> l9 = List.of(1,2,2,3,4,5,6);
		l9.stream()
			.distinct()
			.skip(1)
			.limit(3)
			.forEach(System.out::println);
		System.out.println("");
		
		// Stream from Array
		System.out.println("10) Stream from Array :: ");
		int[] arr = {1,2,3};
		Arrays.stream(arr)
				.map(n -> n*n)
				.forEach(System.out::println);
		System.out.println("");
	}
	
	static <T, R> R execute(T input , Function<T, R> operation) {
		return operation.apply(input);
	}
	
	static void processData(String data, Consumer<String> callback) {
		System.out.println("\tProcessing data...");
		callback.accept("\tDone Processing ...");
	}
	
	static <T> T execute(RetryableTask<T> task, int retries){
		for(int i=0; i<retries; i++) {
			try {
				return task.run();
			}catch(Exception e) {
				System.out.println("Retry ::" + (i+1));
			}
		}
		throw new RuntimeException("All retries failed...");
	}
	
	static void advanceFuncIntf() {
		Function<String, String> toUpper = String::toUpperCase;

		// Custom Functional Interfaces — Domain-Specific Behavior
		// Example: Retryable Operation
		System.out.println("8) Custom Functional Interfaces :: ");
		String result;
		try {
			result = execute(() -> {
				if(Math.random() < 0.7)
					throw new RuntimeException("Failed");
				return "Success";
			}, 3);
		} catch (Exception e) {
			result = e.getMessage();
		}
		System.out.println("Result = " + result);
		
		
		// Functional Interfaces with Streams
		// Use Case: Complex stream pipelines with inline logic
		System.out.println("7) Functional Interfaces with Stream :: ");
		Predicate<String> longName = str -> str.length() > 3;
//		Function<String, String> toUpper = String::toUpperCase;
		Consumer<String> printer = n -> System.out.print(n + " ");
		
		System.out.print("\t");
		List<String> names = List.of("bob","alice","tom","rita");
		names.stream()
			.map(toUpper)
			.filter(longName)
			.forEach(printer);
		System.out.println("");
		
		// Currying / Partial Application
		// Use Case: Pre-configure part of a function
		System.out.println("6) Currying :: ");
		Function<Integer, Function<Integer, Integer>> adder = a -> (b -> a + b);
		System.out.print("\t 5 + 10 = ");
		 Function<Integer, Integer> add5 = adder.apply(5);
		System.out.println(add5.apply(10));	
		
		
		// Callback / Event Handling with Consumer
		// Use Case: Execute code after a task completes
		System.out.println("5) Callback / Event Handling :: ");
		processData("File.txt", System.out::println);
		 
		// Lazy Evaluation with Supplier
		// Use Case: Delay heavy computation until needed
		
		Supplier<String> heavyTask = () -> {
			System.out.println("\tPerforming heavy task...");
			return "\tResult ready";
		};
		
		System.out.println("4) Lazy Evaluation :: \n\tBefore call");
		System.out.println(heavyTask.get());
		
		
		// Higher-Order Functions — Pass Behavior as Parameter
		// Use Case: Pass logic to a reusable method
		
		System.out.println("3) Higher-Order Functions :: ");
		System.out.print("\t");
		System.out.print(execute(5, n -> n*n));
		System.out.print(" ");
		System.out.print(execute("java", String::toUpperCase));
		System.out.println("");
		
		// Predicate Combination — Complex Filtering Logic
		// Use Case: Dynamically filter data
		
		Predicate<Integer> even = n -> n%2 == 0;
		Predicate<Integer> gt15 = n -> n>15;
		
		List<Integer> nums = List.of(10,15,20,25,30);
		System.out.println("2) Predicate Combination :: ");
		nums.stream()
			.filter(even.and(gt15))
			.forEach(n -> System.out.print("\t" + n + " "));
		System.out.println("");
		
		// Functional Composition — Dynamic Behavior Building
		// Use Case: Chain multiple transformations dynamically
		
		Function<String, String> trim = String::trim;
//		Function<String, String> toUpper = String::toUpperCase;
		Function<String, String> addPrefix = s -> "User: " +s;
		
		System.out.println("1) Functional Composition :: ");
		Function<String, String> pipeline = trim.andThen(toUpper).andThen(addPrefix);
		System.out.print("\t");
		System.out.println(pipeline.apply("  alice  "));
		
	}
	
	static void map() {
		Map<Integer, String> m1 = new HashMap<>();
		m1.put(0, "zero");
		m1.put(1, "one");
		System.out.println("HashMap :: " + m1.toString());

		Map<Integer, String> m2 = Map.of(1, "one", 2, "two");
		System.out.println("Map.of :: " + m2.toString());
	
		Map<Integer, String> m3 = Map.ofEntries(Map.entry(2,"two"), Map.entry(3,"three"));
		System.out.println("Map.ofEntries :: " + m3.toString());
	
		Map<Integer, String> m4 = new HashMap<>() {{
			put(3, "three");
			put(4, "four");
		}};
		System.out.println("Double Brace Initialization :: " + m4.toString());
	
		Map<Integer, String> m5 = IntStream.rangeClosed(0, 2)
									.boxed()
									.collect(Collectors.toMap(i -> i, i -> "val"+i));
		System.out.println("Stream :: " + m5.toString());
	
		Map<Integer, String> m6 = new LinkedHashMap<>();
		m6.put(5, "five");
		m6.put(6, "six");
		System.out.println("LinkedHashMap" + m6.toString());
	
		Map<Integer, String> m7 = new TreeMap<>();
		m7.put(7, "seven");
		m7.put(6, "six");
		System.out.println("TreeMap :: " + m7.toString());
	
	}
	
	static void arrayList() {
		List<Integer> l1 = new ArrayList<>();
		l1.add(0);
		l1.add(0);
		l1.add(7);
		System.out.println("ArrayList (mutable) :: " + l1.toString());
		
		List<Integer> l2 = Arrays.asList(0,0,7);
		System.out.println("Arrays.asList() :: " + l2.toString());
		
		List<String> l3 = List.of("A","B","C");
		System.out.println("List.of() :: " + l3.toString());
		
		List<Integer> l4 = IntStream.rangeClosed(0, 3)
								.boxed()
								.collect(Collectors.toList());
		System.out.println("Streams :: " + l4.toString());
		
		Integer[] arr = {1,2,3};
		List<Integer> l5 = new ArrayList<>(Arrays.asList(arr));
		System.out.println("Arrays :: " + l5.toString());
		
		List<Integer> l6 = new ArrayList<>(Collections.nCopies(3, 0));
		System.out.println("Collections.nCopies() :: " + l6.toString());
		
	}
	
	static void array() {
		int[] arr1 = new int[3];
		arr1[0] = 0;
		arr1[1] = 0;
		arr1[2] = 7;
		System.out.println("Fixed size + manual population :: " + Arrays.toString(arr1));
		
		int[] arr2 = {0,0,7};
		System.out.println("Direct initialization :: " + Arrays.toString(arr2));
		
		int[] arr3 = new int[3];
		Arrays.fill(arr3, 7);
		System.out.println("Arrays.fill() :: " + Arrays.toString(arr3));
		
		int[] arr4 = new int[3];
		Arrays.setAll(arr4, i -> i*2);
		System.out.println("Arrays.setAll() :: " + Arrays.toString(arr4));
		
		int[] arr5 = {0,0,7};
		System.out.print("Using Streams :: " + Arrays.toString(IntStream.range(0, arr5.length).toArray()));
		
		
	}
	
	
	static void functionalInterface() {
		Consumer<String> c = data -> System.out.println("Consumer :: " + data);
		c.accept("Manmohan");
				
		Supplier<Integer> s = () -> 100;
		System.out.println("Supplier :: " + s.get());
		
		Function<Integer,String> f = data -> "Manmohan 00" + data;
		System.out.println("Function :: " + f.apply(7));
		
		Predicate<Integer> isEven = data -> data%2 == 0;
		System.out.println("Predicate :: " + isEven.test(4));
		
		BiFunction<String, Integer,String> combine = (data, in) -> data + " 00" + in;
		System.out.println("BiFunction :: " + combine.apply("Manmohan", 7));
		
		BiConsumer<String, Integer> biConsume = (data,in) -> System.out.println("BiConsumer :: " + data + " 00" + in);
		biConsume.accept("Manmohan",7);
		
		UnaryOperator<Integer> square = x -> x * x;
		System.out.println("UnaryOperator :: " + square.apply(3));
		
		BinaryOperator<Integer> max = (a,b) -> a > b ? a : b;
		System.out.println("BinaryOperator :: " + max.apply(20,10));
		
	}
	
	static void priorityQueue() {
		PriorityQueue<Integer> minPq = new PriorityQueue<>();
		addElements(minPq);
		printElements(minPq);
		System.out.print("Min Order :: ");
		printOrderedElements(minPq);

		System.out.println("");
		
		PriorityQueue<Integer> maxPq = new PriorityQueue<>(Comparator.reverseOrder());
		addElements(maxPq);
		printElements(maxPq);
		System.out.print("Max Order :: ");
		printOrderedElements(maxPq);
	}
	
	static void addElements(PriorityQueue<Integer> pq) {
		pq.offer(10);
		pq.offer(30);
		pq.offer(20);
	}
	
	static void printOrderedElements(PriorityQueue<Integer> pq) {
		while(!pq.isEmpty()) {
			System.out.print(pq.poll() + " ");
		}
	}
	
	static void printElements(PriorityQueue<Integer> pq) {
		System.out.print ("Using Array :: ");
		Object[] arr = pq.toArray();
		for(Object i : arr) {
			System.out.print(i + " ");
		}
		System.out.println("");
		
		System.out.print ("Using iterator :: ");
		Iterator<Integer> itr = pq.iterator();
		while(itr.hasNext()) {
			System.out.print(itr.next() + " ");
		}
		System.out.println("");
		
		System.out.print ("Using iterator with lambda :: ");
		Iterator<Integer> itr2 = pq.iterator();
		itr2.forEachRemaining(e -> System.out.print(e + " "));
		System.out.println("");
		
		System.out.print ("Using Enhanced for loop :: ");
		for(Integer i : pq) {
			System.out.print(i + " ");
		}
		System.out.println("");
		
		System.out.print ("Using forEach :: ");
		pq.forEach(e -> System.out.print(e + " "));
		System.out.println("");

		System.out.print("Using Stream :: ");
		pq.stream()
			.forEach(e -> System.out.print(e + " "));
		System.out.println("");
	}

	static void mapToList() {
		Map<String, Integer> map = new HashMap<>();
		map.put("a", 1);
		map.put("b", 2);
		map.put("c", 3);
		
		/*
		 * MOST COMMON & INTERVIEW-SAFE WAY
			Using entrySet() + ArrayList
			Line-by-line explanation
				map.entrySet()
					Returns a Set of key-value pairs
					Type: Set<Map.Entry<String, Integer>>
					Each element is a live view of the map
				new ArrayList<>(...)
					Converts the Set into a modifiable list
					Now you can:
						Sort
						Reorder
						Iterate by index
				This is the most used method in interviews.
		 */
		List<Map.Entry<String, Integer>> list =
		        new ArrayList<>(map.entrySet());

		/*
		 	Using Java 8 Streams (Modern & Clean)
			Explanation
				map.entrySet()
					Gets all key-value pairs
				.stream()
					Creates a sequential stream
				.collect(Collectors.toList())
					Collects elements into a List
					Returned list may not be ArrayList explicitly
		*/
		List<Map.Entry<String, Integer>> list1 =
		        map.entrySet()
		           .stream()
		           .collect(Collectors.toList());


		/*
			 3. ️Using Streams with Explicit ArrayList Why this exists Guarantees the list
			 is an ArrayList Useful when performance or mutability matters
		 */		
		List<Map.Entry<String, Integer>> list2 =
		        map.entrySet()
		           .stream()
		           .collect(Collectors.toCollection(ArrayList::new));

	}
	
}
