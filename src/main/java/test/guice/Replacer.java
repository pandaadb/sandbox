package test.guice;

public class Replacer {

	static String test = "CLASSPATH=ASD.jar:/home/lib/test.jar\n"
			+ "\n"
			+ "test";
	
	public static void main(String[] args) {
		
		String replaceAll = test.replaceAll("CLASSPATH=.*", "\\$APP_HOME/lib/*.jar");
		System.out.println(replaceAll);
		
		
		
	}
}
