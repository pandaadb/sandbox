package strings;

public class SubstringTest {

	
	public static void main(String[] args) {
		
		String pf = "PREFIX_";
		String test ="PREFIX_feed1";
		String ss = test.substring(pf.length(), test.length());
		System.out.println(ss);
		
	}
}
