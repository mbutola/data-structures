package com.msb.lrg.problems.design;

import java.util.concurrent.ConcurrentHashMap;

public class L1UrlShortenerServiceDesign {

	static class UrlShortenerService {
	    private final UrlRepository repository;
	    private final IdGenerator idGenerator;
	
	    public UrlShortenerService(UrlRepository repository, IdGenerator idGenerator) {
	        this.repository = repository;
	        this.idGenerator = idGenerator;
	    }
	
	    public String shortenUrl(String longUrl) {
	
	        // 🔹 Basic validation
	        if (longUrl == null || longUrl.isEmpty()) {
	            throw new IllegalArgumentException("Invalid URL");
	        }
	
	        // 🔹 Check if already exists
	        String existing = repository.findShortCode(longUrl);
	        if (existing != null) {
	            return existing;
	        }
	
	        // 🔹 Generate unique ID
	        long id = idGenerator.nextId();
	
	        // 🔹 Convert to Base62
	        String shortCode = Base62Encoder.encode(id);
	
	        // 🔹 Store mapping
	        repository.save(shortCode, longUrl);
	
	        return shortCode;
	    }
	
	    public String getOriginalUrl(String shortCode) {
	        String url = repository.findLongUrl(shortCode);
	
	        if (url == null) {
	            throw new RuntimeException("URL not found");
	        }
	
	        return url;
	    }
	}

    static class Base62Encoder {

        private static final String BASE62 = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        public static String encode(long num) {
            StringBuilder sb = new StringBuilder();

            while (num > 0) {
                sb.append(BASE62.charAt((int) (num % 62)));
                num /= 62;
            }

            return sb.reverse().toString();
        }
    }
    
    static class IdGenerator {

        private long lastTimestamp = -1L;
        private long sequence = 0L;

        private final long nodeId;
        private static final long MAX_SEQUENCE = (1 << 12) - 1;

        public IdGenerator(long nodeId) {
            this.nodeId = nodeId;
        }

        public synchronized long nextId() {
            long current = System.currentTimeMillis();

            if (current == lastTimestamp) {
                sequence = (sequence + 1) & MAX_SEQUENCE;
                if (sequence == 0) {
                    while (current <= lastTimestamp) {
                        current = System.currentTimeMillis();
                    }
                }
            } else {
                sequence = 0;
            }

            lastTimestamp = current;

            return (current << 22) | (nodeId << 12) | sequence;
        }
    }

    static class UrlRepository {

        private final ConcurrentHashMap<String, String> shortToLong = new ConcurrentHashMap<>();
        private final ConcurrentHashMap<String, String> longToShort = new ConcurrentHashMap<>();

        public void save(String shortCode, String longUrl) {
            shortToLong.put(shortCode, longUrl);
            longToShort.put(longUrl, shortCode);
        }

        public String findLongUrl(String shortCode) {
            return shortToLong.get(shortCode);
        }

        public String findShortCode(String longUrl) {
            return longToShort.get(longUrl);
        }
    }
    
    public static void main(String[] args) {

        UrlRepository repository = new UrlRepository();
        IdGenerator idGenerator = new IdGenerator(1);

        UrlShortenerService service = new UrlShortenerService(repository, idGenerator);

        String longUrl = "https://www.google.com/search?q=system+design";

        String shortCode = service.shortenUrl(longUrl);
        System.out.println("Short URL: " + shortCode);

        String original = service.getOriginalUrl(shortCode);
        System.out.println("Original URL: " + original);
    }
}