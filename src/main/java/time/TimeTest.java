package time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TimeTest {

	public static void main(String[] args) {
		
		
//		Instant parse = Instant.parse("2016-09-06T16:43:08.804Z");
//		System.out.println(parse);
//		
//		LocalDateTime now = LocalDateTime.now();
//		System.out.println("No is: " + now);
//		
//		Instant instant = now.atZone(ZoneId.systemDefault()).toInstant();
//		System.out.println("Instant: " + instant);
//		
//		LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
//		System.out.println("No at: " + ldt);
//		
//		System.out.println("asd");
	
		
		String ts = "2016-09-12T13:15:17.309Z";
		
		
		ZonedDateTime parse2 = ZonedDateTime.parse(ts, DateTimeFormatter.ISO_DATE_TIME);
		System.out.println(parse2);
		
		ZonedDateTime parse = 
		    parse2
		        .withZoneSameInstant(ZoneId.systemDefault());
		
		System.out.println(parse);
        
		ArrayList<String> tmp;
//		tmp.stream().filter( x -> x.startsWith("my-prefix")).sorted( (k1, k2) -> k1.compareTo(k2)).collect(Collectors.toList());
		
		
		System.out.println("asd");
		
		LocalDateTime first = LocalDateTime.parse("2017-03-03T12:15:01Z", DateTimeFormatter.ISO_DATE_TIME);
		LocalDateTime then = LocalDateTime.parse("2017-03-18T16:57:53Z", DateTimeFormatter.ISO_DATE_TIME);
		
		long until = first.until(then, ChronoUnit.MINUTES);
		System.out.println(until);
	}	
}
