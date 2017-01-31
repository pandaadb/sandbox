package test.guice;

import java.time.Clock;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;

public class ClockInject {
	
	public static void main(String[] args) {
		
		Injector injector = Guice.createInjector(new AbstractModule() {
			
			@Override
			protected void configure() {
				// TODO Auto-generated method stub
				bind(Clock.class).toInstance(Clock.systemUTC());
			}
		});
		
		B instance = injector.getInstance(B.class);
		
	}
	
	public static abstract class A {

		@Inject
	    A(@Named("string1") String string1, @Named("string1") String string2,Clock clock) {
	       // initialize vars
	    }
	}

	public static class B extends A {

		B(String string1, String string2, Clock clock) {
			super(string1, string2, clock);
			// TODO Auto-generated constructor stub
		}

	}

	public static class C extends A {

		C(String string1, String string2, Clock clock) {
			super(string1, string2, clock);
			// TODO Auto-generated constructor stub
		}

	}
}
