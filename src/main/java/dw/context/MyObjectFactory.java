package dw.context;

import org.glassfish.hk2.api.Factory;

public class MyObjectFactory implements Factory<MyObject> {

	@Override
	public MyObject provide() {
		return new MyObject();
	}

	@Override
	public void dispose(MyObject instance) {
		// TODO Auto-generated method stub
		
	}

}
