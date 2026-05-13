package com.msb.lrg.problems.design.practice;

import java.util.HashMap;
import java.util.Map;

public class UrlShortenerService {
	URLRepository urlRepository;
	IdGenerator idGenerator;
	
	UrlShortenerService(URLRepository urlRepository, IdGenerator idGenerator){
		
	}
	
	public String ShortenUIrl(String longUrl) {
		
		/*
		 * Illelegal argument
		 * if exists in URLRepo return
		 * IdGenerator to generate id 
		 * Call Base62 encode to encode and pass id
		 * save short url
		 * return shoirt url
		 */
		
		return null;
	}
	
    public String getOriginalUrl(String shortCode) {
    	/*
    	 * return from Url Repo or error.
    	 */
    	return null;
    }
    
	static class IdGenerator {
		public synchronized long nextId() {
			return 0;
		}
	}
	
	static class Base62Encoder {
		public static String encode(long num) {
			/*
			 * create base62 string
			 * loop 
			 * 	take remainder nd index into String
			 * 	Divide by 62 until zero
			 */
			return null;
		}
	}
	
	static class URLRepository {
		Map<String, String> longToShort = new HashMap<String, String>();
		Map<String, String> shortTolong = new HashMap<String, String>();
		
		public void save(String shortCode, String longUrl) {
			
		}
		
		public String findShortCode(String longUrl){
			return null;
		}
		
		public String findLongUrl(String shortCode){
			return null;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
