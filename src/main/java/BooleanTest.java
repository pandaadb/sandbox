
public class BooleanTest {

	public static void main(String[] args) {
		boolean falseTest = false;
		boolean trueTrest = true;
		
		trueTrest = true;
		trueTrest |= false;
		System.out.println( "true |= false => " + trueTrest );
		
		trueTrest = false;
		trueTrest |= false;
		System.out.println( "false |= false => " + trueTrest );
		
		trueTrest = false;
		trueTrest |= true;
		System.out.println( "false |= true => " + trueTrest );
		
		trueTrest = true;
		trueTrest |= true;
		System.out.println( "true |= true => " + trueTrest );
	}
}
