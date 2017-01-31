package snippet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FilterTest {

	
	public static void main(String[] args) {
		
		
		Test t = new Test();
		t.list = new ArrayList<>();
		
		
		t.list.add(new Model());
		t.list.add(new Model());
		t.list.add(new Model());
		t.list.add(new Model());

		Set<Test> set = new HashSet<>();
		
		set.add(t);
		
		
	}
	
	public static class Test { 
		
		List<Model> list;
	}
	
	public static class Model {
		
		boolean flag;
	}
}
