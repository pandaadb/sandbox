package time;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeShit {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {
//		printTime();
		
		ScheduledExecutorService s = Executors.newScheduledThreadPool(1);
		s.schedule(() -> System.out.println("Test"), 2000l, TimeUnit.MILLISECONDS);
		
		System.setProperty("faketime.offset.seconds", String.valueOf(60 * 60));
		
		System.out.println(new Date());
		System.out.println(LocalDateTime.now());
		System.out.println(ZonedDateTime.now());
		
		Thread.sleep(5000);
		
		System.out.println();
	}

	public static void printTime() throws ClassNotFoundException, SQLException {
		Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");

		ResultSet query = conn.createStatement().executeQuery("SELECT now() as ts");

		if (query.next()) {
			Timestamp long1 = query.getTimestamp("ts");
			System.out.println(long1);
		}

		conn.close();
	}
}
