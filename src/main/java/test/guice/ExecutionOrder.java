package test.guice;

import java.util.Set;

import javax.inject.Inject;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;

public class ExecutionOrder {

	public static void main(String[] args) {
		
		Injector createInjector = Guice.createInjector(new AbstractModule() {
			@Override
			protected void configure() {
				Multibinder<Managed> multiBinder = Multibinder.newSetBinder(binder(), Managed.class);
				multiBinder.addBinding().to(Service1.class);
				multiBinder.addBinding().to(Service2.class);
				
				bind(ManagedManager.class).in(Singleton.class);
			}
		});
		
		createInjector.getInstance(ManagedManager.class); // start it
	}
	
	public interface Managed extends Comparable<Managed> {
		
		public default void start() {}
		public default int getOrder() { return 0;}
		
		@Override
		default int compareTo(Managed o) {
			return Integer.compare(getOrder(), o.getOrder());
		}
	}
	
	public static class ManagedManager {
		@Inject
		public ManagedManager(final Set<Managed> managed) {
			managed.stream().sorted().forEach(Managed::start);
		}
		
	}
	
	public static class Service1 implements Managed {
		
		@Override
		public void start() {
			System.out.println("Started Service 1");
		}
		
		@Override
		public int getOrder() {
			return 1;
		}
	}
	
	public static class Service2 implements Managed {
		
		@Override
		public void start() {
			System.out.println("Started Service 2");
		}
		
		@Override
		public int getOrder() {
			return 2;
		}
	}
}
