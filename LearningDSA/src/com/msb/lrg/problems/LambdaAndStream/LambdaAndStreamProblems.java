package com.msb.lrg.problems.LambdaAndStream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class LambdaAndStreamProblems {
	
	static class Txn {
	    String region;
	    String type;
	    double amount;

	    Txn(String region, String type, double amount) {
	        this.region = region;
	        this.type = type;
	        this.amount = amount;
	    }
	}
	
	static class Sensor {
	    String id;
	    long timestamp;
	    double value;

	    Sensor(String id, long timestamp, double value) {
	        this.id = id;
	        this.timestamp = timestamp;
	        this.value = value;
	    }

	    public String toString() { return id + " -> " + value + " @ " + timestamp; }
	}
	
	static class Document {
		int id;
		String text;
		
		Document(int id, String text){
			this.id = id;
			this.text = text;
		}
	}

	public static void main(String[] args) {
//		problem1();
//		problem2();
//		problem3();
//		problem4();
		problem5();
	}
	
	static void problem5() {
//		You want to compute the average length of strings,
//		but without using built-in averagingInt() — build your own collector.
		
		List<String> words = Arrays.asList("java", "lambda", "stream", "collector");
		
		// Option 1
		Double avg = words.stream()
							.mapToInt(s -> s.length())
							.average()
							.orElse(0.0);
				
		System.out.println(avg);
		
		//Option 2 Custom collector
		Collector<String, int[], Double> myCollector = 
								Collector.of(
											() -> new int[2], 
											(a,s) -> {a[0]+=s.length(); a[1]++;}, 
											(a,b) -> {a[0]+=b[0];a[1]+=b[1]; return a;}, 
											a -> a[1] == 0 ? 0.0 : (double)a[0]/a[1]);

		avg = words.stream().collect(myCollector);
		System.out.println(avg);
	}
	
	static void problem4() {
//		You have transactions with region, type, and amount.
//		You want:
//		region → (type → totalAmount)
		
		  List<Txn> txns = Arrays.asList(
						            new Txn("Asia", "Retail", 1000),
						            new Txn("Asia", "Corporate", 5000),
						            new Txn("US", "Retail", 7000),
						            new Txn("US", "Corporate", 5000),
						            new Txn("US", "Retail", 3000),
						            new Txn("US", "Corporate", 3000),
						            new Txn("Asia", "Retail", 4000)
						        );

		  txns.stream()
		  		.collect(Collectors.groupingBy(t -> t.region,
		  										Collectors.groupingBy(t -> t.type,
		  															Collectors.summingDouble(t -> t.amount))))
		  		.forEach((k,v) -> System.out.println(k+":"+v));
	}
	
	static void problem3() {
		
//		You receive periodic data updates as (sensorId, timestamp, value).
//		You need the latest record per sensor.
		
        List<Sensor> readings = Arrays.asList(
                						new Sensor("A", 100, 25.3),
                						new Sensor("A", 200, 26.1),
                						new Sensor("B", 150, 19.8),
                						new Sensor("B", 300, 21.0),
                						new Sensor("C", 250, 33.5));
        
//        Option 1 — Sort before grouping
        System.out.println("Option 1 ===");
        readings.stream()
        			.sorted(Comparator.comparingLong((Sensor s) -> s.timestamp).reversed())
        			.collect(Collectors.groupingBy(
        									s -> s.id,
        									LinkedHashMap::new,
        									Collectors.toList()))
        			.forEach((k,v) -> System.out.println(k+":"+v));

//        Option 2 — Pick latest per group (per id)
//        To get the most recent reading per sensor ID, you can use a toMap or groupingBy + reducing approach.
//        Using toMap with merge logic
        
        System.out.println("Option 2 ===");
        readings.stream()
        		.collect(Collectors.toMap(
        								s -> s.id,
        								s -> s,
        								(s1, s2) -> s1.timestamp > s2.timestamp ? s1 : s2))
        		.forEach((k,v) -> System.out.println(k+":"+v));
        
        
//        Option 3 — Using groupingBy + collectingAndThen
        System.out.println("Option 3 ===");
        readings.stream()
        			.collect(Collectors.groupingBy(
        									s -> s.id, 
        									Collectors.collectingAndThen(
        													Collectors.maxBy(Comparator.comparingLong( s -> s.timestamp)),
        													Optional::get)))
        			.forEach((k,v) -> System.out.println(k + ":" + v));
        
	}
	
	static void problem2() {
		
//		You have a list of words and need to group them as anagrams
//		words that have the same letters (e.g., listen, silent, enlist).
		
		List<String> words = Arrays.asList("listen", "silent", "enlist", "rat", "tar", "art", "evil", "vile");
		
		words.stream()
				.collect(Collectors.groupingBy(word -> word.chars()
															.sorted()
															.collect(StringBuilder::new,
																	StringBuilder::appendCodePoint,
																	StringBuilder::append)
															.toString()))
				.forEach((k,v) -> System.out.println(k+":"+v));
		
	}
	
	static void problem1() {

		/*
		 * You’re indexing documents (like a search engine).
		 * Each document has an ID and text content.
		 * You need to build a Map of
		 * 		word → list of document IDs containing that word.
		 */
		List<Document> docs = Arrays.asList(
				new Document(1, "java streams and lambdas"),
				new Document(2, "advanced java programming"),
				new Document(3, "lambdas and functional programming"));

		docs.stream()
				.flatMap(doc -> Arrays.stream(doc.text.split("\\s+"))
										.map(word -> Map.entry(word.toLowerCase(), doc.id)))
				.collect(Collectors.groupingBy(
									Map.Entry::getKey,
									Collectors.mapping(Map.Entry::getValue, Collectors.toSet())))
				.forEach((k,v) -> System.out.println(k + ":" + v));
	}

}
