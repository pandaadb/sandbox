package dw2;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

public class Application extends io.dropwizard.Application<Configuration>{
	
	@Override
	public void run(Configuration configuration, Environment environment) throws Exception {
		environment.jersey().register(R2.class);
		environment.jersey().register(R1.class);
	}
	
	public static void main(String[] args) throws Exception {
		new Application().run("server", "/home/artur/dev/repo/sandbox/src/main/resources/config/test.yaml");
	}
	
	public static class MyResourceBean {
		String test;
	}
	
	@Path("/")
	public static class R1 {
		
		@GET
		@Path("/bar/stuff/foo")
		public String testR1() {
			return  "R1";
		}
	}
	
	@Path("bar")
	public static class R2 {
		
		@GET
		@Path("/bar/{id}")
		public String getId(@PathParam("id") String id) {
			return id;
		}
	}
}
