package time;

import java.time.Instant;
import java.time.ZoneId;

public class SecondOfEpoch {
	
	
	public static void main(String[] args) {
		
		System.out.println(Instant.parse("2016-11-01T12:06:44.342Z").atZone(ZoneId.of("UTC")).toEpochSecond());
		
		
		long time = 1480608777;
		
		long nextTime = 1480608788;
		
		System.out.println((nextTime - time) % 10);
		
	}
	
}
