package snippet;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class LogWriter {


	public static Gson gson = new Gson();
	
	public static void main(String[] args) {
		
		try {
	        PrintStream out = new PrintStream(new FileOutputStream("/var/logstash/input/test2.log"));
	        System.setOut(out);
	    } catch (FileNotFoundException ex) {
	        System.out.print("Exception");
	    }
		
		Map<String, String> timestamper = new HashMap<>();
		
	    while(true)
	    {

	    	String format = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
	    	
	    	timestamper.put("myTimestamp", format);
	    	System.out.println(gson.toJson(timestamper));
	    	try {
	            Thread.sleep(1000);                 //1000 milliseconds is one second.
	        } catch(InterruptedException ex) {
	            Thread.currentThread().interrupt();
	        }
	    }
		
	}
}
