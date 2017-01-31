package test.guice;

import java.util.Enumeration;
import java.util.Properties;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Key;
import com.google.inject.Singleton;
import com.google.inject.multibindings.OptionalBinder;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

public class TestModule3 extends AbstractModule {

	@Override
	protected void configure() {

		OptionalBinder.newOptionalBinder(binder(), Key.get(String.class, Names.named("myValue"))).setDefault()
				.toInstance("777");

		Properties p = new Properties();
		p.setProperty("myValue", "12:777"); // This now has a default value

		// use enumeration to include the default properties
		for (Enumeration<?> e = p.propertyNames(); e.hasMoreElements();) {
			String propertyName = (String) e.nextElement();
			String value = p.getProperty(propertyName);

			String[] split = value.split(":");
			
			OptionalBinder<String> binding = OptionalBinder.newOptionalBinder(binder(), Key.get(String.class, Names.named(propertyName)));
			binding.setBinding().toInstance(split[0]);

		}

		bind(Manager.class).to(ManagerImpl.class).in(Singleton.class);
	}

	public static interface Manager {
		public void talk();
	}

	public static class ManagerImpl implements Manager {

		@Inject
		@Named("myValue")
		String test;

		@Override
		public void talk() {
			System.out.println(test);
		}
	}

	public static void main(String[] args) {

		Manager instance = Guice.createInjector(new TestModule3()).getInstance(Manager.class);
		instance.talk();

	}
}
