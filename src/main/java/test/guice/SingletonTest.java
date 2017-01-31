package test.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class SingletonTest {
	
	public static void main(String[] args) {
		
		Injector createInjector = Guice.createInjector(new AbstractModule() {
			
			@Override
			protected void configure() {
				bind(Test.class).asEagerSingleton();
			}
		});
		
		System.out.println("Start");
		Test instance = createInjector.getInstance(Test.class);
		System.out.println(instance);
		Test instance2 = createInjector.getInstance(Test.class);
		System.out.println(instance2);
	}

	
	public static class Test {
		
		public Test() {
			System.out.println("Constructor called");
		}
	}
}
