package dw;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

public class Application extends io.dropwizard.Application<Configuration>{

	
	private static final Logger log = Logger.getLogger("my-log-1");
	private static final Logger log2 = Logger.getLogger("my-log-2");
	
	@Override
	public void run(Configuration configuration, Environment environment) throws Exception {
		
		environment.jersey().register(HelloResource.class);
		environment.jersey().register(MyHeaderResponseFilter.class);
		
		ObjectMapper objectMapper = environment.getObjectMapper();
	}
	
	public static void main(String[] args) throws Exception {
		new Application().run("server", "/home/artur/dev/repo/sandbox/src/main/resources/config/test.yaml");
	}
	
	public static class MyHeaderResponseFilter implements ContainerResponseFilter {

		@Override
		public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
				throws IOException {
			responseContext.getHeaders().add("X-Powered-By", "foo");
		}
		
	}
}
