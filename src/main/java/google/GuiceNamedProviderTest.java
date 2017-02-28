package google;

import javax.inject.Named;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class GuiceNamedProviderTest extends AbstractModule {

	public static void main(String[] args) {
		Injector i = Guice.createInjector(new GuiceNamedProviderTest());
		i.getInstance(Inject.class);
	}

	@Override
	protected void configure() {
		bind(Inject.class).in(Singleton.class);
	}
	
	@Provides
	@Named("b1")
	@Singleton
	Bean b1() {
		Bean b = new Bean();
		b.name = "b1";
		return b;
	}
	
	@Provides
	@Named("b2")
	@Singleton
	Bean b2() {
		Bean b = new Bean();
		b.name = "b2";
		return b;
	}
	
	public static class Bean {
		String name;
	}
	
	public static class Inject {
		
		@javax.inject.Inject
		public Inject(@Named("b1") Bean b1, @Named("b2") Bean b2) {
			System.out.println(b1.name);
			System.out.println(b2.name);
		}
	}
	
}
