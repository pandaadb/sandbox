import javax.ws.rs.HeaderParam;

public class OverWriteTest {

	
	
	public static interface test {
		
		public void test(String x);
	}
	
	public static class TestImpl implements test {

		@Override
		public void test(@HeaderParam("asd") String x) {
			
		}
		
	}
}
