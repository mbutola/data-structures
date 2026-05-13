package com.msb.lrg.problems.hackerrank;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/*

| Use Case  | Before Java 8      | Java 8+ (Recommended)                         |
| --------- | ------------------ | --------------------------------------------- |
| Date/Time | `Date`, `Calendar` | `LocalDate`, `LocalDateTime`, `ZonedDateTime` |
| Format    | `SimpleDateFormat` | `DateTimeFormatter`                           |

 */
public class H0002Date {

	public static void main(String[] args) throws Exception {
		java8();
		java8Plus();
		difference();
	}
	
	public static void difference() throws Exception{

//		Difference in Days
//		Using ChronoUnit
		LocalDate d1 = LocalDate.of(2026, 4, 1);
		LocalDate d2 = LocalDate.of(2026, 4, 18);
		
		long days = ChronoUnit.DAYS.between(d1, d2);
		
		System.out.println(days);  // 17

//		Difference in Time (Hours, Minutes, Seconds)
//		✅ Using LocalDateTime

		LocalDateTime t1 = LocalDateTime.of(2026, 4, 18, 10, 0);
		LocalDateTime t2 = LocalDateTime.of(2026, 4, 18, 12, 30);

		long hours = ChronoUnit.HOURS.between(t1, t2);
		long minutes = ChronoUnit.MINUTES.between(t1, t2);

		System.out.println(hours);   // 2
		System.out.println(minutes); // 150	

//		3. Full Difference (Days + Hours + Minutes)
//		✅ Using Duration

		LocalDateTime t11 = LocalDateTime.of(2026, 4, 18, 10, 0);
		LocalDateTime t12 = LocalDateTime.of(2026, 4, 20, 12, 30);

		Duration duration = Duration.between(t11, t12);

		long days1 = duration.toDays();
		long hours1 = duration.toHours();
		long minutes1 = duration.toMinutes();

		System.out.println(days1);    // 2
		System.out.println(hours1);   // 50
		System.out.println(minutes1); // 3030	
		
		long days11 = duration.toDays();
		long hours11 = duration.toHours() % 24;
		long minutes11 = duration.toMinutes() % 60;

		System.out.println(days11 + " days " + hours11 + " hours " + minutes11 + " minutes");
		
//		5. Using Period (Date Only)

		LocalDate d21 = LocalDate.of(2026, 4, 1);
		LocalDate d22 = LocalDate.of(2026, 6, 18);

		Period p = Period.between(d21, d22);

		System.out.println(p.getMonths()); // 2
		System.out.println(p.getDays());   // 17
	}
	
	public static void java8() throws Exception{
		Date date = new Date();  // current date & time
//		System.out.println("Java8 :: " + date);
		Calendar cal = Calendar.getInstance();
//		System.out.println("Java8 :: " + cal);

//		Parse String → Date
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = sdf.parse("17/04/2026");
//		System.out.println("Java8 :: " + date1);
		
//		 Date Formatting
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formatted = sdf2.format(new Date());
//		System.out.println("Java8 :: " + formatted);
		
//		Date Manipulations (Add/Subtract)
		Calendar cal2 = Calendar.getInstance();
//		System.out.println("Java8 :: " + cal2);
		cal2.add(Calendar.DAY_OF_MONTH, 5);
		cal2.add(Calendar.MONTH, -2);
		cal2.add(Calendar.YEAR, 1);
		Date date2 = cal2.getTime();
//		System.out.println("Java8 :: " + date2);
		
//		Time Manipulations
		Calendar cal3 = Calendar.getInstance();
//		System.out.println("Java8 :: " + cal3);
		cal3.add(Calendar.HOUR, 2);
		cal3.add(Calendar.MINUTE, -30);
//		System.out.println("Java8 :: " + cal3);
		
//		Difference Between Dates
		Date d1 = sdf.parse("01/04/2026");
		Date d2 = sdf.parse("18/04/2026");
		long diff = d2.getTime() - d1.getTime();
		long days = diff / (1000 * 60 * 60 * 24);
//		System.out.println("Java8 :: " + days);
		
//		Time Zones (Important 🔥)
		TimeZone tz = TimeZone.getTimeZone("Asia/Kolkata");
		Calendar cal4 = Calendar.getInstance(tz);

//		Convert Old ↔ New
//		Date → LocalDate
		Date date3 = new Date();
		LocalDate ld = date3.toInstant()
		                   .atZone(ZoneId.systemDefault())
		                   .toLocalDate();
//		System.out.println(ld);
		
//		System.out.println(ld.getDayOfYear());

		Calendar cal5 = Calendar.getInstance();
		cal5.set(2026, Calendar.APRIL, 17);
		int day = cal5.get(Calendar.DAY_OF_YEAR);
//		System.out.println(day);
		
		Calendar cal6 = Calendar.getInstance();
		cal6.set(Calendar.YEAR, 2026);
		cal6.set(Calendar.DAY_OF_YEAR, 108);

		Date date6 = cal6.getTime();
//		System.out.println(date6);
	}

	public static void java8Plus() throws Exception {
		LocalDate date = LocalDate.now();              // 2026-04-18
//		System.out.println("Java8+ :: " + date);
		LocalTime time = LocalTime.now();              // 10:30:45
//		System.out.println("Java8+ :: " + time);
		LocalDateTime dt = LocalDateTime.now();        // both
//		System.out.println("Java8+ :: " + dt);

//		Parse String → Date
		String input = "2026-04-17";
		LocalDate date1 = LocalDate.parse(input);  // ISO format
//		System.out.println("Java8+ :: " + date1);

		//Custom format:
		DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date2 = LocalDate.parse("16/04/2026", f);
//		System.out.println("Java8+ :: " + date2);
		
//		 Date Formatting
		LocalDateTime date3 = LocalDateTime.now();
		DateTimeFormatter f3 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatted = date3.format(f3);
//		System.out.println("Java8+ :: " + formatted);
		
//		Date Manipulations (Add/Subtract)
		LocalDate date4 = LocalDate.now();
//		System.out.println("Java8+ :: " + date4);
		date4 = date4.plusDays(5);
		date4 = date4.minusMonths(2);
		date4 = date4.plusYears(1);
//		System.out.println("Java8+ :: " + date4);
		
//		Time Manipulations
		LocalTime time2 = LocalTime.now();
//		System.out.println("Java8+ :: " + time2);
		time2 = time2.plusHours(2);
		time2 = time2.minusMinutes(30);
//		System.out.println("Java8+ :: " + time2);
		
//		Difference Between Dates
		LocalDate d1 = LocalDate.of(2026, 4, 1);
		LocalDate d2 = LocalDate.of(2026, 4, 18);

		long days = ChronoUnit.DAYS.between(d1, d2);
//		System.out.println("Java8+ :: " + days);

//		Compare Dates
		if (d1.isBefore(d2)) { 
//			System.out.println("d1.isBefore(d2)"); 
		}
		if (d2.isAfter(d1)) { 
//			System.out.println("d1.isAfter(d2)"); 
		}
		if (d1.isEqual(d2)) { 
//			System.out.println("d1 not equal d2");
		}
		
//		Time Zones (Important 🔥)
		ZonedDateTime zdt = ZonedDateTime.now();
		ZonedDateTime india = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));

//		LocalDate → Date
		LocalDate ld5 = LocalDate.now();
		Date date5 = Date.from(
		    ld5.atStartOfDay(ZoneId.systemDefault()).toInstant()
		);
//		System.out.println(date5);
		
//		System.out.println(ld1.getDayOfYear());
//		System.out.println(LocalDate.ofYearDay(2026, 108));
		
	}

}
