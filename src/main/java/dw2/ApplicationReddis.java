package dw2;

import java.io.IOException;
import java.io.OutputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import org.glassfish.jersey.server.ServerProperties;

import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ApplicationReddis extends io.dropwizard.Application<Configuration>{
	
	@Override
	public void initialize(Bootstrap<Configuration> bootstrap) {
		super.initialize(bootstrap);
	}
	
	@Override
	public void run(Configuration configuration, Environment environment) throws Exception {
		environment.jersey().property(ServerProperties.OUTBOUND_CONTENT_LENGTH_BUFFER, 0);
		environment.jersey().register(StreamResource.class);
	}
	
	public static void main(String[] args) throws Exception {
		new ApplicationReddis().run("server", "/home/artur/dev/repo/sandbox/src/main/resources/config/test.yaml");
	}
	
	@Path("test")
	public static class StreamResource{ 
		
		@GET
		public Response get() {
			return Response.ok(new StreamingOutput() {
				
				@Override
				public void write(OutputStream output) throws IOException, WebApplicationException {
					for(int i = 0; i < 100; i++) {
						output.write(("Hello " + i + "\n").getBytes());
						output.flush();
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).build();
		}
	}
	
}
