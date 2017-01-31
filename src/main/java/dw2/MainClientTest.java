package dw2;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.apache.juli.JdkLoggerFormatter;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.logging.LoggingFeature.Verbosity;

public class MainClientTest {

	public static void main(String[] args) {
		
		Client build = ClientBuilder.newBuilder().build();
		
		// Configure Logger to log it all
		Logger logger = Logger.getLogger("test");
		logger.setLevel(Level.ALL);
		logger.setUseParentHandlers(false);
		Handler[] handlers = logger.getHandlers();
		for(Handler h : handlers) logger.removeHandler(h);
		logger.addHandler(buildseh());
		logger.info("Logger");
		build = build.register(new LoggingFeature(logger, Level.ALL, Verbosity.PAYLOAD_ANY, null));
		
		build.target("https://www.google.com").request().get();
	}
	
	public static StreamHandler buildseh() {
	    final StreamHandler seh = new StreamHandler(System.err, new JdkLoggerFormatter()) {
	        @Override
	        public synchronized void publish(final LogRecord record) {
	            super.publish(record);
	            flush();
	        }
	    };
	    seh.setLevel(Level.ALL); // Default StdErr Setting
	    return seh;
	}
}
