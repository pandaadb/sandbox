package test.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Singleton;

public class DoubleModuleTest {

	public static void main(String[] args) {
		AbstractModule a_mod = new AbstractModule() {

			@Override
			protected void configure() {
				bind(A.class).in(Singleton.class);
			}
		};

		AbstractModule b_mod = new AbstractModule() {

			@Override
			protected void configure() {
				bind(B.class).in(Singleton.class);
			}
		};

		AbstractModule c_mod = new AbstractModule() {

			@Override
			protected void configure() {
				bind(C.class).in(Singleton.class);
			}
		};
		
		
		C instance = Guice.createInjector(a_mod, b_mod, c_mod).getInstance(C.class);
	}

	public static class A {

		@Inject
		public A(B b) {
			// TODO Auto-generated constructor stub
		}

	}

	public static class B {
		@Inject
		public B(A a) {
			// TODO Auto-generated constructor stub
		}
	}

	public static class C {
		@Inject
		public C(A a, B b) {
			System.out.println("a");
		}
		
	}
}
