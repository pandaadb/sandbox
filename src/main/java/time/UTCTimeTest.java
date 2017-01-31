package time;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class UTCTimeTest {

	public static void main(String[] args) {
		
		ZonedDateTime utcNow = Instant.now().atZone(ZoneId.of("UTC"));
		System.out.println(utcNow);
		
	}
}
