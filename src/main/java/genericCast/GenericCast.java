package genericCast;

import java.util.ArrayList;
import java.util.List;

public class GenericCast {

	public static void main(String[] args) {
		
		List test = new ArrayList();
		test.add(new Test1());
		
		List<Test1> x = (List<GenericCast.Test1>) test;
	}
	
	public static class Test1 {
		
		String test;
	}
	
	public static class Test2 {
		
		int number;
	}
	
}
