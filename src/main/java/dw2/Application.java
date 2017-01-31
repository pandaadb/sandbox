package dw2;

import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

public class Application extends io.dropwizard.Application<Configuration>{
	
	@Override
	public void run(Configuration configuration, Environment environment) throws Exception {
		environment.jersey().register(MyResource.class);
	}
	
	public static void main(String[] args) throws Exception {
		new Application().run("server", "/home/artur/dev/repo/sandbox/src/main/resources/config/test.yaml");
	}
	
	public static class MyResourceBean {
		String test;
	}
}
