package time;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeWithZoneIdSerializer;
import com.google.common.collect.Lists;

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
	
		
//		String ts = "2016-09-12T13:15:17.309Z";
//		
//		
//		ZonedDateTime parse2 = ZonedDateTime.parse(ts, DateTimeFormatter.ISO_DATE_TIME);
//		System.out.println(parse2);
//		
//		ZonedDateTime parse = 
//		    parse2
//		        .withZoneSameInstant(ZoneId.systemDefault());
//		
//		System.out.println(parse);
//        
//		ArrayList<String> tmp;
//		LocalDateTime now = LocalDateTime.now(Clock.systemUTC());
//		System.out.println(now);
//		System.out.println(LocalDateTime.now(ZoneId.of("UTC")));
//		
////		tmp.stream().filter( x -> x.startsWith("my-prefix")).sorted( (k1, k2) -> k1.compareTo(k2)).collect(Collectors.toList());
//		
//		
//		System.out.println("asd");
//		
//		LocalDateTime first = LocalDateTime.parse("2017-03-03T12:15:01Z", DateTimeFormatter.ISO_DATE_TIME);
//		LocalDateTime then = LocalDateTime.parse("2017-03-18T16:57:53Z", DateTimeFormatter.ISO_DATE_TIME);
//		
//		long until = first.until(then, ChronoUnit.MINUTES);
//		System.out.println(until);
//		
//		System.out.println(Lists.newArrayList("absd", "asass", "asd"));
//		System.out.println(Lists.newArrayList(1, 2 , 3));
	    
	    
	    
	    System.out.println(new Date(1506004423454l));
	    System.out.println(new Date(1506004390545l));
	    System.out.println(new Date(1506004423454l));
	}	
}
