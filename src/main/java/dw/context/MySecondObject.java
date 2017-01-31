package dw.context;

import javax.inject.Inject;

public class MySecondObject {

	@Inject
	private MyObject o;
	
	
	public MyObject getO() {
		return o;
	}
}
