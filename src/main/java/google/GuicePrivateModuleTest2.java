package google;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.PrivateModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

import snippet.FilterTest.Test;

public class GuicePrivateModuleTest2 {

	
	public static void main(String[] args) {
		
		Injector injector = Guice.createInjector(new AbstractModule() {
			
			@Override
			protected void configure() {
				Map<String, String> tmp = new HashMap<>();
				tmp.put("test", "pandaadb");
				
				Names.bindProperties(binder(), tmp);
				
				bind(Conf.class).in(Singleton.class);
				bind(TestMe.class).in(Singleton.class);
				bind(TestMe.class).in(Singleton.class);
			}
		});
		
		injector.getInstance(TestMe.class);
	}
	
	public static class Conf {
		private String test;

		@Inject
		public Conf(@Named("test") String test) {
			this.test = test;
		}
		
	}
	
	
	public static class TestMe {
		
		private Conf c;

		@Inject
		public TestMe(Conf c) {
			this.c = c;
			System.err.println("Created");
		}
	}
	
}
