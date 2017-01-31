package test.guice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.matcher.Matchers;

public class TestModule2 extends AbstractModule {

	@Override
	protected void configure() {
		bind(Helper.class).in(Singleton.class);
		bind(Manager.class).to(ManagerImpl.class).in(Singleton.class);

		MyInterceptor i = new MyInterceptor();
		requestInjection(i); // inject the thing
		
		bindInterceptor(Matchers.any(), Matchers.any(), i);
	}

	public static interface Manager {
		public void talk();
	}
	
	public static class ManagerImpl implements Manager {
	
		@Inject
		public ManagerImpl(Helper h) {
		}

		@Override
		public void talk() {
			System.out.println("talking");
		}
	}
	
	public static class Helper {
		
	}
	
	public static class MyInterceptor implements MethodInterceptor {

		@Inject
		private Manager m;
		
		public MyInterceptor() {
		}
		
		@Override
		public Object invoke(MethodInvocation invocation) throws Throwable {
			System.out.println("intercept and manager " + m.getClass().getName());
			return invocation.proceed();
		}
	}
	
	public static void main(String[] args) {
		Injector createInjector = Guice.createInjector(new TestModule2());
		Manager instance = createInjector.getInstance(Manager.class);
		instance.talk();
	}
}
