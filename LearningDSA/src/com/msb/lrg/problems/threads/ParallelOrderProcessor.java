package com.msb.lrg.problems.threads;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
 * Problem 3: Parallel Processing of Collections with parallelStream()
 * Problem Explanation
 * 		Given a list of orders (ID + amount), compute:
 * 			Total revenue per customer
 * 			n parallel
 * 			Without concurrency errors
 */
public class ParallelOrderProcessor {

	static class Order{
		String customer;
		double amount;
		
		Order(String customer, double amount){
			this.customer = customer;
			this.amount = amount;
		}
	}
	
	/*
	 * Explanation
	 * 		parallelStream() divides the list into substreams processed in parallel.
	 * 		toConcurrentMap() safely merges partial results.
	 * 		Double::sum merges values atomically per key.
	 * Highlights: Parallel data aggregation with zero explicit threads.
	 */
	public static void main(String[] args) {
		
		List<Order> orders = List.of(
				 				new Order("Alice", 100),
					            new Order("Bob", 150),
					            new Order("Alice", 300),
					            new Order("Bob", 200),
					            new Order("Charlie", 50));

		Map<String, Double> report = orders.parallelStream()
												.collect(Collectors.toConcurrentMap(
															o -> o.customer,
															o -> o.amount,
															Double::sum));
		report.forEach((k,v) -> System.out.println(k + ":" + v));
		
	}

}
