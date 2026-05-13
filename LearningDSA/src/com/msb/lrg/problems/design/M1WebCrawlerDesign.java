package com.msb.lrg.problems.design;

import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class M1WebCrawlerDesign {
	
	static class WebCrawler {

	    // Thread-safe structures
	    private final Set<String> visited = ConcurrentHashMap.newKeySet();
	    private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();
	    private final AtomicBoolean running = new AtomicBoolean(true);
	
	    private final ExecutorService executor;
	
	    private static final int MAX_THREADS = 5;
	
	    public WebCrawler() {
	        executor = Executors.newFixedThreadPool(MAX_THREADS);
	    }
	
	    // 🔥 Start crawling
	    public void startCrawling(List<String> seeds) {
	        queue.addAll(seeds);;
	        visited.addAll(seeds);;
	
	        for (int i = 0; i < MAX_THREADS; i++) {
	            executor.submit(this::crawl);
	        }
	    }
	
	    // 🔹 Worker method
	    private void crawl() {
	        while (running.get()) {
	            try {
	//                String url = queue.take(); // blocking
	
	                String url = queue.poll(2, TimeUnit.SECONDS);
	
	                if (url == null) continue;
	
	                System.out.println("Crawling: " + url);
	
	                String content = fetchContent(url);
	
	                Set<String> links = extractLinks(content);
	
	                for (String link : links) {
	                    if (visited.add(link)) { // atomic check + add
	                        queue.offer(link);
	                    }
	                }
	
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }
	
	    // 🔹 Fetch page content
	    private String fetchContent(String urlStr) {
	        // In real system → HTTP call
	        return "<a href='http://example.com/page1'></a>";
	
	//        StringBuilder content = new StringBuilder();
	//
	//        try {
	//            URL url = new URL(urlStr);
	//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	//
	//            conn.setRequestMethod("GET");
	//            conn.setConnectTimeout(5000);
	//
	//            BufferedReader reader = new BufferedReader(
	//                    new InputStreamReader(conn.getInputStream())
	//            );
	//
	//            String line;
	//            while ((line = reader.readLine()) != null) {
	//                content.append(line);
	//            }
	//
	//            reader.close();
	//
	//        } catch (Exception e) {
	//            // ignore failures
	//        }
	//
	//        return content.toString();
	    }
	
	    // 🔹 Extract links using regex
	    private Set<String> extractLinks(String html) {
	        // Dummy links (simulate discovery)
	        return Set.of(
	                "http://example.com/page1",
	                "http://example.com/page2"
	        );
	//        Set<String> links = ConcurrentHashMap.newKeySet();
	//
	//        Pattern pattern = Pattern.compile("href=\"(http[s]?://[^\"]+)\"");
	//        Matcher matcher = pattern.matcher(html);
	//
	//        while (matcher.find()) {
	//            links.add(matcher.group(1));
	//        }
	//
	//        return links;
	    }
	}

    // 🧪 MAIN METHOD
    public static void main(String[] args) {

        List<String> seeds = List.of(
                "http://example.com",
                "http://example.org"
        );

        WebCrawler crawler = new WebCrawler();

        crawler.startCrawling(seeds);

        // Let it run for some time
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        crawler.running.set(false);
        crawler.executor.shutdown();
        
        System.out.println("Crawling finished.");
    }
}