package time;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class MinuteOfDayTest {

	public static void main(String[] args) {
		
		ZonedDateTime zdt = Instant.now().atZone(ZoneId.of("UTC"));
		
		int minOfDay = zdt.get(ChronoField.MINUTE_OF_DAY);
		
		System.out.println(minOfDay);
	}
	
}
