package google;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.PrivateModule;

public class GuicePrivateModuleOverwriteTest {

	
	public static void main(String[] args) {
		Injector i = Guice.createInjector(new Module1());
		i.getInstance(Test.class).write();
	}
	
	
	public static class Module1 extends AbstractModule {
		@Override
		protected void configure() {
			install(new Child());
		}
	}

	public static class Child extends PrivateModule {

		@Override
		protected void configure() {
			bind(Test.class).to(TestReal.class);
			expose(Test.class);
		}
		
	}
	
	public static interface Test {
		public void write();
	}
	
	public static class TestReal implements Test {
		public void write() {
			System.out.println("Real");
		} 
	}
	
	public static class TestFake implements Test {
		public void write() {
			System.out.println("Fake");
		} 
	}
}
