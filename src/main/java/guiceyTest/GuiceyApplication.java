package guiceyTest;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.google.inject.AbstractModule;
import com.google.inject.PrivateModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import ru.vyarus.dropwizard.guice.GuiceBundle;
import ru.vyarus.dropwizard.guice.module.installer.install.binding.LazyBinding;

public class GuiceyApplication extends io.dropwizard.Application<Configuration>{

	@Override
	public void initialize(Bootstrap<Configuration> bootstrap) {
		
		AbstractModule m = new AbstractModule() {
			protected void configure() {
				
				
				
				install(new PrivateModule() {
					@Override
					protected void configure() {
						Map<String,String> props = new HashMap<>();
						props.put("private.specific.property", "pandaadb");
						Names.bindProperties(binder(), props);
						bind(Config.class).in(Singleton.class);
						
						// this is the resource we want to bind, but instantiate privetly 
						bind(Resource.class).in(Singleton.class);
								
						// expose it for others to use/be injected
						expose(Resource.class);
					}
				});
			};
		};
		
		
		bootstrap.addBundle(GuiceBundle.<Configuration>builder()
				.modules(m) // bind the module for resource CREATION
				.extensions(Resource.class) // add extension for jersey binding
				.build()
	    );
	}
	
	@Override
	public void run(Configuration configuration, Environment environment) throws Exception {
//		
//		Optional<Injector> oI = InjectorLookup.getInjector(this);
//		Injector i = oI.get();
//		
//		
//		Resource resource = i.getInstance(Resource.class);
//		
//		System.out.println(resource);
//		
		
	}
	
	public static void main(String[] args) throws Exception {
		new GuiceyApplication().run("server", "/home/artur/dev/repo/sandbox/src/main/resources/config/test2.yaml");
	}
	
	// want to hid this
	public static class Config {
		
		private String test;

		@Inject
		public Config(@Named("private.specific.property")String test) {
			this.test = test;
		}
		
	}
	
	@LazyBinding
	@Path("test")
	public static class Resource {
		
		private Config c;

		@Inject
		public Resource(Config c) {
			this.c = c;
			System.err.println("Created");
		}
		
		@GET
		public String getConfigValue() {
			return c.test;
		}
	}
	
}
