package hackerrank;

import java.lang.reflect.InvocationTargetException;

import hackerrank.InnerTst.Outer.Inner;

public class InnerTst {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
//		Object o = null;
//		Class<?> forName = Class.forName(InnerTst.class.getName() + "$Inner$Test");
//		
//		Constructor<?>[] constructor = forName.getDeclaredConstructors();
//		Constructor<?> c = constructor[0];
//		c.setAccessible(true);
//		o = c.newInstance(Inner.class.newInstance());
//		
//		Method testMethod = forName.getDeclaredMethods()[0];
//		testMethod.setAccessible(true);
//		testMethod.invoke(o, 5);
		
		Inner inner = new Outer().new Inner();
		inner.shout();
		
	}
	
	static class StaticStuff {
		private class Test {
			private void test(int num){
				System.out.println("Test " + num);
			};
		}
	}
	
	public static class Outer {
		
		private int outerInt = 2;
		
		public class Inner {
			
			public void shout() {
				System.out.println(outerInt);
			}
		}
	}
}
