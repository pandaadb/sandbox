package time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DTParserTest {

	public static void main(String[] args) {
		LocalDateTime parse = LocalDateTime.parse("2020-10-22T15:15", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
		System.out.println(parse);
	}
}
