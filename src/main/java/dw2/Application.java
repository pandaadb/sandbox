package dw2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import com.google.common.io.Files;

import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

public class Application extends io.dropwizard.Application<Configuration>{
	
	@Override
	public void run(Configuration configuration, Environment environment) throws Exception {
		environment.jersey().register(DLTest.class);
	}
	
	public static void main(String[] args) throws Exception {
		new Application().run("server", "/home/artur/dev/repo/sandbox/src/main/resources/config/test.yaml");
	}
	
	@Path("/dl")
	public static class DLTest {
		
		@GET
		@Produces("application/zip")
		public Response test() {
			return Response.ok(new StreamingOutput() {
				@Override
				public void write(OutputStream output) throws IOException, WebApplicationException {
					Files.copy(new File("/home/artur/status.json"), output);
					
					// alternatively manual copy: 
					try(FileInputStream s = new FileInputStream("/home/artur/status.json");) {
						byte[] chunks = new byte[1024];
						int read;
						while( (read = s.read(chunks)) != -1) {
							output.write(chunks);
							System.out.println(new String(new byte[] {(byte) read})); // see what you have 
						}
					}
					
				}
			}).build();
		}
	}
}