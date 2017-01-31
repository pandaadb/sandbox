package time;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeFormatTest {

	public static void main(String[] args) {
		ZonedDateTime now = ZonedDateTime.now();
		System.out.println(now);
		ZonedDateTime nowIndia = now.toLocalDateTime().atZone(ZoneId.of("Asia/Kolkata"));
		System.out.println(nowIndia);
	}
}
