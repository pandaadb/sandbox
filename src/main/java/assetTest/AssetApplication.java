package assetTest;

import java.net.URL;
import java.net.URLClassLoader;

import com.codahale.metrics.MetricRegistry;

import io.dropwizard.Configuration;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class AssetApplication extends io.dropwizard.Application<Configuration> {

	@Override
	public void run(Configuration configuration, Environment environment) throws Exception {
	    
	    MetricRegistry metrics = environment.metrics();
	}

	@Override
	public void initialize(Bootstrap<Configuration> bootstrap) {
		super.initialize(bootstrap);
		bootstrap.addBundle(new AssetsBundle("/assets2/", "/assets"));
	}

	public static void main(String[] args) throws Exception {

		ClassLoader cl = ClassLoader.getSystemClassLoader();

		URL[] urls = ((URLClassLoader) cl).getURLs();

		URL r = null;
		
		for (URL url : urls) {
			System.out.println(url.getFile());
			if(url.getFile().contains("assets")) {
				r = url;
			}
		}

		System.out.println();
		
		URL resource2 = ClassLoader.getSystemClassLoader().getResource("/home/artur/tmp/assets/");
		
		URL resource = AssetApplication.class.getResource("/home/artur/tmp/assets/");
		
		
		new AssetApplication().run("server", "/home/artur/dev/repo/sandbox/src/main/resources/config/test.yaml");
	}

}
