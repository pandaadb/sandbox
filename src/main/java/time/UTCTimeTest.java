package time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class UTCTimeTest {

	public static void main(String[] args) {
		
		ZonedDateTime utcNow = Instant.now().atZone(ZoneId.of("UTC"));
		System.out.println(utcNow);
		
		LocalDateTime parse = LocalDateTime.parse("2017-03-29T10:00:00.000Z", DateTimeFormatter.ISO_DATE_TIME);
		
		System.out.println(parse);
		
		OffsetDateTime atOffset = parse.atOffset(ZoneOffset.of("+3"));
		System.out.println(atOffset);
		
		ZonedDateTime atZone = atOffset.toInstant().atZone(ZoneId.of("UTC"));
		System.out.println(atZone);
		
		System.out.println("Test conf + 3");
		
		OffsetDateTime parse2 = OffsetDateTime.parse("2017-03-29T10:00+03:00");
		System.out.println(parse2);
		System.out.println(parse2.atZoneSameInstant(ZoneId.of("UTC")));
		
		System.out.println("Test conf with Z");
		
		parse2 = OffsetDateTime.parse("2017-03-29T10:00Z");
		System.out.println(parse2);
		System.out.println(parse2.atZoneSameInstant(ZoneId.of("UTC")));
	}
}
