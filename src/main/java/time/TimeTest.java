package time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

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
		
		ZonedDateTime zonedDateTime = Instant.parse(ts).atZone(ZoneId.of("UTC"));
		System.out.println(zonedDateTime);
		
		ZonedDateTime atZone = zonedDateTime.toInstant().atZone(ZoneId.of("BST"));
		System.out.println(atZone);
		
		
		ZonedDateTime parse2 = ZonedDateTime.parse(ts, DateTimeFormatter.ISO_DATE_TIME);
		System.out.println(parse2);
		
		ZonedDateTime parse = 
		    parse2
		        .withZoneSameInstant(ZoneId.systemDefault());
		
		System.out.println(parse);
        
		
	}
}
