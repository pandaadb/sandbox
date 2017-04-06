package time;

import java.time.LocalDateTime;

public class OneMonthTest {

	public static void main(String[] args) {
		
		LocalDateTime now = LocalDateTime.now();
		System.out.println(now);
		System.out.println(now.plusMonths(1));
		
	}
}
