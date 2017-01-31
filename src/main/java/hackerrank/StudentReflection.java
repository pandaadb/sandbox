package hackerrank;

import java.lang.reflect.Method;
import java.util.TreeSet;

class Student {
	
}

public class StudentReflection {

	
	public static void main(String[] args) {
		Class c = Student.class;
		Method[] declaredMethods = c.getDeclaredMethods();
		TreeSet<String> names = new TreeSet<>();
		for(Method m : declaredMethods) {
		}
	}
	
}
