package LogTest;

import org.apache.log4j.Logger;

import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class LogFilterTestApplication extends io.dropwizard.Application<Configuration>{

	private static final Logger log = Logger.getLogger(LogFilterTestApplication.class);
	
	@Override
	public void run(Configuration configuration, Environment environment) throws Exception {
	}
	
	@Override
	public void initialize(Bootstrap<Configuration> bootstrap) {
		super.initialize(bootstrap);
	}
	
	public static void main(String[] args) throws Exception {
		new LogFilterTestApplication().run("server", "/home/artur/dev/repo/sandbox/src/main/resources/config/test.yaml");
	}
	
}
