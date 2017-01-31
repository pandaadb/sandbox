package test.guice;

import java.util.Properties;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

public class TestModule extends AbstractModule {

	@Override
	protected void configure() {
		Properties p = new Properties();
		p.setProperty("my.test.string", "Some String"); // works with boolean, int, double ....
		Names.bindProperties(binder(),p);
//		bind(X.class).to(Test.class).in(Singleton.class); // This is now a guice managed singleton
		
		bind(X.class).toProvider(TestProvider.class).in(Singleton.class);
	}

	public interface X {
		
	}
	
	public static class Test implements X {
		
		private String test;

		@Inject
		public Test(@Named("my.test.string") String test) {
			this.test = test;
			System.out.println(this.test);
		}
		
		public String getTest() {
			return test;
		}
	}
	
	public static class TestProvider implements Provider<X> {

		private String test;

		private X instance;
		
		@Inject
		public TestProvider(@Named("my.test.string") String test) {
			this.test = test;
		}
		
		@Override
		public X get() {
			return instance;
		}
		
	}
	
	public static void main(String[] args) {
		
		Injector createInjector = Guice.createInjector(new TestModule());
		
		Provider<X> provider = createInjector.getProvider(X.class);
		
		System.out.println(provider);
		
		Provider<X> provider2 = createInjector.getProvider(X.class);
		
		System.out.println(provider);
		
		X x = provider.get();
		
		X x2 = provider2.get();
		
		
		
	}
}
