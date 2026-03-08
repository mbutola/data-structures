package com.msb.lrg.problems.threads;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/*
 * Problem 1: Counting Word Frequency from Multiple Files (ConcurrentHashMap)
 * Problem Explanation
 * 		You have N text files. Each thread reads one file and counts word frequencies.
 * 		All threads must update a shared global map of word counts safely — without data loss or corruption.
 * You must:
 * 		Use multiple threads for parallelism
 * 		Safely update the shared map
 * 		Merge results efficiently
 */
public class WordCountConcurrent {

	/*
	 * Explanation
	 * 		ConcurrentHashMap allows thread-safe updates without locking the entire map.
	 * 		merge(key, value, remappingFunction) handles atomic updates.
	 * 		Each thread processes a separate file in parallel.
	 * 		Sorting and limiting gives top 10 words at the end.
	 * Highlights: Parallel I/O + concurrency-safe aggregation + Stream sorting.
	 */
	public static void main(String[] args) throws Exception{
		
		List<Path> files = Arrays.asList(
									Paths.get("resources\\file1.txt"),
									Paths.get("resources\\file2.txt"),
									Paths.get("resources\\file3.txt"));
		
		ConcurrentHashMap<String, Long> wordCounts = new ConcurrentHashMap<>();
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		for(Path file : files) {
			executor.submit(() -> {
				try (Stream<String> lines = Files.lines(file)) {
					lines.flatMap(line -> Arrays.stream(line.split("\\W+")))
							.filter(word -> !word.isBlank())
							.forEach(word -> wordCounts.merge(word.toLowerCase(), 1L, Long::sum));
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}
		
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.SECONDS);
		
		wordCounts.entrySet()
					.stream()
					.sorted(Map.Entry.comparingByValue())
					.forEach(e -> System.out.println(e.getKey() + ":" + e.getValue()));
	}
}
