package HK2Test;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

import io.dropwizard.Configuration;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class GuiceJerseyManualBridge extends io.dropwizard.Application<Configuration> {

	@Override
	public void run(Configuration configuration, Environment environment) throws Exception {
		JerseyEnvironment jersey = environment.jersey();

		// create the Guice env and its dependencies
		Injector i = Guice.createInjector(new AbstractModule() {
			@Override
			protected void configure() {
				Map<String, String> props = new HashMap<>();
				props.put("testme", "Hello World Guice Inject Test");
				Names.bindProperties(binder(), props);
				bind(HelloResource.class);
			}
		});
		
		// get instance
		
		HelloResource resourceInstance = i.getInstance(HelloResource.class);
		jersey.register(new AbstractBinder() {
			
			@Override
			protected void configure() {
				// teach jersey about your guice dependency 
				bind(resourceInstance).to(HelloResource.class);
			}
		});
		
		jersey.register(HelloResource.class); // register resource - jersey will discover this from the binding
		
//		ResourceConfig c  = new ResourceConfig();
//		c.register(resourceInstance);
	}

	@Override
	public void initialize(Bootstrap<Configuration> bootstrap) {
		super.initialize(bootstrap);
	}

	public static void main(String[] args) throws Exception {
		new GuiceJerseyManualBridge().run("server", "/home/artur/dev/repo/sandbox/src/main/resources/config/test2.yaml");
	}

	@Path("test")
	@Produces(MediaType.APPLICATION_JSON)
	public static class HelloResource {

		
		
		@Inject
		@Named("testme")
		private String testString;

		public HelloResource() {
			System.err.println("I am created now");
		}
		
		@GET
		@Path("test")
		public String test(String x) {
			return testString;
		}

	}

}
