package viewTest;

import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

public class ViewApplication extends io.dropwizard.Application<Configuration>{

	@Override
	public void run(Configuration configuration, Environment environment) throws Exception {
		environment.jersey().register(ViewResource.class);
//		environment.jersey().register(HeaderResponseFilter.class);
//		environment.servlets().addFilter("Custom-Filter-Name", new ServletRequestFilter()).addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/assets/*");
		
	}
	
	@Override
	public void initialize(Bootstrap<Configuration> bootstrap) {
		super.initialize(bootstrap);
		bootstrap.addBundle(new ViewBundle<>());
//		bootstrap.addBundle(new AssetsBundle("/assets/", "/assets"));
	}
	
	public static void main(String[] args) throws Exception {
		
		System.err.println(ViewApplication.class.getResource("/viewTest/Two/TestView.ftl"));
		new ViewApplication().run("server", "/home/artur/dev/repo/sandbox/src/main/resources/config/test2.yaml");
	}
	
}
